package com.icap.service.impl;

import com.icap.service.PrimeNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

/**
 * Created by admin on 10/04/2016.
 */
@Service
public class ThreadedSimpleLoopingPrimeNumberService implements PrimeNumberService {

    protected static final Integer MAX_THREADS = 50;

    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberGen.class);

    @Override
    public Set<Integer> generatePrimeNumbers(Integer limit) {
        HashSet<Integer> allPrimes = new HashSet<Integer>();
        Collections.synchronizedSet(allPrimes);
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i <= limit; i++) {
            Callable<Integer> callable  = new PrimeNumberGen(i);
            Future<Integer> future = executor.submit(callable);

            try {
                if (future.get() != null) {
                    allPrimes.add(future.get());
                }
            } catch (InterruptedException  | ExecutionException e) {
                logger.error("generatePrimeNumbers error", e);
            }
        }
        executor.shutdown();
        TreeSet<Integer> primeNosOrdered = new TreeSet<>(allPrimes);
        return primeNosOrdered;
    }
}
