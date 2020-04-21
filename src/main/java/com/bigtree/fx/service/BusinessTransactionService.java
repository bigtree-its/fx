package com.bigtree.fx.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.utils.BusinessTransactionCSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BusinessTransactionService {

    @Autowired
    BusinessTransactionCSVReader businessTransactionCSVReader;

    public List<BusinessTransaction> getTransactions(final Month month) {
        log.debug("Getting transactions for month {}", month.name());
        File file;
        try {
            file = ResourceUtils.getFile("src/main/resources/static/biz-trx-oct-2019.csv");
            return businessTransactionCSVReader.getTransactions(file.getPath());
        } catch (final FileNotFoundException e) {
            log.error("Could not load transactions for month {}", month.name());
        }
        return null;
    }
    
    
    
}