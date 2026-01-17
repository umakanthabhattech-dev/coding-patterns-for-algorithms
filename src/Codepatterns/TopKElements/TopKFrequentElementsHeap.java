package Codepatterns.TopKElements;

import java.util.*;

/*
same as leetcode 347. Top K Frequent Elements
 */
class TopKFrequentElementsHeap {
    public static List<Integer> topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int n : arr)
            numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> topKElements = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            topKElements.add(entry);
            if (topKElements.size() > k) {
                topKElements.poll();
            }
        }

        List<Integer> topNumbers = new ArrayList<>(k);
        while (!topKElements.isEmpty()) {
            topNumbers.add(topKElements.poll().getKey());
        }
        Collections.sort(topNumbers);
        return topNumbers;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 3, 5, 12, 11, 12, 11, 12, 5},
                {1, 3, 5, 14, 18, 14, 5},
                {2, 3, 4, 6, 6, 7, 7},
                {9, 8, 7, 6, 6, 5, 4, 3, 2, 1},
                {2, 4, 3, 2, 3, 4, 5, 4, 4, 4},
                {1, 1, 1, 1, 1, 1},
                {2, 3},
                {1, 1, 2, 2}
        };
        int[] inputK = {2, 2, 1, 1, 3, 1, 2, 1};
        for (int i = 0; i < inputK.length; i++) {
            List<Integer> result = topKFrequent(inputs[i], inputK[i]);
            System.out.print(i + 1);
            System.out.println(".\tInput: (" + Arrays.toString(inputs[i]) + ", " + inputK[i] + ")");
            System.out.println("\n\tTop " + inputK[i] + " frequent elements: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
/*
Time complexity
Let n be the number of elements in our list and k be the number of frequent elements we need to return.
Since we iterate through a list of size n inserting an element into the heap takes O(log(k)) time.
If k<n the time complexity will be O(n×log(k)) and if k==n the time complexity will be O(n×log(n))

O(N + U*logk) ==> O(n×log(k)) N

Space
Space Complexity: O(N) (or O(N + k), depending on context)
heap and hash map to store the elements. Since the heap will be storing at most k elements and the hash map will contain
n elements

 */
