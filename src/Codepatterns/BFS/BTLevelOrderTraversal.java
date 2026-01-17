package Codepatterns.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *leetcode 102. Binary Tree Level Order Traversal
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 */
public class BTLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            //why levelSize? To process nodes level by level
            //
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.value);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        BTLevelOrderTraversal solution = new BTLevelOrderTraversal();
        List<List<Integer>> levelOrderTraversal = solution.levelOrder(root);

        // Print the level order traversal
        System.out.println(levelOrderTraversal);
    }
}
/*
Time and Space Complexity

Time Complexity: O(n)
Every node in the binary tree is processed exactly once. Therefore, if there are n nodes, the time complexity is linear with respect to the number of nodes.
Space Complexity: O(n)
In the worst case, the queue may contain all the nodes at the last level of the tree.
This happens in a completely balanced binary tree where the last level contains about n/2 nodes.
Thus, the space complexity can also be considered linear in the worst case.
Additionally, the result list will also require space to store the level order traversal,
contributing to the overall space complexity.
 */

/*
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class LevelOrderTraversal {

    public static void levelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public static void main(String[] args) {
        /*
                1
              /   \
             2     3
            / \   / \
           4   5 6   7
        */
/*
TreeNode root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);

levelOrder(root);
    }
            }

 */