package com.example.fetch.interview;

import com.example.fetch.interview.models.Receipt;
import com.example.fetch.interview.scorer.ReceiptScorer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ReceiptScorerTest {
    private static Receipt receipt1;
    private static Receipt receipt2;

    @BeforeAll
    static void testSetup() throws IOException {
        String recept1Json = readFileToString("receipt1.json");
        String recept2Json = readFileToString("receipt2.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);

        receipt1 = objectMapper.readValue(recept1Json, Receipt.class);
        receipt2 = objectMapper.readValue(recept2Json, Receipt.class);
    }

    @Test
    public void test_WHEN_ValidReceipt_THEN_return_correct_score() {
        Assert.isTrue(ReceiptScorer.scoreReceipt(receipt1).getPoints() == 28);
        Assert.isTrue(ReceiptScorer.scoreReceipt(receipt2).getPoints() == 109);
    }

    public static String readFileToString(String filePath) throws IOException {
        String path = Objects.requireNonNull(ReceiptScorer.class.getClassLoader().getResource(filePath)).getPath();
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
