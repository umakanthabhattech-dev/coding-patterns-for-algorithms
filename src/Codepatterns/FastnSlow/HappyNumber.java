package Codepatterns.FastnSlow;

import java.util.HashSet;
import java.util.Set;

/*
Leetcode 202.
Write an algorithm to determine if a number n is happy.
A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
1 <= n <= 231 - 1
2147483648-1
2pow2+1pow2+..=275
so max 999
 */
public class HappyNumber {
    boolean isHappy(int n) {
        Set<Integer> used = new HashSet<>();
        while(true) {
            int sum = 0;
            while(n != 0) {
                sum += Math.pow(n % 10, 2.0);
                n = n / 10;
            }
            if (sum == 1) return true;

            n = sum;
            if (used.contains(n)) {
                return false;
            } else {
                used.add(n);
            }
        }
    }

    public static void main(String args[]) {
        HappyNumber happy = new HappyNumber();
        boolean isHappy = happy.isHappy(61);
        System.out.println("Is number is a happy number " + isHappy);
    }
}
/*
As max number could be 999
Time complexity O(1)
Space complexity O(1)

If constriant is not given then for large number case
Time complexity O(n)
Space complexity O(n)
 */