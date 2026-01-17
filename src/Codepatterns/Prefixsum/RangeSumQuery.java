package Codepatterns.Prefixsum;

/*
Leetcode
303. Range Sum Query - Immutable
Given an integer array nums, handle multiple queries of the following type:
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:
NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
(i.e. nums[left] + nums[left + 1] + ... + nums[right]).
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]
 */
class RangeSumQuery {

    private int[] prefix;

    // Constructor: build prefix sum array
    public RangeSumQuery(int[] nums) {
        int n = nums.length;
        prefix = new int[n];

        if (n == 0) return;

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        //prefix array got built here for input nums
        // Example:        nums =   [2, 3, 5, 2, -5, 1, 4]
        //index:                     0  1  2  3   4  5  6
        // is converted to prefix = [2, 5, 10, 12, 7, 8, 12]
    }

    // Range sum query [left, right]
    public int sumRange(int left, int right) {
        if (left == 0) {
            return prefix[right];
        }
        return prefix[right] - prefix[left - 1];
    }

    // ✅ MAIN METHOD INSIDE NumArray
    public static void main(String[] args) {

        int[] nums = {2, 3, 5, 2, -5, 1, 4};

        RangeSumQuery obj = new RangeSumQuery(nums);

        System.out.println(obj.sumRange(0, 2)); // 2+3+5 = 10
        System.out.println(obj.sumRange(2, 4)); // 5+2-5 = 2
        System.out.println(obj.sumRange(3, 6)); // 2-5+1+4 = 2
    }
}
/*
Complexity (interview ready)

Preprocessing: O(n)
Each query: O(1)
Space: O(n)
 */

//class RangeSumQuery {
//    int[] arrayNums;
//    int[] sumDP;
//    public RangeSumQuery(int[] nums) {
//        this.arrayNums = new int[nums.length];
//        this.sumDP = new int[nums.length];
//        this.sumDP[0] = nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            this.arrayNums[i] = nums[i];
//            if (i > 0) {
//                this.sumDP[i] = this.sumDP[i - 1] + nums[i];
//            }
//        }
//    }
//
//    public int sumRange(int left, int right) {
//        if (left > 0) {
//            return (this.sumDP[right] - this.sumDP[left - 1]);
//        }
//        return this.sumDP[right];
//    }
//
//    public static void main(String[] args) {
//        // Example usage
//        int[] nums = {1, 3, 5};
//        RangeSumQuery numArr = new RangeSumQuery(nums);
//        // Test sumRange method
//        System.out.println(numArr.sumRange(0, 2)); // Output: 9 (1 + 3 + 5)
//        System.out.println(numArr.sumRange(0, 1)); // Output: 4 (1 + 3)
//        System.out.println(numArr.sumRange(1, 2)); // Output: 8 (3 + 5)
//    }
//}