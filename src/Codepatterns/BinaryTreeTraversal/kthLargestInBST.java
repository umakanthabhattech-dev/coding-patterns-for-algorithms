package Codepatterns.BinaryTreeTraversal;

/*
leetcode 230. Kth Largest Element in a BST

 */
public class kthLargestInBST {
    private int count = 0; // To keep track of the number of nodes visited
    private int result = -1; // To store the kth largest value

    public int kthLargest(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return result; // Return the kth largest value
    }

    private void inOrderTraversal(TreeNode node, int k) {
        if (node == null) {
            return; // Base case: if the node is null, return
        }

        // Traverse the right subtree first as reverse order for largest number
        inOrderTraversal(node.right, k);

        // Increment the count of visited nodes
        count++;

        // If count equals k, we found our kth largest
        if (count == k) {
            result = node.val; // Store the kth largest value
            return; // Exit the recursion means we found our answer
        }

        // Traverse the left subtree
        inOrderTraversal(node.left, k);
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        // Create a sample BST:
        //        5
        //       / \
        //      3   7
        //     / \   \
        //    2   4   8
        //   /
        //  1

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right.right = new TreeNode(8);

        kthLargestInBST solution = new kthLargestInBST();
        int k = 2; // Let's find the 2nd largest element
        int kthLargest = solution.kthLargest(root, k);

        System.out.println("The " + k + "nd largest element in the BST is: " + kthLargest);
    }
}
/*
Complexity Analysis:

Time Complexity: O(k) - We may need to traverse up to k nodes.
Space Complexity: O(h) - Due to the recursion stack, where h is the height of the tree.
In a balanced tree, this is O(log n), and in the worst case (skewed tree), it is O(n).
 */
/*
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class KthLargestInBST {

    private int count = 0;
    private int result = -1;

    public int kthLargest(TreeNode root, int k) {
        reverseInorder(root, k);
        return result;
    }

    private void reverseInorder(TreeNode node, int k) {
        if (node == null || count >= k) {
            return;
        }

        // Step 1: Go to right subtree (larger values)
        reverseInorder(node.right, k);

        // Step 2: Visit current node
        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        // Step 3: Go to left subtree (smaller values)
        reverseInorder(node.left, k);
    }
}

 */