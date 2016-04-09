package com.icap.controller;

import com.icap.model.PrimeNumbersDTO;
import com.icap.service.PrimeNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
@RestController
public class PrimeNumbersController {

    @Autowired
    protected PrimeNumber simplePrimeNumber;

    @RequestMapping(name = "/simplePrimeNoGenerator/{limit}", method = RequestMethod.GET)
    @ResponseBody
    public PrimeNumbersDTO getAll(@PathVariable("limit") Long limit) {
        long startTime = System.currentTimeMillis();
        Set<Integer> allPrimes = simplePrimeNumber.generatePrimeNumbers(limit);
        long finishTime = System.currentTimeMillis();
        return generateDTO(allPrimes, finishTime - startTime);
    }

    private PrimeNumbersDTO generateDTO(Set<Integer> primeNos, long totalTime) {
        PrimeNumbersDTO primeNumbersDTO = new PrimeNumbersDTO();
        primeNumbersDTO.setPrimeNumbers(primeNos);
        primeNumbersDTO.setNoOfPrimeNumbers(primeNos.size());
        return primeNumbersDTO;
    }

}
