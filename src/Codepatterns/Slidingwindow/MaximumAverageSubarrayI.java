package Codepatterns.Slidingwindow;
/*
Leetcode 643. Maximum Average Subarray I
You are given an integer array nums consisting of n elements, and an integer k.
Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
Any answer with a calculation error less than 10-5 will be accepted.
Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
Logic is similar to max sum subarray of size k
if sum is maximum then average will also be maximum
 */
public class MaximumAverageSubarrayI {
    public static double findMaxAverage(int[] nums, int k) {
        // Calculate the sum of the first 'k' elements
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        double maxSum = currentSum;

        // Slide the window over the array
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k]; // Add next element and remove the first element of the previous window
            maxSum = Math.max(maxSum, currentSum); // Update maxSum if currentSum is greater
        }

        return maxSum / k; // Return the maximum average
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        double result = findMaxAverage(nums, k);
        System.out.println(result); // Output: 12.75
    }
}
/*
Time Complexity: O(n)
Space Complexity: O(1)
 */
/*
package leetcode.easy;

public class MaximumAverageSubarrayI {

  double findMaxAverage(int[] nums, int k) {

    // Get sum for starting window
    int sum = 0;
    for (int i = 0; i < k; i++)
      sum += nums[i];

    int maxSum = sum;

    // Start sliding window
    int startIndex = 0;
    int endIndex = k;
    while (endIndex < nums.length) {

      sum -= nums[startIndex]; // Remove previous element
      startIndex++;

      sum += nums[endIndex]; // Add next element
      endIndex++;

      maxSum = Math.max(maxSum, sum); // Update max sum
    }

    // Return the average
    return (double) maxSum / k;
  }
}
 */
