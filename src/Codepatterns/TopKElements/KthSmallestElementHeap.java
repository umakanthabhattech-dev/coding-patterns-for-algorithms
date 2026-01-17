package Codepatterns.TopKElements;

import java.util.PriorityQueue;

public class KthSmallestElementHeap {
    public static int findKthSmallest(int[] nums, int k) {
        // Create a Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add elements to the Max Heap
        for (int num : nums) {
            maxHeap.add(num);
            // If the size of the heap exceeds k, remove the largest element
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // The root of the Max Heap is the k-th smallest element
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("The " + k + "-th smallest element is: " + findKthSmallest(nums, k));
    }
}
/*
Complexity Analysis

Time Complexity: The overall time complexity is O(n log k), where n is the number of elements in the array. Each insertion into the heap takes O(log k) time.
Space Complexity: The space complexity is O(k) due to the storage of k elements in the heap.
 */