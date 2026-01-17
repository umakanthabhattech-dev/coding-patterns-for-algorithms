package Codepatterns.MonotonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Leetcode number: 496
Next Greater Element I
The next greater element of some element x in an array is the
first greater element that is to the right of x in the same array.
You are given two distinct 0-indexed integer arrays nums1 and nums2,
where nums1 is a subset of nums2.
For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
and determine the next greater element of nums2[j] in nums2.
If there is no next greater element, then the answer for this query is -1.
Return an array ans of length nums1.length such that
ans[i] is the next greater element as described above.
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
 */
public class NextGreaterElementI {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // Process nums2 -- find next greater for each element
        //step1-keep find next greater for each element for all elements in nums2
        //monotonic decreasing stack(same as daily temperatures problem)
        //put into map
        //step2-build result for nums1 from map

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        // Remaining elements → no next greater
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }
}
/*
Complexity Analysis
Time: O(n + m) where n is the length of nums2 and m is the length of nums1
Space: O(n) (stack + hashmap)
 */





//public class NextGreaterElementI {
//
//    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//
//        if (nums2.length == 0 || nums1.length == 0)
//            return new int[0];
//
//        Map<Integer, Integer> numberNGE = new HashMap<>();
//        Stack<Integer> numStack = new Stack<>();
//
//        numStack.push(nums2[nums2.length - 1]);
//        numberNGE.put(nums2[nums2.length - 1], -1);
//
//        for (int i = nums2.length - 2; i >= 0; i--) {
//
//            if (nums2[i] < numStack.peek()) {
//                numberNGE.put(nums2[i], numStack.peek());
//                numStack.push(nums2[i]);
//                continue;
//            }
//
//            while (!numStack.isEmpty() && numStack.peek() < nums2[i])
//                numStack.pop();
//
//            if (numStack.isEmpty()) {
//                numStack.push(nums2[i]);
//                numberNGE.put(nums2[i], -1);
//            } else {
//                numberNGE.put(nums2[i], numStack.peek());
//                numStack.push(nums2[i]);
//            }
//        }
//
//        for (int i = 0; i < nums1.length; i++)
//            nums1[i] = numberNGE.get(nums1[i]);
//
//        return nums1;
//    }
//    public static void main(String[] args) {
//        NextGreaterElementI solution = new NextGreaterElementI();
//
//        // Sample input
//        int[] nums1 = {4, 1, 2};
//        int[] nums2 = {1, 3, 4, 2};
//
//        // Get the result
//        int[] result = solution.nextGreaterElement(nums1, nums2);
//
//        // Print the result
//        System.out.print("Next Greater Elements: ");
//        for (int num : result) {
//            System.out.print(num + " ");
//        }
//    }}
/*
Time Complexity: O(m + n)
Space Complexity: O(m)
 */
