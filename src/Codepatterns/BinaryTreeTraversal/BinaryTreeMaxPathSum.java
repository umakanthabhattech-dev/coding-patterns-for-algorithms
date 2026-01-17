package Codepatterns.BinaryTreeTraversal;

/*
124. Binary Tree Maximum Path Sum
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
has an edge connecting them. A node can only appear in the sequence at most once.
Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

path : is like curve
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class BinaryTreeMaxPathSum {
    private int maxSum = Integer.MIN_VALUE; // To keep track of the maximum path sum

    public int maxPathSum(TreeNode root) {
        calculateMaxPathSum(root);
        return maxSum; // Return the maximum path sum found
    }

    private int calculateMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0; // Base case: if the node is null, return 0
        }

        // Recursively calculate the maximum path sum for left and right subtrees
        int leftMax = Math.max(calculateMaxPathSum(node.left), 0); // Ignore negative sums
        int rightMax = Math.max(calculateMaxPathSum(node.right), 0); // Ignore negative sums

        // Calculate the maximum path sum passing through the current node
        int currentMax = node.val + leftMax + rightMax;

        // Update the overall maximum path sum
        maxSum = Math.max(maxSum, currentMax);

        // Return the maximum path sum extending from the current node
        return node.val + Math.max(leftMax, rightMax);
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        // Create a sample binary tree:
        //        -10
        //        /  \
        //       9   20
        //          /  \
        //         15   7
        // paths can be found analysing the curve like 15,20,7
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BinaryTreeMaxPathSum solution = new BinaryTreeMaxPathSum();
        int maxPathSum = solution.maxPathSum(root);

        System.out.println("The maximum path sum in the binary tree is: " + maxPathSum);
    }
}
/*
Complexity Analysis:

Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
Space Complexity: O(h), where h is the height of the tree, due to the recursion stack. In the worst case (skewed tree),
this could be O(n). In a balanced tree, it would be O(log n).
 */
/*
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // Step 1: get max gain from left & right
        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        // Step 2: path passing through this node
        int currentPathSum = node.val + leftGain + rightGain;

        // Step 3: update global maximum
        maxSum = Math.max(maxSum, currentPathSum);

        // Step 4: return max gain to parent (only one side allowed)
        return node.val + Math.max(leftGain, rightGain);
    }
}

 */
