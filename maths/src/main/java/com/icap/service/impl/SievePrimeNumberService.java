package com.icap.service.impl;

import com.icap.service.PrimeNumberService;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
@Service
public class SievePrimeNumberService implements PrimeNumberService {

    private BitSet sieve;

    @Override
    public Set<Integer> generatePrimeNumbers(Integer limit) {
        sieve = new BitSet((int)(limit+1)/2);
        for (int i = 3; i * i <= limit; i += 2) {
            if (is_composite(i))
                continue;

            // We increment by 2*i to skip even multiples of i
            for (int multiple_i = i * i; multiple_i <= limit; multiple_i += 2 * i)
                set_composite(multiple_i);

        }
        Set<Integer> primes = new HashSet<Integer>();
        primes.add(2);
        for (int i = 3; i <= limit; i += 2)
            if (!is_composite(i))
                primes.add(i);

        return primes;
    }

    private boolean is_composite(int k) {
        assert k >= 3 && (k % 2) == 1;
        return sieve.get((k-3)/2);
    }

    private void set_composite(int k) {
        assert k >= 3 && (k % 2) == 1;
        sieve.set((k-3)/2);
    }
}
