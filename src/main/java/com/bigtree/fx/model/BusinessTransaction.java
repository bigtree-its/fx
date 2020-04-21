package com.bigtree.fx.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessTransaction {

    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDate date;
    private String reference;

}