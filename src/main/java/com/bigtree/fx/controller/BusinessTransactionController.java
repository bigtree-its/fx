package com.bigtree.fx.controller;

import java.time.LocalDate;
import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.service.BusinessTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessTransactionController {

    @Autowired
    BusinessTransactionService businessTransactionService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Business Transaction";
    }

    @RequestMapping("/transactions")
    public ResponseEntity getTransactions(){
        final List<BusinessTransaction> transactions = businessTransactionService.getTransactions(LocalDate.now().getMonth());
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }
}