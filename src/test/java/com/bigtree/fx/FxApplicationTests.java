package com.bigtree.fx;

import com.bigtree.fx.controller.BusinessTransactionController;
import com.bigtree.fx.jpa.BusinessTransactionRepository;
import com.bigtree.fx.service.BusinessTransactionService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FxApplicationTests {

	@Autowired
	BusinessTransactionController controller;

	@Autowired
	BusinessTransactionService service;

	@Autowired
	BusinessTransactionRepository repository;

	@Test
	void contextLoads() {
		Assertions.assertThat(controller != null);
		Assertions.assertThat(service != null);
		Assertions.assertThat(repository != null);
	}

}
