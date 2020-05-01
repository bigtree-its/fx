package com.bigtree.fx;

import static org.mockito.ArgumentMatchers.nullable;

import com.bigtree.fx.jpa.BusinessTransactionRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BusinessTrxRepositoryTest {

    @Autowired
    BusinessTransactionRepository repository;

    @Test
    public void test() {
        Assertions.assertThat(repository != nullable(null));
    }
}