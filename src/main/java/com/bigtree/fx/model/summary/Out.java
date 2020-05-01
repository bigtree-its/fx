package com.bigtree.fx.model.summary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Out {

    private Transaction topPayee;
    private Transaction topMonth;
    private List<Transaction> payments;
    private Monthly monthly;

}