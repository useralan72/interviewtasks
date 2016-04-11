package com.icap.controller;

import com.icap.model.PrimeNumbersDTO;
import com.icap.service.PrimeNumberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import java.util.Set;

/**
 * Created by admin on 09/04/2016.
 */
@RestController
@Validated
@RequestMapping(value = "/primeNumbers", method = RequestMethod.GET)
public class PrimeNumbersController {

    @Autowired
    @Qualifier("simpleLoopingPrimeNumberService")
    protected PrimeNumberService simpleLoopingPrimeNumberService;

    @Autowired
    @Qualifier("threadedSimpleLoopingPrimeNumberService")
    protected PrimeNumberService threadedSimpleLoopingPrimeNumberService;

    @Autowired
    @Qualifier("sievePrimeNumberService")
    protected PrimeNumberService sievePrimeNumberService;

    @RequestMapping(value = "/simpleLoopingAlgorithm/{limit}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "simpleLoopingAlgorithm", notes = "This is a simple non threaded algorithm so limit is set to 100000 for performance reasons")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "long", paramType = "path")
    })
    public PrimeNumbersDTO getAllSimpleLoop(@Max(100000) @PathVariable("limit") Integer limit) {
        long startTime = System.currentTimeMillis();
        Set<Integer> allPrimes = simpleLoopingPrimeNumberService.generatePrimeNumbers(limit);
        long finishTime = System.currentTimeMillis();
        return generateDTO(allPrimes, finishTime - startTime);
    }

    @RequestMapping(value = "/threadedSimpleLoopingAlgorithm/{limit}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "threadedSimpleLoopingAlgorithm", notes = "This is a simple threaded algorithm so limit is set to 100000 for performance reasons")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "long", paramType = "path")
    })
    public PrimeNumbersDTO getAllMultithreadedSimpleLoop(@Max(100000) @PathVariable("limit") Integer limit) {
        long startTime = System.currentTimeMillis();
        Set<Integer> allPrimes = threadedSimpleLoopingPrimeNumberService.generatePrimeNumbers(limit);
        long finishTime = System.currentTimeMillis();
        return generateDTO(allPrimes, finishTime - startTime);
    }

    @RequestMapping(value = "/sieveAlgorithm/{limit}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "sieveAlgorithm", notes = "This uses the Sieve of Eratosthenes algorithm")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "long", paramType = "path")
    })
    public PrimeNumbersDTO getAllSieveAlgorithm(@PathVariable("limit") Integer limit) {
        long startTime = System.currentTimeMillis();
        Set<Integer> allPrimes = sievePrimeNumberService.generatePrimeNumbers(limit);
        long finishTime = System.currentTimeMillis();
        return generateDTO(allPrimes, finishTime - startTime);
    }

    private PrimeNumbersDTO generateDTO(Set<Integer> primeNos, long totalTime) {
        PrimeNumbersDTO primeNumbersDTO = new PrimeNumbersDTO();
        primeNumbersDTO.setPrimeNumbers(primeNos);
        primeNumbersDTO.setNoOfPrimeNumbers(primeNos.size());
        primeNumbersDTO.setProcessTime(totalTime);
        return primeNumbersDTO;
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return strBuilder.toString();
    }

}
