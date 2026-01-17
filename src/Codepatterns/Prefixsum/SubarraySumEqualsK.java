package Codepatterns.Prefixsum;

import java.util.HashMap;

/*
leetcode 560:
https://www.youtube.com/watch?v=xvNwoz-ufXA
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2
Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int cumulativeSum = 0;
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1); // Initialize with sum 0 to handle cases where subarray starts from index 0

        for (int num : nums) {
            cumulativeSum += num;

            // Check if (cumulativeSum - k) exists in the map
            if (sumMap.containsKey(cumulativeSum - k)) {
                count += sumMap.get(cumulativeSum - k);
            }

            // Update the map with the current cumulative sum count
            sumMap.put(cumulativeSum, sumMap.getOrDefault(cumulativeSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println("Number of subarrays: " + solution.subarraySum(nums, k)); // Output: 2
    }
}
/*
trace the input: [1,2,3]





 */
/*
Time Complexity:
O(n), where n is the length of the input array, since we only traverse the array once.
Space Complexity: O(n) in the worst case for the hash map, which stores the cumulative sums.

Store cumulativeSum of each index and its count in a map
get how many sum[i] - k are there in hash map and add to the count
finally everytime add current cumulativeSum to the map

 */
/*

import java.util.*;

public class tUf {
    public static int findAllSubarraysWithGivenSum(int arr[], int k) {
        int n = arr.length; // size of the given array.
        Map mpp = new HashMap();
        int preSum = 0, cnt = 0;

        mpp.put(0, 1); // Setting 0 in the map.
        for (int i = 0; i < n; i++) {
            // add current element to prefix Sum:
            preSum += arr[i];

            // Calculate x-k:
            int remove = preSum - k;

            // Add the number of subarrays to be removed:
            cnt += mpp.getOrDefault(remove, 0);

            // Update the count of prefix sum
            // in the map.
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        int k = 6;
        int cnt = findAllSubarraysWithGivenSum(arr, k);
        System.out.println("The number of subarrays is: " + cnt);
    }
}
Time Complexity: O(N) or O(N*logN)
Space O(N)
 */
/*
https://www.youtube.com/watch?v=YxRmeRyVQm4
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // Core method
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // Base case: prefix sum = 0 occurs once
        prefixSumCount.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // Check if (prefixSum - k) exists
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }

            // Update frequency of current prefixSum
            prefixSumCount.put(
                prefixSum,
                prefixSumCount.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println(subarraySum(nums1, k1)); // Output: 2

        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println(subarraySum(nums2, k2)); // Output: 2

        int[] nums3 = {2, 3, -5, 5, -5, 1, 4};
        int k3 = 5;
        System.out.println(subarraySum(nums3, k3)); // Output: 6
    }
}

 */
