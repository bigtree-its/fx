package com.bigtree.fx.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

public class BusinessTransactionServiceTest {

    BusinessTransactionService service = new BusinessTransactionService();

    @Test
    public void testSaveEntries() throws URISyntaxException, IOException {
        File file = ResourceUtils.getFile("src/test/resources/entry.csv");
        byte[] readAllBytes = Files.readAllBytes(file.toPath());
        String content = new String(readAllBytes);
        service.create(content);
    }

}