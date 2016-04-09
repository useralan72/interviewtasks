package com.clearscore.art;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 */
public class ConcurrencyUtils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ConcurrencyUtils.class);

    public static int runConcurrently(final Callable<Integer> runnable, int threadCount) {
        final CountDownLatch startSignal = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            Future<Integer> f= executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        startSignal.await();
                        LOGGER.debug("Started {}", runnable.getClass().toString());
                        return runnable.call();
                    } finally {
                        LOGGER.debug("Finished {}", runnable);
                    }
                }
            });
            futures.add(f);
        }

        LOGGER.debug("Unleashing {} threads", threadCount);
        startSignal.countDown();

        int errors = 0;
        for (Future<Integer> future : futures) {
            try {
                Integer delta = future.get();
                errors += delta != null? delta:0;
            } catch (InterruptedException | ExecutionException e) {
                errors++;
                LOGGER.error("", e);
            }
        }

        executorService.shutdownNow();

        LOGGER.debug("All threads finished with {} total errors", errors);

        return errors;
    }
}
