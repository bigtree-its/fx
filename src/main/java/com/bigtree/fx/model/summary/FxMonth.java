package com.bigtree.fx.model.summary;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FxMonth {

    private String month;
    private BigDecimal amount;
    private List<Transaction> transactions;
}