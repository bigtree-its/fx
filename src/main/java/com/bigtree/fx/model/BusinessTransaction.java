package com.bigtree.fx.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bigtree.fx.entity.BusinessTransactionEntity;

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
    private String category;

    public static BusinessTransaction from(BusinessTransactionEntity entity) {
        if (entity != null) {
            return BusinessTransaction.builder().amount(entity.getAmount()).date(entity.getDate())
                    .reference(entity.getReference())
                    .category(entity.getCategory())
                    .transactionType(entity.getTransactionType()).build();
        }
        return null;
    }

}