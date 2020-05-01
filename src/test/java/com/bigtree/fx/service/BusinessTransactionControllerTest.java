package com.bigtree.fx.service;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import com.bigtree.fx.controller.BusinessTransactionController;
import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.utils.BusinessTransactionCSVReader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

@WebMvcTest(BusinessTransactionController.class)
public class BizTrxControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BusinessTransactionService service;

	BusinessTransactionCSVReader csvreader = new BusinessTransactionCSVReader();

	@Test
	public void name() throws Exception {
		File file = ResourceUtils.getFile("src/test/resources/test-business-transaction.csv");
		List<BusinessTransaction> list = csvreader.getTransactions(file.getPath());
		when(service.getTransactions(2019)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/transactions")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
				// .andExpect(MockMvcResultMatchers.content().json(""));
	}
}