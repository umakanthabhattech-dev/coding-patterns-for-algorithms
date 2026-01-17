package Codepatterns.OverlappingIntervals;
/*
Leetcode Problem 57: Insert Interval
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
You are also given an interval newInterval = [start, end] that represents the start and end of
another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.


Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;

        // Add all intervals before the newInterval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals
        //current start must not be greater than newInterval end
        //(3,5) and (2,3) are overlapping
        //(3,5) and (6,8) are not overlapping
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval); // Add the merged interval

        // Add all remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval ii = new InsertInterval();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] merged = ii.insert(intervals, newInterval);

        System.out.println(Arrays.deepToString(merged));
    }
}
/*
Time Complexity: O(n)
Space Complexity: O(n)
 */
/*
import java.util.*;

public class InsertInterval {

    // Optimal O(n) solution
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1️⃣ Add all intervals that end BEFORE newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2️⃣ Merge all OVERLAPPING intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add merged interval
        result.add(newInterval);

        // 3️⃣ Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // 🔹 MAIN METHOD (for testing)
    public static void main(String[] args) {

        int[][] intervals = {
            {1, 2},
            {3, 5},
            {6, 7},
            {8, 10},
            {12, 14}
        };

        int[] newInterval = {4, 8};

        int[][] result = insert(intervals, newInterval);

        // Print result
        System.out.println("Result intervals:");
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

 */