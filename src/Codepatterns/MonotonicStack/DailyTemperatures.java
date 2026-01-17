package Codepatterns.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

/*
A monotonic stack is a stack that keeps its elements in a specific order
— either increasing or decreasing — from top to bottom.

739. Daily Temperatures
Given an array of integers temperatures represents the daily temperatures,
return an array answer such that answer[i] is the number of days you have to
wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.
Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
The stack top always holds the latest day that is still waiting to see a warmer temperature in the future.
 */

/*
Leetcode Problem 739: Daily Temperatures
Given an array of integers temperatures represents the daily temperatures,
return an array answer such that answer[i] is the number of days you have
to wait after the ith day to get a warmer temperature. If there is
no future day for which this is possible, keep answer[i] == 0 instead.
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]


 */
class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() &&
                    temperatures[i] > temperatures[stack.peek()]) {

                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();

        // Sample input
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        // Get the result
        int[] result = dt.dailyTemperatures(temperatures);

        // Print the result
        System.out.println(Arrays.toString(result));
    }
}

/*
Stack:
BOTTOM
┌─────────┐
│ 0 (73)  │
└─────────┘
TOP

BOTTOM
┌─────────┐
│ 1 (74)  │
└─────────┘
TOP


Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
├─────────┤
│ 3 (71)  │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
├─────────┤
│ 3 (71)  │
├─────────┤
│ 4 (69)  │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
├─────────┤
│ 3 (71)  │
└─────────┘
TOP
Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
├─────────┤
│ 5 (72)  │
└─────────┘
TOP


Stack:
BOTTOM
┌─────────┐
│ 2 (75)  │
└─────────┘
TOP
Stack:
BOTTOM
┌─────────┐
│ 6 (76)  │
└─────────┘
TOP

Stack:
BOTTOM
┌─────────┐
│ 6 (76)  │
├─────────┤
│ 7 (73)  │
└─────────┘
TOP
BOTTOM
┌─────────┐
│ 6 (76)  │
├─────────┤
│ 7 (73)  │
└─────────┘
TOP








 */

//public class DailyTemperatures {
//
//    public int[] dailyTemperatures(int[] temperatures) {
//
//        Stack<Integer> helperStack = new Stack<>();
//
//        int n = temperatures.length;
//        int[] result = new int[n];
//
//        for(int idx = n - 1; idx >= 0; idx--) {
//
//            // Popping all indices with a lower or equal
//            // temperature than the current index
//            while(!helperStack.isEmpty()
//                    && temperatures[idx] >= temperatures[helperStack.peek()]) {
//                helperStack.pop();
//            }
//
//            // If the stack still has elements,
//            // then the next warmer temperature exists!
//            if(!helperStack.isEmpty()) {
//                result[idx] = helperStack.peek() - idx;
//            }
//
//            // Inserting current index in the stack
//            helperStack.push(idx);
//        }
//
//        return result;
//    }
//
//    public static void main(String[] args) {
//        DailyTemperatures dt = new DailyTemperatures();
//
//        // Sample input
//        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
//
//        // Get the result
//        int[] result = dt.dailyTemperatures(temperatures);
//
//        // Print the result
//        System.out.println(Arrays.toString(result)); // Output: [1, 1, 4, 2, 1, 1, 0, 0]
//    }
//}
/*
Space and time complexity O(n)
 */