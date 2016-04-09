package com.icap.model;

import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
public class PrimeNumbersDTO {
    private Integer noOfPrimeNumbers;
    private Set<Integer> primeNumbers;
    private long processTime;

    public Integer getNoOfPrimeNumbers() {
        return noOfPrimeNumbers;
    }

    public void setNoOfPrimeNumbers(Integer noOfPrimeNumbers) {
        this.noOfPrimeNumbers = noOfPrimeNumbers;
    }

    public Set<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(Set<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }
}
