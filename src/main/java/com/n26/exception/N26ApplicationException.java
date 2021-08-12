package com.n26.exception;

import com.n26.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * class to handle exceptions for application
 */

@ControllerAdvice
public class N26ApplicationException {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<Void> handleInvalidFieldsException(InvalidFieldsException exception) {
        LOGGER.debug("Either amount or timestamp is not valid. " +
                "Throwing InvalidFieldsException due to -" + exception.getStackTrace().toString());
        return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TransactionOlderException.class)
    public ResponseEntity<Void> handleTransactionOlderException(TransactionOlderException exception) {
        LOGGER.debug("Transaction is older. Throwing TransactionOlderException");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidJSONException.class)
    public ResponseEntity<Void> handleInvalidJSONException(InvalidJSONException exception) {
        LOGGER.debug("Request is not valid. Please provide amount and/or timestamp");
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    /**
     * global exception
     */
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception exception) {
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
