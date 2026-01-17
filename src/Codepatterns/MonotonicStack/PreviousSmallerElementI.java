package Codepatterns.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;
/*
Leetcode Problem: Previous Smaller Element I
Given an array of integers, for each element, find the previous smaller element. If there is no previous smaller element, return -1 for that position.
Input: arr = [4, 5, 2, 10, 8]
Output: [-1, 4, -1, 2, 2]
Explanation:
- For 4, there is no previous element, so the result is -1.
- For 5, the previous element is 4, which is smaller than 5.
- For 2, there is no previous smaller element, so the result is -1.
- For 10, the previous smaller element is 2.
- For 8, the previous smaller element is 2.

 */

public class PreviousSmallerElementI {
    public static int[] previousSmaller(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        System.out.println(Arrays.toString(previousSmaller(arr)));
    }
}
//time: O(n)
//space: O(n)
//stack diagram with tracing
/*
Stack:
BOTTOM
┌─────────┐
│ 4       │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 4       │
├─────────┤
│ 5       │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 2       │
└─────────┘
TOP
Stack:
BOTTOM
┌─────────┐
│ 2       │
├─────────┤
│ 10      │
└─────────┘
TOP
Stack:
BOTTOM
┌─────────┐
│ 2       │
├─────────┤
│ 8       │
└─────────┘
TOP










 */






//public class PreviousSmallerElementI {
//
//    public int[] previousSmallerElement(int[] nums1, int[] nums2) {
//        if (nums2.length == 0 || nums1.length == 0)
//            return new int[0];
//
//        Map<Integer, Integer> numberPSE = new HashMap<>();
//        Stack<Integer> numStack = new Stack<>();
//
//        // Iterate through nums2 to find previous smaller elements
//        for (int i = 0; i < nums2.length; i++) {
//            while (!numStack.isEmpty() && numStack.peek() >= nums2[i]) {
//                numStack.pop();
//            }
//
//            // If the stack is empty, there is no previous smaller element
//            if (numStack.isEmpty()) {
//                numberPSE.put(nums2[i], -1);
//            } else {
//                numberPSE.put(nums2[i], numStack.peek());
//            }
//
//            numStack.push(nums2[i]);
//        }
//
//        // Populate the result for nums1 based on the previous smaller elements found
//        for (int i = 0; i < nums1.length; i++) {
//            nums1[i] = numberPSE.get(nums1[i]);
//        }
//
//        return nums1;
//    }
//
//    public static void main(String[] args) {
//        PreviousSmallerElementI solution = new PreviousSmallerElementI();
//
//        // Sample input
//        int[] nums1 = {4, 1, 2};
//        int[] nums2 = {1, 3, 4, 2};
//
//        // Get the result
//        int[] result = solution.previousSmallerElement(nums1, nums2);
//
//        // Print the result
//        System.out.print("Previous Smaller Elements: ");
//        for (int num : result) {
//            System.out.print(num + " ");
//        }
//    }
//}