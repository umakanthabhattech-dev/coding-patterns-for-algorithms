package Codepatterns.FastnSlow;

/*287. Find the Duplicate Number
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and using only constant extra space.
Example 1:
Input: nums = [1,3,4,2,2]
Output: 2
bruit force takes O(N2)
sorting = O(nlogn) using quick sort
hashmap = O(N) time complexity and O(N) spoce

Fast and Slow = O(N) time complexity and O(1) spoce
using

 */
public class FindTheDuplicateNumber {

    public static int findDuplicate(int[] nums) {
        // Phase 1: Detect intersection point using slow & fast
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];           // move 1 step
            fast = nums[nums[fast]];     // move 2 steps
        } while (slow != fast);

        // Phase 2: Move one pointer to start and move both 1 step until meet
        int slow2 = nums[0];

        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow;   // or slow2
    }

    // Main method to test
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};   // Example input
        int duplicate = findDuplicate(nums);
        System.out.println("Duplicate number is: " + duplicate);
    }
}
/*
Time complexity: O(n)
Space complexity: O(1)
                  p steps
     Start(0) <----------------------------+
        ●                                    |
        |                                    |
        v                                    |
       (1) --------------------------------> (2)
        ●                                      ●
         \                                     |
          \                                    v
           \                                (3)
            \                                ●
             \                                |
              \                               |
               \                              v
                \                          (4)
                 \                          ●
                  \                         |
                   \                        |
                    \                       |
                     \                      |
                      v                     |
                    (5)  <------------------+
                     ●   ←-- (c - x) ------→
                     ^
                     |
                     |
                     +------ x steps ------+
                     (intersection point)

        p steps =  is the distance from the start to the entrance of the cycle.
        x steps  = is the distance from the entrance of the cycle to the intersection point.
        c = is the length of the cycle.
        When slow and fast meet at the intersection point, the distance traveled by slow  = is (p + x),
        and the distance traveled by fast = is (p + x + n*c) where n is the number of full cycles fast has made.
        Since fast moves twice as fast as slow, we have:
        2 * (p + x) = p + x + n * c
        Simplifying this gives:
        p + x = n * c
        From this equation, we can derive that:
        p = n * c - x if n=1 then
        p = c - x
        This means that the distance from the start to the entrance of the cycle (p) is equal to the distance from
        the intersection point to the entrance of the cycle (c - x).
        Final conclusion:
        This means that if we start one pointer at the beginning of the list (Start(0)) and another pointer at the intersection point,
        and move both pointers one step at a time, they will meet at the entrance of the cycle after p steps.


P = Distance from Start(0) to the cycle start (node 1).

x = Distance from cycle start to the meeting/intersection point (node 5 in screenshot).

c − x Remaining arc of the cycle back to the cycle start.

Cycle = Nodes: 1 → 2 → 3 → 4 → 5 → back to 1

Intersection meeting

Slow and fast pointer meet at node 5 (exactly as in your screenshot).

 */
/* Aother solution using Negative Marking
Use negative marking only when:
    The problem does not say “don’t modify array”
    You want a quick solution
    Input array doesn’t need to be preserved
Examples:
    Checking for duplicates in array without constraints
    Quick coding test where you don’t care about mutation
but LeetCode 287 has constraint of not modifying the array.

public class FindDuplicateNumberNegativeMarking {

    public static int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);  // get value

            // If value at that index is already negative,
            // that means we have visited this index before → duplicate found.
            if (nums[index] < 0) {
                return index;
            }

            // Mark that index as visited
            nums[index] = -nums[index];
        }

        return -1; // not needed as per constraints, but for safety
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        int duplicate = findDuplicate(nums);
        System.out.println("Duplicate number is: " + duplicate);
    }
}

    */