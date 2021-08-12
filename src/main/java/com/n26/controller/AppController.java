package com.n26.controller;

import com.n26.exception.InvalidJSONException;
import com.n26.model.Statistics;
import com.n26.model.Transaction;
import com.n26.service.AppService;
import com.n26.util.N26Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

/**
 * controller to handle all the requests
 */

@Slf4j
@RestController
public class AppController {

    @Autowired
    AppService appService;

    /**
     * insert transaction operation
     * @param transaction
     * @return
     */
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    private ResponseEntity<Void> insertTransaction(@RequestBody Transaction transaction){
        //check if request is valid in terms of transaction fields
        if(N26Util.validateTransactionJSON(transaction)){
            throw new InvalidJSONException();
        }
        appService.insertTransaction(transaction);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * get statistics in last 60 seconds
     * @return
     */
    @GetMapping("/statistics")
    private ResponseEntity getStatistics(){
        Statistics statistics = appService.getStatistics();
        Map tempMap = new HashMap<>();
        return new ResponseEntity(statistics, HttpStatus.OK);
    }

    /**
     * delete transactions operation
     * @return
     */
    @DeleteMapping
    private ResponseEntity<Void> deleteTransactions(){
        appService.deleteTransactions();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
