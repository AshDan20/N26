package com.n26.util;

import com.n26.model.Transaction;

import java.math.BigDecimal;

/**
 * Class to have utility methods
 */
public class N26Util {

    private static final double ZERO = 0;

    /**
     * validate transaction request
     *
     * @param transaction
     * @return
     */
    public static boolean validateTransactionJSON(Transaction transaction) {
        return isEmpty(transaction.getAmount()) || isEmpty(transaction.getTimestamp());
    }

    /**
     * check null or empty
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return ((string == null) || (string.length() < 1));
    }

    /**
     * return BigDecimal of attribute HALF_ROUND_UP
     *
     * @param
     * @return
     */
    public static BigDecimal format(double attr) {
        //handle edge case - if stat is empty, make min and max zero
        if(attr == Double.POSITIVE_INFINITY || attr == Double.NEGATIVE_INFINITY){
            attr = N26Util.ZERO;
        }
        return BigDecimal.valueOf(attr).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
