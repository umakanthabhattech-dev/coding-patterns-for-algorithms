package Codepatterns.LinkedListInplaceRev;

/*
1721. Swapping Nodes in a Linked List
Given the linked list and an integer, k, return the head of the linked list after swapping the values of the
kth node from the beginning and the kth node from the end of the linked list.
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

 */
public class SwapListNodes {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode firstKNode = null;

        // Move fast pointer k-1 steps ahead
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        firstKNode = fast; // This is the kth node from the beginning

        // Move both fast and slow pointers until fast reaches the end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Now slow is at the kth node from the end
        ListNode secondKNode = slow; // This is the kth node from the end

        // Swap the values of the two nodes
        int temp = firstKNode.value;
        firstKNode.value = secondKNode.value;
        secondKNode.value = temp;

        return head; // Return the head of the modified list
    }
    public static void main(String[] args) {
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Value of k
        int k = 2;

        // Create an instance of Solution and call swapNodes
        SwapListNodes solution = new SwapListNodes();
        head = solution.swapNodes(head, k);

        // Print the modified linked list
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}
/*
Time complexity = O(n)
space complexity=O(1)
 */