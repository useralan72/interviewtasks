package com.icap.service.impl;

import com.icap.service.PrimeNumberService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by admin on 09/04/2016.
 */
@Service
public class SimpleLoopingPrimeNumberService implements PrimeNumberService {

    Set<Integer> allPrimes = new HashSet<>();

    @Override
    public Set<Integer> generatePrimeNumbers(Integer limit) {
        //1 is not a prime
        if (limit <= 1) {
            return allPrimes;
        }
        for (int i = 2; i <= limit; i++) {
            boolean isPrimeNumber = true;
            // check to see if the number is prime
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            if (isPrimeNumber) {
                allPrimes.add(i);
            }
        }
        TreeSet<Integer> primeNosOrdered = new TreeSet<>(allPrimes);
        return primeNosOrdered;
    }
}
