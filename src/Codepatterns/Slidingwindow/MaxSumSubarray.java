package Codepatterns.Slidingwindow;

/*

Find the maximum sum of a subarray of size k.
Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k
Example:
Input: nums = [2, 1, 5, 1, 3, 2], k = 3
Output: 9
 */
public class MaxSumSubarray {
    public static int maxSum(int[] arr, int k) {
        int n = arr.length;
        if (n < k) {
            throw new IllegalArgumentException("Array size must be greater than or equal to k");
        }

        // Calculate the sum of the first 'k' elements
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }

        // Initialize current sum as maxSum
        int currentSum = maxSum;

        // Slide the window from start to end of the array
        for (int i = k; i < n; i++) {
            // Add next element and remove the first element of the previous window
            currentSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, currentSum); // Update maxSum if currentSum is greater
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 400};
        int k = 2;
        System.out.println("Maximum sum of a subarray of size " + k + " is: " + maxSum(arr, k));
    }
}
/*time: O(n)
space: O(1)

 */