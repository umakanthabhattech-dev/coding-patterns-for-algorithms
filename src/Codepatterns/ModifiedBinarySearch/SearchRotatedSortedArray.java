package Codepatterns.ModifiedBinarySearch;

/*
Leetcode 33. Search in Rotated Sorted Array
There is an integer array nums sorted in ascending order (with "distinct values").
Prior to being passed to your function, nums is possibly rotated at an unknown
pivot index k (1 <= k < nums.length) such that the resulting array is
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
 */
public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(left <= right) {
            System.out.println("left = " + left + ", right = " + right);
            int mid = (left + right) /2;
           // int mid = left + (right - left) / 2; can use if left and right numbers are very large
            // because (left + right) leads to overflow
            //left + (right - left) / 2 = (2left + (right - left))/2=> (left+right)/2
            if (target == nums[mid])
                return mid; // Target found

            //check sorted sub array whether it is from right or left side
            if (nums[left] <= nums[mid]) { //left side is sorted
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1; //Target is in the left side
                }  else {
                    left = mid + 1; //Target is in the right side
                }
            } else {//Right side is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String args[]) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        SearchRotatedSortedArray rotated = new SearchRotatedSortedArray();
        int found = rotated.search(nums, target);

        if (-1 == found) {
            System.out.println("Target not found");
        } else {
            System.out.println("Target found at " + found);
        }
    }
}
/*
Time Complexity: O(log n)
Space Complexity: O(1)
 */
/*
logic

 Left side check for target
 start->target->mid
  if(a[mid] > target)
    end = mid-1


  Right side check for target
  mid->target->end
  if(a[mid] < target)
    start = mid+1

But if each side is not sorted then above wont work so put else check also

//check left sorted
if (a[low] <= a[mid])
    then Left side check for target
         start->target->mid
        if(a[mid] > target && a[start]<=target)
         end = mid-1
        else
        start = mid+1
 same for right side

 */