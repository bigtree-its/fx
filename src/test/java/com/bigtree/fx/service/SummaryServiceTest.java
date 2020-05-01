package com.bigtree.fx.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.model.summary.Summary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

public class SummaryServiceTest {

    BusinessTransactionService transactionService = new BusinessTransactionService();
    SummaryService service = new SummaryService();

    @Test
    public void testSummary() throws IOException {
        String fileContent = getFileContent();
        List<BusinessTransaction> transactions = transactionService.extracted(fileContent);
        Summary summary = service.prepareSumamry(transactions);
        Assertions.assertNotNull(summary);
    }

    private String getFileContent() throws FileNotFoundException, IOException {
        File file = ResourceUtils.getFile("src/test/resources/entry.csv");
        byte[] readAllBytes = Files.readAllBytes(file.toPath());
        return new String(readAllBytes);
    }

}