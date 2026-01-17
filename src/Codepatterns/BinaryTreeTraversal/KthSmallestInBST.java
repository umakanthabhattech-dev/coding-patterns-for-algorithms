package Codepatterns.BinaryTreeTraversal;
/*
230. Kth Smallest Element in a BST
Given the root of a binary search tree, and an integer k,
return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
 */
public class KthSmallestInBST {
    private int count = 0; // To keep track of the number of nodes visited
    private int result = -1; // To store the kth smallest value

    private int kthSmallest(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return result; // Return the kth smallest value
    }

    private void inOrderTraversal(TreeNode node, int k) {
        if (node == null || count >= k) {
            return; // Base case: if the node is null, return
        }

        // Traverse the left subtree
        inOrderTraversal(node.left, k);

        // Increment the count of visited nodes
        count++;

        // If count equals k, we found our kth smallest
        if (count == k) {
            result = node.value; // Store the kth smallest value
            return; // Exit the recursion
        }

        // Traverse the right subtree
        inOrderTraversal(node.right, k);
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

        KthSmallestInBST solution = new KthSmallestInBST();
        int k = 3; // Let's find the 3rd smallest element
        int kthSmallest = solution.kthSmallest(root, k);

        System.out.println("The " + k + "rd smallest element in the BST is: " + kthSmallest);
    }
}
/*
Time Complexity

The time complexity of this algorithm is O(k). This is because, in the worst case, we may need to traverse up to k nodes before finding the kth smallest element. If k is less than the total number of nodes in the tree, the traversal will stop as soon as we reach the kth node.
Space Complexity

The space complexity is O(h), where h is the height of the tree. This is due to the recursive call stack used during the in-order traversal. In the case of a balanced BST, the height h would be O(log n), where n is the number of nodes. However, in the worst case (for example, in a skewed tree), the height could be O(n).
Summary

Time Complexity: O(k)
Space Complexity: O(h) (where h is the height of the tree)
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

public class KthSmallestInBST {

    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null || count >= k) {
            return;
        }

        // Step 1: Visit left subtree (smaller values)
        inorder(node.left, k);

        // Step 2: Visit current node
        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        // Step 3: Visit right subtree (larger values)
        inorder(node.right, k);
    }
}

 */