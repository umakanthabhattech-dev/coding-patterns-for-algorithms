package Codepatterns.TopKElements;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
leetcode 347. Top K Frequent Elements
Given an integer array nums and an integer k,
return the k most frequent elements. You may return the answer in any order.

 */
public class TopKFrequentElement {

    public int[] topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> topK = new ArrayList<>();
        for (int pos = bucket.length - 1;pos >= 0 && topK.size() < k; pos--) {
            System.out.println("pos = " + pos);
            System.out.println("topK.size() = " + topK.size());
            if (bucket[pos] != null) {
                topK.addAll(bucket[pos]);
            }
        }

        return topK.stream().mapToInt(i -> i).toArray();

    }

    public static void main(String[] args) {
        TopKFrequentElement solution = new TopKFrequentElement();

        // Example input
//        int[] nums = {1, 1, 1, 2, 2, 3};
//        int k = 2;

        int[] nums = {1, 1, 2, 2};
        int k = 1;


        // Get the top k frequent elements
        int[] result = solution.topKFrequent(nums, k);

        // Print the result
        System.out.print("Top " + k + " frequent elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

}
