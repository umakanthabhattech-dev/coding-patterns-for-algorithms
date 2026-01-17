package Codepatterns.Slidingwindow;

/*
Leetcode 992. Subarrays with K Different Integers
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.
Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers:
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,3]
Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers:
[1,2,1,3], [2,1,3], [1,3,4]
Constraints:
1 <= nums.length <= 2 * 10^4
1 <= nums[i] <= nums.length
1 <= k <= nums.length

intuition:
We can use the sliding window technique to solve this problem.
We will maintain two sliding windows:
1. One for at most k distinct integers.
2. One for at most k-1 distinct integers.
The difference between the counts of these two windows will give
us the count of subarrays with exactly k distinct integers.
 */

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDistinct {

    // Function to count subarrays with at most K distinct integers
    public static int atMostK(int[] nums, int k) {
        if (k < 0) return 0;

        // Frequency map to store counts of elements in the current window
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            // Add current elemet
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // Shrink window if distinct elements exceed k
            while (freqMap.size() > k) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }

            // All subarrays ending at 'right' are valid
            //why? because from left to right, all subarrays will have at most k distinct integers
            //equation works because for every right pointer,
            // the number of valid subarrays is (right - left + 1)
            count += right - left + 1;
        }

        return count;
    }

    // Function to count subarrays with exactly K distinct integers
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // MAIN METHOD
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 4};
        int k = 3;

        int result = subarraysWithKDistinct(nums, k);
        System.out.println("Subarrays with exactly " + k + " distinct integers: " + result);
    }
}
/*youtube : https://www.youtube.com/watch?v=7wYGbV_LsX4
//time: O(n)
//space: O(n)
 */