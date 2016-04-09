package com.icap.service.impl;

import com.icap.service.PrimeNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
@Service
public class SimplePrimeNumber implements PrimeNumber{

    Set<Integer> allPrimes = new HashSet<>();

    @Override
    public Set<Integer> generatePrimeNumbers(int limit) {
        for (int i = 0; i < limit; i++) {
           if (isPrime(i)) {
              allPrimes.add(i);
           }
        }
        return allPrimes;
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
