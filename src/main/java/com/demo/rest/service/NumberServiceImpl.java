package com.demo.rest.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberServiceImpl implements NumberService {

    /**
     * This method takes in a number N and generates a List containing N fibonacci series
     *
     * @param maxCount
     * @return list containing the N fibonacci series
     */

    @Override
    public List<Long> getNFibonnacci(final int maxCount) {
        List<Long> fibonacciList = new ArrayList<>();

        if(maxCount <=0){
            return fibonacciList;
        } else if(maxCount==1){
            fibonacciList.add(0L);
            return fibonacciList;
        }

        long a = 0;
        long b = 1;
        fibonacciList.add(a);
        fibonacciList.add(b);

        int count=2;
        fib(a,b,fibonacciList,count, maxCount);
        return fibonacciList;
    }

    /**
     * Method that uses the recursive function to generate and populate the fibonacci series
     * @param a the initial seed value
     * @param b the initial 2nd seed value
     * @param ints reference to the ArrayList containing the series
     * @param count the existing value of count which increments for every recursion
     * @param maxCount the maximum number of fibonacci elements to be generated and populated
     */
    private void fib(long a, long b, List<Long> ints,int count, final int maxCount){
        if(count==maxCount){
            return;
        }
        long c = a+b;
        ints.add(c);
        count++;
        fib(b,c,ints,count,maxCount);
    }
}
