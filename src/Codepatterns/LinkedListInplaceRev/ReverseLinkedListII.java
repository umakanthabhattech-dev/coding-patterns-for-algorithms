package Codepatterns.LinkedListInplaceRev;
/*
Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right, and return the reversed list.
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
 */
public class ReverseLinkedListII {

    ListNode reverseBetween(ListNode head, int left, int right) {
        // create a dummy node to mark the head of this list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // make markers for currentNode and for the node before reversing
        //Dummy needed to store previous node if range left starts from 0 or first node
        ListNode leftPre = dummy;
        ListNode currNode = head;

        for (int i = 0; i < left - 1; i++) {
            leftPre = leftPre.next;
            currNode = currNode.next;
        }

        // make a marker to node where we start reversing
        ListNode subListHead = currNode;

        ListNode preNode = null;
        for (int i = 0; i <= right - left; i++) {
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }

        // Join the pieces
        leftPre.next = preNode;
        subListHead.next = currNode;

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create a linked list: 4 -> 8 -> 15 -> 16 -> 23 -> 42
        ListNode head = new ListNode(4);
        head.next = new ListNode(8);
        head.next.next = new ListNode(15);
        head.next.next.next = new ListNode(16);
        head.next.next.next.next = new ListNode(23);
        head.next.next.next.next.next = new ListNode(42);

        // Create an instance of ReverseLinkedListII
        ReverseLinkedListII solution = new ReverseLinkedListII();

        // Reverse the linked list from position 2 to 5 (8 -> 15 -> 16 -> 23)
        ListNode newHead = solution.reverseBetween(head, 2, 5);

        // Print the reversed linked list
        printList(newHead);
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
/*
Time Complexity: O(n)
Space Complexity: O(1)
 */

