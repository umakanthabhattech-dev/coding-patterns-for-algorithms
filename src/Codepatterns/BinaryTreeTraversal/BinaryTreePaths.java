package Codepatterns.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.List;

/*
257. Binary Tree Paths
Given the root of a binary tree, return all root-to-leaf paths in any order.
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
 */
public class BinaryTreePaths {
    public List<List<Integer>> getAllLeafPaths(TreeNode node) {
        List<List<Integer>> paths = new ArrayList<>();
        if (node == null) {
            return paths;
        }
        List<Integer> currentPath = new ArrayList<>();
        findLeafPaths(node, currentPath, paths);
        return paths;
    }

    private void findLeafPaths(TreeNode node, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == null) {
            return;
        }

        // Add the current node's value to the path
        currentPath.add(node.value);

        // Check if it's a leaf node
        if (node.left == null && node.right == null) {
            // If it's a leaf, add the current path to the list of paths
            paths.add(new ArrayList<>(currentPath));
        } else {
            // Continue to traverse the tree
            findLeafPaths(node.left, currentPath, paths);
            findLeafPaths(node.right, currentPath, paths);
        }

        // Backtrack to explore other paths
        currentPath.remove(currentPath.size() - 1);


    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.left.right.left = new TreeNode(6);
        node.left.right.right = new TreeNode(7);

        /*
                    1
                   / \
                  2   3
                 / \
                4   5
                   / \
                  6   7
         */

        BinaryTreePaths pathsToLeafNodes = new BinaryTreePaths();
        List<List<Integer>> resultPaths = pathsToLeafNodes.getAllLeafPaths(node);

        System.out.println("All paths to leaf nodes: " + resultPaths);
    }
}
/*
Time complexity
O(n) In the worst case, every node in the tree is visited once.
Therefore, if there are n nodes in the tree, the time complexity is O(n).

The space complexity is determined by two factors:
Recursive Call Stack: The maximum depth of the recursion stack can go up to the height of the tree. In the worst case (for a skewed tree), this height can be n, leading to a space complexity of O(n).
Storage for Paths: We store all paths from the root to the leaf nodes. In the worst case, if the tree is a complete binary tree, the number of leaf nodes can be approximately n/2, and each path can have a length of up to h (the height of the tree). Thus, the space required to store these paths can also be considered as O(n) in total.
Combining these factors, the overall space complexity remains O(n).
Summary

Time Complexity: O(n)
Space Complexity: O(n)

find(1)  currentPath = [1]
 ├─ find(2)  currentPath = [1,2]
 │   ├─ find(4)  currentPath = [1,2,4]
 │   │   └─ leaf -> add [1,2,4], return (backtrack to [1,2])
 │   └─ find(5)  currentPath = [1,2,5]
 │       ├─ find(6)  currentPath = [1,2,5,6]
 │       │   └─ leaf -> add [1,2,5,6], return (backtrack to [1,2,5])
 │       └─ find(7)  currentPath = [1,2,5,7]
 │           └─ leaf -> add [1,2,5,7], return (backtrack to [1,2])
 │   └─ return to find(1) (backtrack to [1])
 └─ find(3)  currentPath = [1,3]
     └─ leaf -> add [1,3], return (backtrack to [])

 Order of paths added:
1. [1,2,4]
2. [1,2,5,6]
3. [1,2,5,7]
4. [1,3]

 */