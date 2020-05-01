package com.bigtree.fx.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bigtree.fx.entity.BusinessTransactionEntity;
import com.bigtree.fx.jpa.BusinessTransactionRepository;
import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.model.TransactionType;
import com.google.common.annotations.VisibleForTesting;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BusinessTransactionService {

    @Autowired
    BusinessTransactionRepository businessTransactionRepository;

    public List<BusinessTransaction> getTransactions(int year) {
        return getTransactions(year, 0);
    }

    public List<BusinessTransaction> getTransactions(int year, int month) {
        LocalDate from = null;
        LocalDate to = null;
        if (month < 1 || month > 12) {
            // No month given, getting transactions for whole year
            from = LocalDate.of(year, Month.JANUARY, 1);
            to = LocalDate.of(year, Month.DECEMBER, Month.DECEMBER.minLength());
        } else {
            Month m = Month.of(month);
            from = LocalDate.of(year, m, 1);
            to = LocalDate.of(year, m, m.minLength());
        }
        log.info("Getting transactions for period {} and {}", from, to);
        List<BusinessTransaction> list = new ArrayList<>();
        try {
            Iterable<BusinessTransactionEntity> allRecords = businessTransactionRepository.findAllByDateBetween(from, to);
            for (BusinessTransactionEntity entity : allRecords) {
                list.add(BusinessTransaction.builder().amount(entity.getAmount()).date(entity.getDate())
                        .reference(entity.getReference()).transactionType(entity.getTransactionType()).build());
            }
        } catch (final Exception e) {
            log.info("Failed to get transactions for period  {} and {}", from, to);
        }
        return list;
    }

    public void create(String entries) {
        log.info("Saving new transaction into database...");
        if (StringUtils.isEmpty(entries)) {
            log.error("Content is empty. Save BizTrxEntity failed");
            return;
        }
        final List<BusinessTransaction> transactions = extracted(entries);
        try {
            log.info("Saving {} entities", transactions.size());
            if (CollectionUtils.isNotEmpty(transactions)) {
                final List<BusinessTransactionEntity> entities = transactions.stream().map(BusinessTransactionEntity::from)
                        .collect(Collectors.toList());
                final Iterable<BusinessTransactionEntity> saved = businessTransactionRepository.saveAll(entities);
                if (saved == null) {
                    log.error("Save BizTrxEntity failed");
                } else {
                    log.info("Save BizTrxEntity success");
                }
            }
        } catch (Exception e) {
            log.error("Save BizTrxEntity failed", e);
        }

    }

    @VisibleForTesting
    List<BusinessTransaction> extracted(final String entries) {
        final String[] lines = entries.split("\\r?\\n");
        boolean header = false;
        final List<BusinessTransaction> transactions = new ArrayList<>();
        for (String row : lines) {
            if (!header) {
                header = true;
                continue;
            }
            final String[] data = row.split(",");
            try {
                final Date date = new SimpleDateFormat("dd MMM yyyy").parse(data[0]);
                final TransactionType transactionType = TransactionType.valueOf(data[1]);
                final BusinessTransaction entity = BusinessTransaction.builder()
                        .date(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .amount(transactionType == TransactionType.CR ? new BigDecimal(data[4])
                                : new BigDecimal(data[3]))
                        .reference(data[2]).transactionType(transactionType).build();
                transactions.add(entity);
            } catch (ParseException e) {
                log.error("Transaction date is not parseable {}", data[0]);
            }

        }
           return transactions;
    }

}