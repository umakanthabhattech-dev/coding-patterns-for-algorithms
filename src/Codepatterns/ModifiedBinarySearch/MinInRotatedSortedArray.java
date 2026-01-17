package Codepatterns.ModifiedBinarySearch;

/*
153. Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.
Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 */
public class MinInRotatedSortedArray {
    public static int findMin(int []arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            //mathamatically same as low + (high - low) / 2 to prevent overflow

            //search space is already sorted
            //then arr[low] will always bxe
            //the minimum in that search space:
//            if (arr[low] <= arr[high]) {
//                ans = Math.min(ans, arr[low]);
//                break;
//            }

            //if left part is sorted:
            if (arr[low] <= arr[mid]) {
                // keep the minimum:
                ans = Math.min(ans, arr[low]);

                // Eliminate left half:
                low = mid + 1;

            } else { //if right part is sorted:

                // keep the minimum:
                ans = Math.min(ans, arr[mid]);

                // Eliminate right half:
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       // int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        int[] arr =   {5, 6, 7, 0, 1, 2, 3, 4};
        int ans = findMin(arr);
        System.out.println("The minimum element is: " + ans );
    }
}
/*
trace input
5,6,7,0,1,2,3,4
low=0, high=7
mid=3
arr[mid]=0, arr[low]=5
arr[low]<=arr[mid] => false right part sorted
ans=min(inf,0)=0
eliminate right half => high=mid-1=2
low=0, high=2
mid=1
arr[mid]=6, arr[low]=5
arr[low]<=arr[mid] => left part sorted
ans=min(0,5)=0
eliminate left half => low=mid+1=2
low=2, high=2
mid=2
arr[mid]=7, arr[low]=7
arr[low]<=arr[mid] => left part sorted
ans=min(0,7)=0
eliminate left half => low=mid+1=3
low=3, high=2
exit loop
return ans=0
 */

/*
trace input
4,5,6,7,0,1,2,3
low=0, high=7
mid=3
arr[mid]=7, arr[low]=4
arr[low]<=arr[mid] => left part sorted
ans=min(inf,4)=4
eliminate left half => low=mid+1=4
low=4, high=7
mid=5
arr[mid]=1, arr[low]=0
arr[low]<=arr[mid] => left part sorted
ans=min(4,0)=0
eliminate left half => low=mid+1=6
low=6, high=7
mid=6
arr[mid]=2, arr[low]=2
arr[low]<=arr[mid] => left part sorted
ans=min(0,2)=0
eliminate left half => low=mid+1=7
low=7, high=7
mid=7
arr[mid]=3, arr[low]=3
arr[low]<=arr[mid] => left part sorted
ans=min(0,3)=0
eliminate left half => low=mid+1=8
low=8, high=7
exit loop
return ans=0


Time Complexity: O(logN), N = size of the given array.
Reason: We are basically using binary search to find the minimum.

Space Complexity: O(1)
Reason: We have not used any extra data structures, this makes space complexity, even in the worst case as O(1).
 */



//public class MinInRotatedSortedArray {
//
//    public static int findMin(int[] nums) {
//        int low = 0;
//        int high = nums.length - 1;
//
//        while (low < high) {
//            int mid = low + (high - low) / 2;
//
//            if (nums[mid] > nums[high]) {
//                // Minimum is in the right half
//                low = mid + 1;
//            } else {
//                // Minimum is at mid or in the left half
//                high = mid;
//            }
//        }
//        return nums[low]; // or nums[high]
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {4,5,6,7,0,1,2};
//        System.out.println(findMin(nums)); // Output: 0
//    }
//}
/*trace
Time Complexity: O(log N)
Space Complexity: O(1)
trace the input values

 */

// 4,5,6,7,0,1,2
// low=0, high=6
// mid=3
// arr[mid]=7, arr[high]=2
// arr[mid]>arr[high] => low=mid+1=4
// low=4, high=6
// mid=5
// arr[mid]=1, arr[high]=2
// arr[mid]<=arr[high] => high=mid=5
// low=4, high=5
// mid=4
// arr[mid]=0, arr[high]=1
// arr[mid]<=arr[high] => high=mid=4
// low=4, high=4
// exit loop
// return arr[low]=0