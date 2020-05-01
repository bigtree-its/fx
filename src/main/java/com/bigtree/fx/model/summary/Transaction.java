package com.bigtree.fx.model.summary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bigtree.fx.model.BusinessTransaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private String reference;
    private LocalDate date;
    private BigDecimal amount;

    public static Transaction from(BusinessTransaction t) {
        return Transaction.builder().amount(t.getAmount()).reference(t.getReference()).date(t.getDate()).build();
    }

    public static List<Transaction> fromList(List<BusinessTransaction> t) {
        return t.stream().map(Transaction::from).collect(Collectors.toList());
    }
}