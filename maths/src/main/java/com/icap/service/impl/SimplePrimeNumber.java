package com.icap.service.impl;

import com.icap.service.PrimeNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Loop through all numbers to the given limit.
    For each one of the numbers in the loop create a boolean isPrimeNumber equal to true
    and create another loop where the number is divided to other numbers from 2 up to the number,
    and if the result is zero, then the boolean isPrimeNumber is set to false.
 */
@Service
public class SimplePrimeNumber implements PrimeNumber{

    Set<Integer> allPrimes = new HashSet<>();

    @Override
    public Set<Integer> generatePrimeNumbers(Long limit) {
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
