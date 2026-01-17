package Codepatterns.LinkedListInplaceRev;

/*
Leetcode Problem 206: Reverse Linked List
 */
public class LinkedListReverse {
    ListNode head;

    // Function to reverse the linked list
    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next; // Store next node
            current.next = prev; // Reverse the link
            prev = current;      // Move prev and current one step forward
            current = next;
        }
        return prev; // New head of the reversed list
    }

    // Helper function to print the linked list
    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListReverse list = new LinkedListReverse();
        list.head = new ListNode(1);
        list.head.next = new ListNode(2);
        list.head.next.next = new ListNode(3);
        list.head.next.next.next = new ListNode(4);
        list.head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        list.printList(list.head);

        list.head = list.reverseLinkedList(list.head);

        System.out.println("Reversed Linked List:");
        list.printList(list.head);
    }
}
