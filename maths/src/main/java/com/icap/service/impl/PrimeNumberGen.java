package com.icap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by admin on 10/04/2016.
 */
public class PrimeNumberGen implements Callable<Integer>{

    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberGen.class);

    Integer num;

    PrimeNumberGen(Integer n) {
        num = n;
    }

    @Override
    public Integer call() throws Exception {
        if (isPrime(num)) {
            //logger.debug("Thread id {}", Thread.currentThread().getId());
            return num;
        }
        return null;
    }

    boolean isPrime(Integer n) { //first test is 0
        if(n<2) return false;
        if(n==2) return true;
        if(n%2==0) return false;

        int max = n/2;
        for(int i=3; i< max; i=i+2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
