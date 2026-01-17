package Codepatterns.OverlappingIntervals;

/*
leetcode 56: Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover
all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

 */

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {

    // MERGE METHOD (using your logic)
    public static List<Interval> merge(List<Interval> allIntervals) {

        // Edge case
        if (allIntervals == null || allIntervals.size() == 0)
            return new ArrayList<>();

        // 1. Sort by start time
        allIntervals.sort((a, b) -> a.start - b.start);

        List<Interval> merged = new ArrayList<>();

        Interval prev = allIntervals.get(0);

        for (int i = 1; i < allIntervals.size(); i++) {
            Interval curr = allIntervals.get(i);

            if (prev.end < curr.start) {
                // NO overlap → add previous interval
                merged.add(prev);
                prev = curr;
            } else {
                // Overlap → merge
                prev.end = Math.max(prev.end, curr.end);
            }
        }

        // Add the last interval
        merged.add(prev);

        return merged;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(2, 6));
        input.add(new Interval(8, 10));
        input.add(new Interval(15, 18));

        List<Interval> result = merge(input);

        System.out.println("Merged Intervals:");
        for (Interval in : result) {
            System.out.println("[" + in.start + ", " + in.end + "]");
        }
    }
}



















//import java.util.Arrays;
//import java.util.LinkedList;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//
//public class MergeIntervals {
//    public int[][] merge(int[][] intervals) {
//        // Sort the intervals based on the starting times
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
//
//        LinkedList<int[]> merged = new LinkedList<>();
//
//        for (int[] interval : intervals) {
//            // If the list of merged intervals is empty or if the current interval does not overlap with the previous one
//            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
//                merged.add(interval);
//            } else {
//                // There is an overlap, so we merge the current interval with the last one
//                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
//            }
//        }
//
//        return merged.toArray(new int[merged.size()][]);
//    }
//
//    public static void main(String[] args) {
//        MergeIntervals mi = new MergeIntervals();
//
//        // Example input
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//
//        // Merging intervals
//        int[][] mergedIntervals = mi.merge(intervals);
//
//        // Printing the result
//        System.out.println("Merged Intervals:");
//        for (int[] interval : mergedIntervals) {
//            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
//        }
//    }
//}
/*
Time Complexity: O(n log n) => sorting takes O(n log n) and iteration O(n)
Space Complexity: O(n) as we use linkedlist
 */