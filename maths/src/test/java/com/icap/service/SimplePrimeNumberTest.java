package com.icap.service;

import com.icap.service.impl.SimplePrimeNumber;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertNotNull;

/**
 * Created by admin on 09/04/2016.
 */
public class SimplePrimeNumberTest {

    PrimeNumber primeNumber;

    private static final Logger logger = LoggerFactory.getLogger(SimplePrimeNumberTest.class);

    @Before
    public void setUp() {
        primeNumber = new SimplePrimeNumber();
    }

    @Test
    public void givenLimitOf100ShouldHaveAllPrimeNumbers() {
        //GIVEN
        int limit = 100;

        //WHEN
        Set<Integer> primeNos = primeNumber.generatePrimeNumbers(limit);
        TreeSet<Integer> primeNosOrdered = new TreeSet<>(primeNos);

        //THEN
        assertNotNull(primeNosOrdered);
        primeNos.forEach(System.out::println);
    }
}
