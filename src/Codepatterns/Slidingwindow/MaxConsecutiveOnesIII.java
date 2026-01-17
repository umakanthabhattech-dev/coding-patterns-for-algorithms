package Codepatterns.Slidingwindow;

/*
1004. Max Consecutive Ones III
Given a binary array nums and an integer k,
return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,"1",1,1,1,1,"1"]
Bolded(quoted) numbers were flipped from 0 to 1. The longest subarray is underlined.
Intuition:
We can use a sliding window approach to solve this problem.
We will maintain a window that contains at most k zeros.
We will expand the window by moving the right pointer and
shrink the window by moving the left pointer when we exceed k zeros.
 */
public class MaxConsecutiveOnesIII {
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;

        for (int right = 0; right < nums.length; right++) {
            // If we see a zero, we "use" one flip
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If we exceed allowed flips, shrink window
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }


            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 0, 1, 1, 1, 0, 1, 1};
        int k = 1;
        int result = longestOnes(nums, k);
        System.out.println("The maximum length of consecutive 1's after flipping at most " + k + " zero(s) is: " + result);
    }
}
/*
Time: O(n)
Space: O(1)
YOUTUBE :https://www.youtube.com/watch?v=vLsRew-ABVs
 */

/*
Same as above with comments
public class MaxConsecutiveOnesIII {

    // Sliding Window method
    public static int longestOnes(int[] nums, int k) {
        int start = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int end = 0; end < nums.length; end++) {

            // If we see a zero, we "use" one flip
            if (nums[end] == 0) {
                zeroCount++;
            }

            // If we exceed allowed flips, shrink window
            while (zeroCount > k) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }

            // Update maximum window size
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        int[] nums1 = {1,1,1,0,0,0,1,1,1,1,0};
        int k1 = 2;
        System.out.println(longestOnes(nums1, k1)); // Output: 6

        int[] nums2 = {0,0,1,1,1,0,0};
        int k2 = 2;
        System.out.println(longestOnes(nums2, k2)); // Output: 5
    }
}

 */
