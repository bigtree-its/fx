package com.bigtree.fx.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.model.TransactionType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BusinessTransactionCSVReader {

    public List<BusinessTransaction> getTransactions(String file) {
        List<BusinessTransaction> transactions = new ArrayList<>();
        if (StringUtils.isNotEmpty(file)) {
            BufferedReader csvReader;
            try {
                csvReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                log.error("CSV file: {} not found", file);
                return null;
            }
            String row = "";
            try {
                boolean header = false;
                while ((row = csvReader.readLine()) != null) {
                    if ( !header){
                        header = true;
                        continue;
                    }
                    String[] data = row.split(",");
                    Date date = new SimpleDateFormat("dd MMM yyyy").parse(data[0]);
                    TransactionType transactionType = TransactionType.valueOf(data[1]);
                    BusinessTransaction spending = BusinessTransaction.builder()
                            .date(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .amount(transactionType == TransactionType.CR ? new BigDecimal(data[4])
                                    : new BigDecimal(data[3]))
                            .reference(data[2])
                            .transactionType(transactionType)
                            .build();
                    transactions.add(spending);
                }
            } catch (IOException e) {
                log.error("CSV file: {} cannot loaded", file);
                return null;
            } catch (ParseException e) {
                log.error("Cannot parse the date");
                return null;
            } finally {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    log.error("CSV file: {} cannot loaded", file);
                    return null;
                }
            }
        }
        return transactions;
    }

}