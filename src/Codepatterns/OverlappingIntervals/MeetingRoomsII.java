package Codepatterns.OverlappingIntervals;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
LeetCode Problem 253: Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.
example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1

Solution:
At any point in time, what is the maximum number of overlapping meetings?

That maximum overlap = minimum rooms required.


 */
public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Step 1: Sort the meetings in increasing order of their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Create a priority queue to track end times of meetings
        //By default minHeap
        // The heap will store the end times of meetings
        // The smallest end time will be at the top
        // This helps us efficiently check if a room is free
        // to allocate to a new meeting
        // Min-heap to track the minimum end time
        // of merged intervals
        // The size of the heap will tell us the number of rooms needed
        // at any point in time
        // because each entry in the heap represents a meeting room
        // that is currently occupied
        // by a meeting that has not yet ended
        // Thus, the maximum size of the heap
        // during the iteration will give us the minimum number of rooms required

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Step 3: Add the first meeting's end time to the heap
        heap.offer(intervals[0][1]);

        // Step 4: Iterate through the remaining meetings
        for (int i = 1; i < intervals.length; i++) {
            // If the meeting starts after the earliest ending meeting, free that room
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            // Add the current meeting's end time to the heap
            heap.offer(intervals[i][1]);
        }

        // The size of the heap is the number of meeting rooms needed
        return heap.size();
    }
}
/*
If overlap create new room and add to the heap.
if no overlap get min time from minheap and poll that and add new into the healp
Time complexity
Sorting: O(NlogN)
Iterating with poll and offer: O(N⋅logN)

Space Complexity:
O(N) priority queue potentially holding all meeting end times.
 */