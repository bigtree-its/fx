package com.bigtree.fx.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.model.TransactionType;
import com.bigtree.fx.model.summary.Party;
import com.bigtree.fx.model.summary.Period;
import com.bigtree.fx.model.summary.Summary;
import com.bigtree.fx.model.summary.Transaction;
import com.bigtree.fx.model.summary.TransactionGroup;
import com.google.common.annotations.VisibleForTesting;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SummaryService {

    @Autowired
    BusinessTransactionService transactionService;

    public Summary getSummary(final int year) {
        log.info("Preparing summary for year {}", year);
        final List<BusinessTransaction> transactions = transactionService.getTransactions(year);
        if (CollectionUtils.isEmpty(transactions)) {
            log.error("No transactions found for the year {}", year);
            return Summary.builder().build();
        }
        return prepareSumamry(transactions);
    }

    @VisibleForTesting
    Summary prepareSumamry(final List<BusinessTransaction> transactions) {

        final BigDecimal totalIn = transactions.stream().filter(t -> t.getTransactionType() == TransactionType.CR)
                .map(BusinessTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalOut = transactions.stream().filter(t -> t.getTransactionType() != TransactionType.CR)
                .map(BusinessTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<BusinessTransaction> sorted = transactions.stream().sorted(
                Comparator.comparing(BusinessTransaction::getDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        List<BusinessTransaction> credits = sorted.stream().filter(t -> t.getTransactionType() == TransactionType.CR)
                .collect(Collectors.toList());
        List<BusinessTransaction> debits = sorted.stream().filter(t -> t.getTransactionType() != TransactionType.CR)
                .collect(Collectors.toList());

        Map<String, List<BusinessTransaction>> creditsByReference = credits.stream()
                .collect(Collectors.groupingBy(BusinessTransaction::getReference));

        Map<String, List<BusinessTransaction>> debitsByReference = debits.stream()
                .collect(Collectors.groupingBy(BusinessTransaction::getReference));

        List<Party> payerParties = new ArrayList<>();
        creditsByReference.entrySet().forEach(e -> {
            payerParties.add(Party.builder().name(e.getKey())
                    .amount(e.getValue().stream().map(BusinessTransaction::getAmount).reduce(BigDecimal.ZERO,
                            BigDecimal::add))
                    .payments(e.getValue().stream().map(Transaction::from).collect(Collectors.toList())).build());
        });
        List<Party> payees = new ArrayList<>();
        debitsByReference.entrySet().forEach(e -> {
            payees.add(Party.builder().name(e.getKey())
                    .amount(e.getValue().stream().map(BusinessTransaction::getAmount).reduce(BigDecimal.ZERO,
                            BigDecimal::add))
                    .payments(e.getValue().stream().map(Transaction::from).collect(Collectors.toList())).build());
        });
        return Summary
                .builder().totalIn(totalIn).totalOut(totalOut).period(Period.builder().from(sorted.get(0).getDate())
                        .to(sorted.get(sorted.size() - 1).getDate()).build())
                .in(TransactionGroup.builder().parties(payerParties).build())
                .out(TransactionGroup.builder().parties(payees).build())
                .build();

    }

}