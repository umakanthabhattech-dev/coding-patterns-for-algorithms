package Codepatterns.FastnSlow;

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
/*
Using Fast and slow
 */
public class HappyNumberFastnSlow {
    public boolean ishappy(int n) {
        int slow = n;
        int fast = getDigitSum(n);
        while(fast != 1 && slow != fast) {
            slow = getDigitSum(slow); // Move slow pointer one step
            fast = getDigitSum(getDigitSum(fast)); // Move fast pointer two steps
        }
        return fast == 1; // If fast reaches 1, then n is a happy number
    }

    private int getDigitSum(int n) {
        int digitSum = 0;
        while (n != 0) {
            digitSum += Math.pow(n%10, 2.0);
            n /= 10;
        }
        return digitSum;
    }
    public static void main(String args[]) {
        HappyNumberFastnSlow happy = new HappyNumberFastnSlow();
        boolean isHappy = happy.ishappy(82);
        System.out.println("Is number is a happy number " + isHappy);
    }
}
/*
Useful in large number
Time Complexity: O(n), similar to the HashSet approach,
as it also processes each number in the sequence until it either finds a cycle or reaches 1.
Space Complexity: O(1), since it only uses a fixed amount of space for the two pointers and
does not require additional storage for previously seen numbers.
 */

