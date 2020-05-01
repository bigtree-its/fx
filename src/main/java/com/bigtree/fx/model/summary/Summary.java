package com.bigtree.fx.model.summary;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Summary {

    private Period period;
    private BigDecimal totalIn;
    private BigDecimal totalOut;
    private TransactionGroup in;
    private TransactionGroup out;
}