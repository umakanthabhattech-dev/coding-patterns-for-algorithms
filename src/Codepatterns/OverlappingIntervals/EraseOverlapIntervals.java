package Codepatterns.OverlappingIntervals;

/*
435. Non-overlapping Intervals
Given an array of intervals intervals where intervals[i] = [starti, endi],
return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 */
import java.util.Arrays;

public class EraseOverlapIntervals {
    public int eraseOverlaps(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort the intervals based on their end points
        //why? To maximize the number of non-overlapping intervals,
        // we should always choose the interval that ends the earliest.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        //output after sorting by end time: [[1,2],[2,3],[1,3],[3,4]]
        int count = 0; // Count of intervals to remove
        int end = intervals[0][1]; // End of the last added interval

        for (int i = 1; i < intervals.length; i++) {
            // If the current interval starts before the last added interval ends, we have an overlap
            if (intervals[i][0] < end) {
                count++; // Increment the count of intervals to remove
            } else {
                // No overlap, update the end to the current interval's end
                end = intervals[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        EraseOverlapIntervals solution = new EraseOverlapIntervals();

        // Example intervals
        int[][] intervals = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        };

        // Calculate the number of intervals to remove
        int result = solution.eraseOverlaps(intervals);

        // Output the result
        System.out.println("Minimum number of intervals to remove: " + result);
    }
}
/*
Time O(n log n) for sorting + O(n) for iteration = O(n log n).
Space Complexity: O(1) (for in-place sorting) or O(n) (if considering the input storage).
 */
/*
Another solution youtube video:
https://www.youtube.com/watch?v=XsrJgwGlRoc&t=137s

import java.util.*;

public class NonOverlappingIntervals {

    // LeetCode 435 solution method
    public static int eraseOverlapIntervals(int[][] intervals) {
        // Edge case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 1. Sort intervals by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 1;                 // number of intervals we keep -DIFF than above
        int prevEnd = intervals[0][1]; // end time of last kept interval

        // 2. Traverse remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                // no overlap → keep interval
                count++;
                prevEnd = intervals[i][1];
            }
            // else → overlap → skip (remove this interval)
        }

        // 3. Removed intervals = total - kept
        return intervals.length - count;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // Test Case 1
        int[][] intervals1 = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 3}
        };

        // Test Case 2
        int[][] intervals2 = {
            {1, 2},
            {1, 2},
            {1, 2}
        };

        // Test Case 3
        int[][] intervals3 = {
            {1, 2},
            {2, 3}
        };

        System.out.println("Output 1: " + eraseOverlapIntervals(intervals1)); // 1
        System.out.println("Output 2: " + eraseOverlapIntervals(intervals2)); // 2
        System.out.println("Output 3: " + eraseOverlapIntervals(intervals3)); // 0
    }
}

 */