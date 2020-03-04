package ru.barancev.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeNumbersTests {

    @Test
    public void testPrime(){
        Assert.assertTrue(PrimeNumbers.isPrime(Integer.MAX_VALUE));
    }


    @Test
    public void testNonPrime(){
        Assert.assertFalse(PrimeNumbers.isPrime(Integer.MAX_VALUE-2));
    }

    @Test(enabled = false)
    public void testPrimeLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(PrimeNumbers.isPrime(n));
    }

    @Test
    public void testPrimeFast(){
        Assert.assertTrue(PrimeNumbers.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeSuperFast(){
        Assert.assertTrue(PrimeNumbers.isPrimeSuperFast(Integer.MAX_VALUE));
    }
}
