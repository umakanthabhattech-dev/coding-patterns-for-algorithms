package Codepatterns.TopKElements;

import java.util.PriorityQueue;

/*
leetcode 703. Kth Largest Element in a Stream
 */
public class KthLargestElementStreamHeap {
    private int k;
    private PriorityQueue<Integer> pq;

    public KthLargestElementStreamHeap(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>(); // Min-heap
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll(); // Remove the smallest element
            }
        }
    }

    public int add(int val) {
        pq.add(val);
        if (pq.size() > k) {
            pq.poll(); // Remove the smallest element
        }
        return pq.peek(); // The kth largest element
    }

    public static void main(String[] args) {
        KthLargestElementStreamHeap kthLargest = new KthLargestElementStreamHeap(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));  // returns 4
        System.out.println(kthLargest.add(5));  // returns 5
        System.out.println(kthLargest.add(10)); // returns 5
        System.out.println(kthLargest.add(9));  // returns 8
        System.out.println(kthLargest.add(4));  // returns 8
    }
}
/*
Constructor Time Complexity: O(n log k)
Add Method Time Complexity: O(log k)
Space Complexity: O(k)
This is because we are maintaining a min-heap of size k to store the k largest elements from the stream.
 */
