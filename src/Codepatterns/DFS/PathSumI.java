package Codepatterns.DFS;
/*
Leetcode 112. Path Sum
Given the root of a binary tree and an integer targetSum,
return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
 */
public class PathSumI {
    private boolean hasPathSum(TreeNode node, int currentTargerSum) {
        System.out.println("Visiting Node with value: " + (node != null ? node.value : "null") + ", Current Target Sum: " + currentTargerSum);
        //Base Case: If the current node (root) is null, it returns false since there's no path.
        if (node == null)
            return false;

        // If the current node is a leaf (both children are null),
        // it checks if the value of the node equals the targetSum.
        if (node.left == null && node.right == null) {
            return node.value == currentTargerSum;
        }
        currentTargerSum -= node.value;

        //If not at a leaf, it subtracts the current node's value from targetSum and recursively
        // checks both the left and right subtrees.
        // Recursively check the left and right subtrees with the updated targetSum
        return hasPathSum(node.left, currentTargerSum) || hasPathSum(node.right, currentTargerSum);
    }

    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        PathSumI solution = new PathSumI();
        int targetSum = 22;
        boolean result = solution.hasPathSum(root, targetSum);
        System.out.println("Has path sum: " + result); // Output: true
    }
}
/*
target sum = 22
          5
         / \
        4   8
       /   / \
     11   13  4
    /  \       \
   7    2       1

Time and Space Complexity

Time Complexity: The time complexity of this algorithm is O(N),
where N is the number of nodes in the binary tree.
In the worst case, we may need to visit every node to find a valid path.

Space Complexity: The space complexity is O(H), where H is the height of the tree.
This is due to the recursion stack.
In the worst case (for a skewed tree), the height can be equal to N, leading to O(N) space. In a balanced tree, it would be O(log N).
 */