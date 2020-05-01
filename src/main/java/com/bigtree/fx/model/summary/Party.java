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
public class Party {
 
    private String name;
    private BigDecimal amount;
    private List<Transaction> payments;

}