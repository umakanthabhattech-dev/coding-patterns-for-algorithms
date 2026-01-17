package Codepatterns.DFS;

import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
    public int val; // Value of the node
    public List<Node> neighbors; // List of this node's neighbors

    // Default constructor
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    // Constructor with value
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    // Constructor with value and neighbors
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


