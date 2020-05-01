package com.bigtree.fx.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table( uniqueConstraints = { @UniqueConstraint(columnNames = { "date", "reference" }) })
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    // @Convert(converter = DateConverter.class)
    private LocalDate date;

    @Column(nullable = false)
    private String reference;

    private String category;

    public  static BusinessTransactionEntity from(BusinessTransaction t) {
        if (t != null) {
            return BusinessTransactionEntity.builder().amount(t.getAmount()).date(t.getDate())
            .reference(t.getReference())
            .category(t.getCategory())
                    .transactionType(t.getTransactionType()).build();
        }
        return null;
    }
}