package Codepatterns.OverlappingIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeFreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> allIntervals = new ArrayList<>();

        // 1. Collect all busy intervals
        for (List<Interval> emp : schedule) {
            allIntervals.addAll(emp);
        }

        // 2. Sort by start time
        //  why by start time?
        // Because we need to process the intervals in the order they occur throughout the day.
        // This allows us to easily identify gaps between the end of one interval and the start of the next,
        // which represent the free time slots.
        // If we sorted by end time, we wouldn't be able to accurately track the start of the next interval,
        // making it difficult to find the gaps.
        allIntervals.sort((a, b) -> a.start - b.start);
        //after sorting: (1,2),(1,3),(4,10),(5,6)

        List<Interval> freeTime = new ArrayList<>();
        Interval prev = allIntervals.get(0);

        // 3. Merge intervals & find gaps
        for (int i = 1; i < allIntervals.size(); i++) {
            Interval curr = allIntervals.get(i);

            if (prev.end < curr.start) {
                // gap found → common free time
                freeTime.add(new Interval(prev.end, curr.start));
                prev = curr;
            } else {
                // overlapping → merge
                prev.end = Math.max(prev.end, curr.end);
            }
        }
        return freeTime;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        List<List<Interval>> schedule = new ArrayList<>();

        // Employee 1
        schedule.add(Arrays.asList(
                new Interval(1, 2),
                new Interval(5, 6)
        ));

        // Employee 2
        schedule.add(Arrays.asList(
                new Interval(1, 3)
        ));

        // Employee 3
        schedule.add(Arrays.asList(
                new Interval(4, 10)
        ));

        List<Interval> result = employeeFreeTime(schedule);

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
//youtube link https://www.youtube.com/watch?v=99l7goR4y0U
/*
time complexity O(nlogn) due to sorting
space complexity O(n) for storing all intervals
 */
/*
o/p
Employee Free Time: 3 to 4
initial input schedules of three employees are (1,2),(5,6) (1,3) (4,10)
after sorting input intervals based on start time we get (1,2),(1,3),(4,10),(5,6)
merging overlapping intervals we get (1,3),(4,10)
the gap between (1,3) and (4,10) is (3,4) which is the common free time for all employees


logic
1. Collect all busy intervals from all employees into a single list.
2. Sort this list based on the start times of the intervals.
3. Iterate through the sorted list to merge overlapping intervals and identify gaps between them,
   which represent common free time for all employees.

 */

