package com.n26.util;

import com.n26.Application;
import com.n26.exception.InvalidFieldsException;
import com.n26.exception.TransactionOlderException;
import com.n26.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

/**
 * Class contains validate methods
 */
@Slf4j
public class N26Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * method to check if transaction is valid (not older than 60 seconds and not of the future date)
     *
     * @param transaction
     */
    public static void checkTransactionValidity(Transaction transaction) {
        String methodName = "N26Validator.checkTransactionValidity ";
        Instant currentUTCDate = Instant.now();
        //check if transaction is of the future date
        Duration duration = Duration.between(Instant.parse(transaction.getTimestamp()), currentUTCDate);
        if (duration.toMillis() < 0) {
            throw new InvalidFieldsException();
        }
        //check if the transaction is older than 60 seconds
        if (duration.toMillis() > N26Constants.STATS_VALID_SECONDS) {
            throw new TransactionOlderException();
        }
    }

    /**
     * Method to validate request fields for /transactions - POST operation
     *
     * @param transaction
     */
    public static void validateTxnReqAttributes(Transaction transaction) {
        try {
            BigDecimal.valueOf(Double.valueOf(transaction.getAmount()));
            Instant.parse(transaction.getTimestamp());
        } catch (Exception e) {
            throw new InvalidFieldsException();
        }
    }
}
