package com.n26.service;

import com.n26.exception.InvalidFieldsException;
import com.n26.model.Statistics;
import com.n26.model.Transaction;

/**
 * service interface
 */
public interface AppService {
    void insertTransaction(Transaction transaction) throws InvalidFieldsException;

    Statistics getStatistics();

    void deleteTransactions();
}
