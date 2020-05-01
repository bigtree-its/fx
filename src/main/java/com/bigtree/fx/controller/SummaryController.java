package com.bigtree.fx.controller;

import com.bigtree.fx.model.summary.Summary;
import com.bigtree.fx.service.SummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SummaryController {

    @Autowired
    SummaryService service;

    @GetMapping("/summary/{year}")
    public ResponseEntity<Summary> getSummary(@PathVariable("year") final int year) {
        log.info("Received request to get summary for year {}", year);
        final Summary summary = service.getSummary(year);
        if ( summary != null){
            return ResponseEntity.ok().body(summary);
        }
        return ResponseEntity.ok().body(Summary.builder().build());
    }
}