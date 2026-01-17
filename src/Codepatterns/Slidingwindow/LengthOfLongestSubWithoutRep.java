package Codepatterns.Slidingwindow;

/*
Leetcode 3. Longest Substring Without Repeating Characters
Given a string s, find the length of the longest
substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
 */

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubWithoutRep {

    // Sliding Window + Two Pointers
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            // If duplicate found, shrink window from left
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add current character
            set.add(s.charAt(right));

            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";

        System.out.println(lengthOfLongestSubstring(s1)); // 3
        System.out.println(lengthOfLongestSubstring(s2)); // 1
        System.out.println(lengthOfLongestSubstring(s3)); // 3
    }
}


/*
Another approach using Sliding Window and HashMap
public class LengthOfLongestSubWithoutRep {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int start = 0; // Left boundary of the sliding window

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            // If the character is already in the map, move the start pointer
            if (charIndexMap.containsKey(currentChar)) {
                start = Math.max(charIndexMap.get(currentChar) + 1, start);
            }
            // Update the last seen index of the character
            charIndexMap.put(currentChar, end);
            // Calculate the maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        int result = lengthOfLongestSubstring(input);
        System.out.println("Length of the longest substring without repeating characters: " + result);
    }
}

 */

