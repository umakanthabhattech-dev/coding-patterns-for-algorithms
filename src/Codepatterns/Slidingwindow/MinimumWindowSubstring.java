package Codepatterns.Slidingwindow;

import java.util.HashMap;
import java.util.Map;
/*
Leetcode 76. Minimum Window Substring
Given two strings s and t of lengths m and n respectively,
return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Constraints:
m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.
Logic:

We can use the sliding window technique to solve this problem.
We will maintain a window that contains all characters of t.
We will expand the window by moving the right pointer and
shrink the window by moving the left pointer when we have a valid window.
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        // Frequency map of t
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int required = tMap.size();
        int formed = 0;

        Map<Character, Integer> windowMap = new HashMap<>();

        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            if (tMap.containsKey(c) &&
                    windowMap.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }

            // Try to shrink
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                // Remove from window ?
                // Decrease count beacuse we are moving left pointer ahead
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);

                //why? because if the character at left pointer is part of t
                // and its frequency in the current window is less than required frequency in t
                // then we have one less character fulfilling the requirement
                // so we decrement formed
                if (tMap.containsKey(leftChar) &&
                        windowMap.get(leftChar) < tMap.get(leftChar)) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    // MAIN METHOD
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s, t)); // Output: BANC
    }
}
//time: O(m + n) where m and n are lengths of s and t
//space: O(m + n)
