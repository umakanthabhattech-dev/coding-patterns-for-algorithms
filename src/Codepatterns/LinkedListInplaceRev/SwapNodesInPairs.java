package Codepatterns.LinkedListInplaceRev;

/*
leetcode 24. Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head.
You must solve the problem without modifying the values in the list's nodes
(i.e., only nodes themselves may be changed.)
Input: head = [1,2,3,4]
Output: [2,1,4,3]
 */
public class SwapNodesInPairs {

    ListNode swapPairs(ListNode head) {
        // Create a dummy node to ease things
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode point = dummy;

        // Ensure nodes we are swapping are not null
        while (point.next != null && point.next.next != null) {
            // Identify nodes to swap
            ListNode swap1 = point.next;
            ListNode swap2 = point.next.next;

            // Actually swap
            swap1.next = swap2.next;
            swap2.next = swap1;

            // Prepare for next
            point.next = swap2;
            point = swap1;
        }

        // Return the start node
        return dummy.next;
    }

    public static void main(String[] args) {
        // Create a linked list: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Create an instance of SwapNodesInPairs
        SwapNodesInPairs solution = new SwapNodesInPairs();

        // Swap nodes in pairs
        ListNode newHead = solution.swapPairs(head);

        // Print the swapped linked list
        printList(newHead);
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
