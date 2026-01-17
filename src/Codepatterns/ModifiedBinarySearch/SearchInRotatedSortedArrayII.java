package Codepatterns.ModifiedBinarySearch;

/*
Leetcode 81. Search in Rotated Sorted Array II
There is an integer array nums sorted in non-decreasing order ("not necessarily with distinct values").
Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
Given the array nums after the rotation and an integer target, return true if target is in nums,
or false if it is not in nums.
You must decrease the overall operation steps as much as possible.
Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Solution :It can not use rotated sorted array I solution as it fails for below case
[3,1,2,3,3,3,3] here mid is at index 3 whose value is 3
as a[low]=a[mid]=a[high] its not possible to check left or right part are sorted
So we shrink the array by 1
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true; // Target found
            }

            // Handle duplicates shrink the array
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            }
            // Left side is sorted
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target is in the left sorted part
                } else {
                    left = mid + 1; // Target is in the right part
                }
            }
            // Right side is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target is in the right sorted part
                } else {
                    right = mid - 1; // Target is in the left part
                }
            }
        }
        return false; // Target not found
    }
}
/*
Time complexity
average O(log n)
worst-case time complexity can degrade to O(n) : if shrink allmost all items we may go to n/2

Space Complexity:  O(1)
 */