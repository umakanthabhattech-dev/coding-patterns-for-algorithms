package Codepatterns.OverlappingIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Leetcode Problem 759: Employee Free Time
We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees,
excluding intervals of length 0.
(An interval [a, b] is considered to have positive length if a < b).
Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

 */
public class EmployeeFreeTimeHeap {

    public static List<Interval> employeeFreeTime(List<Interval> intervals) {

        List<Interval> freeTime = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return freeTime;
        }

        // 1️⃣ Min-heap by start time
        PriorityQueue<Interval> pq = new PriorityQueue<>(
                (a, b) -> a.start - b.start
        );

        // 2️⃣ Push all intervals into heap
        for (Interval i : intervals) {
            pq.offer(i);
        }

        // 3️⃣ Initialize prev
        Interval prev = pq.poll();

        // 4️⃣ Process heap
        while (!pq.isEmpty()) {
            Interval curr = pq.poll();

            if (prev.end < curr.start) {
                // free time found
                freeTime.add(new Interval(prev.end, curr.start));
                prev = curr;
            } else {
                // merge overlapping intervals
                prev.end = Math.max(prev.end, curr.end);
            }
        }
        return freeTime;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        List<Interval> intervals = Arrays.asList(
                new Interval(1, 2),
                new Interval(5, 6),
                new Interval(1, 3),
                new Interval(4, 10)
        );

        List<Interval> result = employeeFreeTime(intervals);

        System.out.println("Employee Free Time:");
        if (result.isEmpty()) {
            System.out.println("No common free time");
        } else {
            for (Interval i : result) {
                System.out.println(i.start + " to " + i.end);
            }
        }
    }
}
/*
time Complexity: O(n log n), where n is the total number of intervals across all employees.
space Complexity: O(n), where n is the total number of intervals stored in the min-heap and the result list.

 */



//public class EmployeeFreeTimeHeap {
//    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
//        List<Interval> result = new ArrayList<>();
//        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.start - b.start);
//
//        // Add all intervals to the min-heap
//        for (List<Interval> employee : schedule) {
//            for (Interval interval : employee) {
//                minHeap.offer(interval);
//            }
//        }
//
//        // Merge intervals and find free time
//        Interval prev = minHeap.poll();
//        while (!minHeap.isEmpty()) {
//            Interval curr = minHeap.poll();
//            if (curr.start > prev.end) {
//                // Found a free time interval
//                result.add(new Interval(prev.end, curr.start));
//            }
//            // Merge intervals
//            prev = new Interval(Math.max(prev.start, curr.start), Math.max(prev.end, curr.end));
//        }
//
//        return result;
//    }
//
//    public static void main(String[] args) {
//        EmployeeFreeTimeHeap eft = new EmployeeFreeTimeHeap();
//
//        // Example input: schedules of three employees
//        List<List<Interval>> schedule = new ArrayList<>();
//
//        // Employee 1's schedule
//        List<Interval> emp1 = new ArrayList<>();
//        emp1.add(new Interval(1, 2));
//        emp1.add(new Interval(5, 6));
//        schedule.add(emp1);
//
//        // Employee 2's schedule
//        List<Interval> emp2 = new ArrayList<>();
//        emp2.add(new Interval(1, 3));
//        schedule.add(emp2);
//
//        // Employee 3's schedule
//        List<Interval> emp3 = new ArrayList<>();
//        emp3.add(new Interval(4, 10));
//        schedule.add(emp3);
//
//        // Finding free time
//        List<Interval> freeTime = eft.employeeFreeTime(schedule);
//
//        // Output the free time intervals
//        System.out.println("Free Time Intervals:");
//        for (Interval interval : freeTime) {
//            System.out.println("[" + interval.start + ", " + interval.end + "]");
//        }
//    }
//}
/*

Time Complexity: O(n log n), where n is the total number of intervals across all employees.
Space Complexity: O(n), where n is the total number of intervals stored in the min-heap and the result list.

 */
