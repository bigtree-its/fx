package com.bigtree.fx.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

public class BusinessTransactionCSVReaderTest {

    private BusinessTransactionCSVReader businessTransactionCSVReader = new BusinessTransactionCSVReader();

    @Test
    public void testGetSpending() throws FileNotFoundException {
        File file = ResourceUtils.getFile("src/test/resources/test-business-transaction.csv");
        if ( file != null && file.isFile()){
            List<BusinessTransaction> transactions = businessTransactionCSVReader.getTransactions(file.getPath());
            assertNotNull(transactions);
        }
    }
}