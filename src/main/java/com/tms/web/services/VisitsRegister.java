package com.tms.web.services;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

@Component
@Profile({"develop", "test"})
public class VisitsRegister {

    @Value("${output.file}")
    private String filePath;

    @Bean
    public Integer readDoc() throws IOException {
        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get(filePath)))) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            String text = extractor.getText();
            String number = text.substring(0, text.length() - 1);
            Integer visits = Integer.parseInt(number);
            visits++;
            return visits;
        } catch (NoSuchFileException exc) {
            return 0;
        }
    }

    @Bean
    public void writeDoc() throws IOException {
        String visits = readDoc().toString();
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(visits);
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                doc.write(out);
            }
        }
    }
}