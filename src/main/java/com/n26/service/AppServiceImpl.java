package com.n26.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.n26.Application;
import com.n26.exception.InvalidJSONException;
import com.n26.model.Statistics;
import com.n26.model.Transaction;
import com.n26.util.N26Constants;
import com.n26.util.N26Util;
import com.n26.util.N26Validator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Insert, Deletion of transaction takes place in this class
 */

@Slf4j
@Service
public class AppServiceImpl implements AppService {

    public static final List<Transaction> TRANSACTIONS = new ArrayList<Transaction>();
    private final Object lock = new Object();

    /**
     * method to insert transaction
     *
     * @param transaction
     */
    @Override
    public void insertTransaction(Transaction transaction) {
        N26Validator.validateTxnReqAttributes(transaction);
        N26Validator.checkTransactionValidity(transaction);
        synchronized (lock) {
            TRANSACTIONS.add(transaction);
        }
    }


    /**
     * Method to get all statistics for last 60 seconds
     *
     * @return statistics
     */
    @Override
    public Statistics getStatistics() {
        Instant currentUTCDate = Instant.now();
        Statistics statResponse;
        synchronized (lock) {
            DoubleSummaryStatistics stat = TRANSACTIONS
                    .stream()
                    .filter(record -> Duration.between(Instant.parse(record.getTimestamp()), currentUTCDate).toMillis() < N26Constants.STATS_VALID_SECONDS)
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getAmount()))
                    .summaryStatistics();
            statResponse = getStatResponse(stat);
        }
        return statResponse;
    }

    /**
     * method to delete all the transactions
     */
    @Override
    public void deleteTransactions() {
        if (!CollectionUtils.isEmpty(TRANSACTIONS)) {
            synchronized (lock) {
                TRANSACTIONS.clear();
            }
        }
    }

    /**
     * method to prepare statistics object
     *
     * @return
     * @param stat
     */
    private Statistics getStatResponse(DoubleSummaryStatistics stat) {
        return new Statistics(N26Util.format(stat.getSum()), N26Util.format(stat.getAverage()),
                N26Util.format(stat.getMax()), N26Util.format(stat.getMin()), stat.getCount());
    }

}
