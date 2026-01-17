package Codepatterns.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.List;
/*
Given the root of a binary tree and an integer x,
return the path from the root to the node with value x as a list of integers.
If such a node does not exist, return an empty list.
 */
public class PathsToNode {
    public List<Integer> geNodePath(TreeNode node, int x) {
        List<Integer> path = new ArrayList<>();
        if (node == null) {
            return path;
        }
        boolean isNodeThere = getPath(node, path, x);
        return path;
    }

    private boolean getPath(TreeNode node, List<Integer> path, int x) {
        if (node == null) {
            return false;
        }
        path.add(node.value);
        if (node.value == x) {
            return true;
        }

        if(getPath(node.left, path, x) || getPath(node.right, path, x)) {
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        node.left.left = new TreeNode(4);
        node.left.right =new TreeNode(5);
        node.left.right.left = new TreeNode(6);
        node.left.right.right = new TreeNode(7);

        PathsToNode pathsToNode = new PathsToNode();

        System.out.println("result path to x = " + pathsToNode.geNodePath(node, 7));


    }
/*
Time Complexity: O(N)
where N is the number of nodes in the binary tree

Space Complexity: O(N)
worst case when the tree is tree is skewed O(N)
else if height is balanced space will be 0(N) height of the tree
 */

}
