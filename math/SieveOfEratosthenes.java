package math;

/*

Question:
Given n count the number of prime numbers less than n

Solution:
Sieve of Eratosthenes. Keep a boolean array flagging numbers as prime or not prime. Starting with 2,
when you find a prime number, flag all its multiples as not prime.

*/

public class SieveOfEratosthenes {
  public int countPrimes(int n) {
    boolean[] notPrime = new boolean[n];
    int count = 0;
    for (int i = 2; i < n; i++) {
      // if its a prime, increment count and flag all its multiples as nonprime
      if (!notPrime[i]) {
        count++;
        for (int j = 2; i*j < n; j++) {
          notPrime[i*j] = true;
        }
      }
    }
    return count;
  }
}