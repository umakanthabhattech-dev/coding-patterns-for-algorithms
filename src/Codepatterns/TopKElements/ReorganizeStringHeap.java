package Codepatterns.TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*


 */
class ReorganizeStringHeap {
    public String reorganizeString(String s) {
        // Step 1: Count frequency of each character
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Step 2: Check feasibility using a max-heap
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(charCount.entrySet());

        StringBuilder result = new StringBuilder();

        // Step 3: Reorganize characters
        while (maxHeap.size() > 1) {
            System.out.println("current peek " + maxHeap.toString());
            System.out.println("current peek " + maxHeap.peek().getKey());
            // Get the two most frequent characters
            Map.Entry<Character, Integer> first = maxHeap.poll();
            System.out.println("current peek after removinf first " + maxHeap.peek().getKey());
            Map.Entry<Character, Integer> second = maxHeap.poll();
            System.out.println("current peek after removing second " + maxHeap.peek().getKey());
            System.out.println("first " + first);
            System.out.println("second " + second);

            // Append them to the result
            result.append(first.getKey());
            result.append(second.getKey());

            // Decrease their counts
            if (first.getValue() > 1) {
                first.setValue(first.getValue() - 1);
                maxHeap.offer(first);
                System.out.println("current peek after adding first " + maxHeap.peek().getKey());
            }
            if (second.getValue() > 1) {
                second.setValue(second.getValue() - 1);
                maxHeap.offer(second);
                System.out.println("current peek after adding second " + maxHeap.peek().getKey());
            }
            System.out.println("--------------------");
        }

        // If there's one character left in the heap, add it to the result
        if (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> last = maxHeap.poll();
            if (last.getValue() > 1) {
                return ""; // Impossible to reorganize
            }
            result.append(last.getKey());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ReorganizeStringHeap solution = new ReorganizeStringHeap();

        // Test cases
        String test = "bbnnc";
        String test1 = "aabbcc";
        String test2 = "aaab";
        String test3 = "aab";
        String test4 = "abcde"; // All unique characters

        System.out.println("Reorganized string for '" + test + "': " + solution.reorganizeString(test));
//        System.out.println("Reorganized string for '" + test1 + "': " + solution.reorganizeString(test1));
//        System.out.println("Reorganized string for '" + test2 + "': " + solution.reorganizeString(test2));
//        System.out.println("Reorganized string for '" + test3 + "': " + solution.reorganizeString(test3));
//        System.out.println("Reorganized string for '" + test4 + "': " + solution.reorganizeString(test4));
    }
}
