package Codepatterns.DFS;
import java.util.ArrayList;
import java.util.List;

/*
113. Path Sum II
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values
 in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
 */

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, targetSum, currentPath, result);
        return result;
    }

    private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Add the current node's value to the path
        currentPath.add(node.value);

        // Check if it's a leaf node and the remaining sum equals the node's value
        if (node.left == null && node.right == null && remainingSum == node.value) {
            result.add(new ArrayList<>(currentPath)); // Add a copy of the current path to the result
        } else {
            // Continue the search on the left and right children
            findPaths(node.left, remainingSum - node.value, currentPath, result);
            findPaths(node.right, remainingSum - node.value, currentPath, result);
        }

        // Backtrack: remove the current node from the path
        currentPath.remove(currentPath.size() - 1);
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
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        PathSumII solution = new PathSumII();
        int targetSum = 22;
        List<List<Integer>> paths = solution.pathSum(root, targetSum);

        // Print the result
        System.out.println("Paths with sum " + targetSum + ": " + paths);
    }
}
/*

Tree diagram:
```text
5
├─ 4
│  └─ 11
│     ├─ 7
│     └─ 2
└─ 8
   ├─ 13
   └─ 4
      ├─ 5
      └─ 1

Time Complexity: The time complexity is O(N), where N is the number of nodes in the binary tree.
Each node is visited once.
Space Complexity: The space complexity is O(H), where H is the height of the tree.
This accounts for the recursion stack. In the worst case (for a skewed tree), it can be O(N),
while in a balanced tree, it would be O(log N). Additionally,
the space used to store the result can also contribute to the space complexity,
depending on the number of valid paths found.
 */