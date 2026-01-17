package Codepatterns.Prefixsum;

import java.util.HashMap;

/*
leetcode 525:

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, -1); // Initialize with sum 0 at index -1
        int maxLength = 0;
        int sum = 0; // This will count the number of 1s - number of 0s

        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 1) ? 1 : -1; // Increment for 1, decrement for 0
            System.out.println("Index: " + i + ", Num: " + nums[i] + ", Cumulative Sum: " + sum);
            if (countMap.containsKey(sum)) {
                System.out.println("  Found previous sum at index: " + countMap.get(sum));
                maxLength = Math.max(maxLength, i - countMap.get(sum));
            } else {
                System.out.println("  Storing sum " + sum + " at index: " + i);
                countMap.put(sum, i); // Store the first occurrence of this count
            }
            System.out.println("current countMap: " + countMap + ", maxLength: " + maxLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 1, 0}; // Example input
        int result = findMaxLength(nums);
        System.out.println("Maximum length of contiguous subarray: " + result);
    }
}
/* youtube explanation:https://www.youtube.com/watch?v=NUQ5Ob_xUvg
/*
trace the input:
Index: 0, Num: 0, Cumulative Sum: -1
  Storing sum -1 at index: 0
current countMap: {0=-1, -1=0}, maxLength: 0
Index: 1, Num: 1, Cumulative Sum: 0
  Found previous sum at index: -1
current countMap: {0=-1, -1=0}, maxLength: 2
Index: 2, Num: 0, Cumulative Sum: -1
  Found previous sum at index: 0
current countMap: {0=-1, -1=0}, maxLength: 2
Index: 3, Num: 1, Cumulative Sum: 0
  Found previous sum at index: -1
current countMap: {0=-1, -1=0}, maxLength: 4
Index: 4, Num: 1, Cumulative Sum: 1
    Storing sum 1 at index: 4
current countMap: {0=-1, 1=4, -1=0}, maxLength: 4
Index: 5, Num: 0, Cumulative Sum: 0
  Found previous sum at index: -1
current countMap: {0=-1, 1=4, -1=0}, maxLength: 6
Maximum length of contiguous subarray: 6

 */
/*

Time Complexity: O(n), where n is the length of the input array, as we traverse the array once.
Space Complexity: O(n) in the worst case for the hashmap that stores the cumulative sums.
 */
/*
approach:
set -1 to all 0 : during count we add -1 if 0 comes else add 1
maintain a hashmap cumulative count at each index: count all which comes before the ith index with ith value
Check for Previous Counts: For each cumulative count, we check if it has been seen before.
If it has, we calculate the length of the subarray and update maxLength if this new length is greater.
index  -1  0   1  2  3  4  5
array  { 0  0,  1, 0, 1, 1, 0}
change   -1 -1  1  -1 1   1 -1
count { 0- 1,
 */