package ru.barancev.testing;

public class PrimeNumbers {


// for cycle
    public static boolean isPrime(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

// while cycle
    public static boolean isPrimeWhileIf(int n){
        int i = 2;
        while (i < n) {
            if (n % i == 0) {
                return false;
            }
        i++;
        }
        return true;
    }

    // while cycle w/o if
    public static boolean isPrimeWhile(int n){
        int i = 2;
        while (i < n && n % i != 0) {
            i++;
        }
        return i == n;
    }

    // for cycle
    public static boolean isPrime(long n){
        for (long i = 2; i < n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // for cycle fast
    public static boolean isPrimeFast(int n){
        for (int i = 2; i < n/2; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // for cycle superfast
    public static boolean isPrimeSuperFast(int n){
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
