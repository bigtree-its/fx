package com.bigtree.fx.controller;

import java.util.List;

import com.bigtree.fx.model.BusinessTransaction;
import com.bigtree.fx.service.BusinessTransactionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BusinessTransactionController {

    @Autowired
    BusinessTransactionService businessTransactionService;

    @GetMapping("/")
    public String welcome() {
        log.info("Welcome endpoint");
        return "Welcome to Business Transaction";
    }

    @GetMapping("/transactions/{year}")
    public ResponseEntity<List<BusinessTransaction>> getTransactions(@PathVariable("year") int year) {
        final List<BusinessTransaction> transactions = businessTransactionService
                .getTransactions(year);
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/transactions")
    public ResponseEntity<String> add(@RequestBody String csv) {
        log.info("Creating new transaction from {}", csv);
        if (StringUtils.isEmpty(csv)) {
            log.error("Request content is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request. Request content is empty");
        }
        businessTransactionService.create(csv);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }
}