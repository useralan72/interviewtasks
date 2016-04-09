package com.icap.service.impl;

import com.icap.service.PrimeNumberService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *  Simple algorithm
 */
@Service
public class SimplePrimeNumberService implements PrimeNumberService {

    Set<Integer> allPrimes = new HashSet<>();

    @Override
    public Set<Integer> generatePrimeNumbers(Integer limit) {
        for (int i = 0; i < limit; i++) {
           if (isPrime(i)) {
              allPrimes.add(i);
           }
        }
        TreeSet<Integer> primeNosOrdered = new TreeSet<>(allPrimes);
        return primeNosOrdered;
    };

    private boolean isPrime(final int number) {
        if (number == 1) {
            return false;
        }
        if (number % 2 == 0 && number != 2 || number % 3 == 0 && number != 3) {
            return false;
        }
        int limit = (int) ((Math.pow(number, 0.5) + 1) / 6.0 + 1);
        for (int i = 1; i < limit; i++) {
            if(number % (6 * i - 1) == 0){
                return false;
            }
            if(number % (6 * i + 1) == 0){
                return false;
            }
        }
        return true;
    }
}
