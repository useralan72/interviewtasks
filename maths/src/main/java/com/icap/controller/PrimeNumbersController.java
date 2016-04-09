package com.icap.controller;

import com.icap.service.PrimeNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
@RestController
public class PrimeNumbersController {

    @Autowired
    protected PrimeNumber simplePrimeNumber;

    @RequestMapping(name = "/simplePrimeNoGenerator", method = RequestMethod.GET)
    public Set<Integer> getAll() {
        return simplePrimeNumber.generatePrimeNumbers(100);
    }

}
