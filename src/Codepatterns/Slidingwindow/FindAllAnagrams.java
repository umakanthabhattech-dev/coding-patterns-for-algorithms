package Codepatterns.Slidingwindow;

/*
Leetcode 438. Find All Anagrams in a String
Given two strings s and p, return an array of all the start indices of p's anagrams in s.
You may return the answer in any order.
Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Constraints:
1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.


 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (p.length() > s.length()) return result;

        int[] freqP = new int[26];
        int[] freqWindow = new int[26];

        // 1️⃣ Build frequency for p
        for (char c : p.toCharArray()) {
            freqP[c - 'a']++;
        }

        int windowSize = p.length();

        // 2️⃣ Build frequency for first window in s
        for (int i = 0; i < windowSize; i++) {
            freqWindow[s.charAt(i) - 'a']++;
        }

        // 3️⃣ Compare first window
        if (Arrays.equals(freqP, freqWindow)) {
            result.add(0);
        }

        // 4️⃣ Slide the window
        for (int i = windowSize; i < s.length(); i++) {
            // add new char (right side)
            freqWindow[s.charAt(i) - 'a']++;

            // remove old char (left side)
            freqWindow[s.charAt(i - windowSize) - 'a']--;

            // compare frequencies
            if (Arrays.equals(freqP, freqWindow)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> ans = findAnagrams(s, p);
        System.out.println(ans);  // Output: [0, 6]
    }
}
/*
Time Complexity: O(n * 26) => O(n), where n is the length of string s.

 */

/*another solution
import java.util.*;

public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        // Frequency arrays for p and current window in s
        int[] freqP = new int[26];
        int[] freqS = new int[26];

        // Build frequency map for string p
        for (char c : p.toCharArray()) {
            freqP[c - 'a']++;
        }

        int windowSize = p.length();

        // Sliding window over string s
        for (int i = 0; i < s.length(); i++) {
            // Add current character to window
            freqS[s.charAt(i) - 'a']++;

            // Remove character that goes out of window
            if (i >= windowSize) {
                freqS[s.charAt(i - windowSize) - 'a']--;
            }

            // Compare frequency arrays
            if (Arrays.equals(freqP, freqS)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(findAnagrams(s1, p1)); // Output: [0, 6]

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(findAnagrams(s2, p2)); // Output: [0, 1, 2]
    }
}
*/



