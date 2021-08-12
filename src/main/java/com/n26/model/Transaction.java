package com.n26.model;

import lombok.Data;

/**
 * holds transaction
 */

@Data
public class Transaction {

    private String amount;
    private String timestamp;

    /**
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @param amount
     * @param timestamp
     */
    public Transaction(String amount, String timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

}
