package Codepatterns.TopKElements;

import java.util.PriorityQueue;

/*
leetcode 215. Kth Largest Element in an Array
 */
public class KthLargestElementHeap {
    public static int findKthLargest(int[] nums, int k) {
        // Create a Min Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        // Add elements to the Min Heap
        for (int num : nums) {
            minHeap.add(num);
            // If the size of the heap exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the Min Heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("The " + k + "-th largest element is: " + findKthLargest(nums, k));
    }
}
/*
Complexity Analysis

Time Complexity: The overall time complexity is O(n log k),
where n is the number of elements in the array.
This is because each insertion into the heap takes O(log k) time.


Space Complexity: The space complexity is O(k) due to the storage of k elements in the heap.
 */