package com.n26.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

/**
 * holds statistics attributes
 */
@Data
public class Statistics {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal sum;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal avg;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal max;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal min;
    private long count;

    /**
     * @param sum
     * @param avg
     * @param max
     * @param min
     * @param count
     */
    public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }


    /**
     * @return sum
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     * @param sum
     */
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    /**
     * @return avg
     */
    public BigDecimal getAvg() {
        return avg;
    }

    /**
     * @param avg
     */
    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    /**
     * @return max
     */
    public BigDecimal getMax() {
        return max;
    }

    /**
     * @param max
     */
    public void setMax(BigDecimal max) {
        this.max = max;
    }

    /**
     * @return min
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     *
     * @param min
     */
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     *
     * @return count
     */
    public long getCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(long count) {
        this.count = count;
    }
}
