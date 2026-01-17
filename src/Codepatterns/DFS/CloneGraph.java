package Codepatterns.DFS;

/*
133. Clone Graph
Given a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.
Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
class Node {
    public int val;
    public List<Node> neighbors;
}
 */

import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    // Map to keep track of cloned nodes to avoid duplicates
    private Map<Node, Node> visited = new HashMap<>();

    // Method to clone the graph
    public Node clone(Node node) {
        System.out.println("clone " + node.val);
        // If the input node is null, return null
        if (node == null) return null;

        // If the node has already been cloned, return the cloned node
        if (visited.containsKey(node)) return visited.get(node);

        // Create a new clone of the current node
        Node cloneNode = new Node(node.val);
        // Store the clone in the visited map
        visited.put(node, cloneNode);

        // Iterate through all the neighbors of the original node
        for (Node neighbor : node.neighbors) {
            // Recursively clone the neighbor and add it to the clone's neighbors
            cloneNode.neighbors.add(clone(neighbor));
        }

        // Return the cloned node
        return cloneNode;
    }

    // Main method to test the clone function
    public static void main(String[] args) {
        // Creating the graph with vertices 1, 2, 3, 4
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Setting up the adjacency list
        node1.neighbors.add(node2); // 1 <-> 2
        node1.neighbors.add(node4); // 1 <-> 4
        node2.neighbors.add(node1); // 2 <-> 1
        node2.neighbors.add(node3); // 2 <-> 3
        node3.neighbors.add(node2); // 3 <-> 2
        node3.neighbors.add(node4); // 3 <-> 4
        node4.neighbors.add(node1); // 4 <-> 1
        node4.neighbors.add(node3); // 4 <-> 3

        // Create an instance of Solution
        CloneGraph solution = new CloneGraph();
        // Clone the graph starting from node1
        Node clonedGraph = solution.clone(node1);

        // Output the cloned graph
        System.out.println("Cloned Node Value: " + clonedGraph.val);
        for (Node neighbor : clonedGraph.neighbors) {
            System.out.println("Neighbor Node Value: " + neighbor.val);
        }

        // Output neighbors of each node in the cloned graph for verification
        System.out.println("Neighbors of Cloned Node 1:");
        for (Node neighbor : clonedGraph.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        // Accessing the cloned nodes correctly
        Node clonedNode2 = clonedGraph.neighbors.get(0); // This should be the clone of node 2
        System.out.println("Neighbors of Cloned Node 2:");
        for (Node neighbor : clonedNode2.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        Node clonedNode3 = clonedNode2.neighbors.get(1); // This should be the clone of node 3
        System.out.println("Neighbors of Cloned Node 3:");
        for (Node neighbor : clonedNode3.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        Node clonedNode4 = clonedGraph.neighbors.get(1); // This should be the clone of node 4
        System.out.println("Neighbors of Cloned Node 4:");
        for (Node neighbor : clonedNode4.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();
    }
}
/*
Time and Space Complexity of the Clone Graph Algorithm

Time Complexity

The time complexity of the cloneGraph function is O(V + E), where:
V is the number of vertices (nodes) in the graph.
E is the number of edges (connections between nodes).
This complexity arises because:
Each node is visited exactly once during the depth-first search (DFS) traversal.
Each edge is processed once when iterating through the neighbors of each node.
Thus, the overall time complexity is linear with respect to the size of the graph.

Space Complexity
The space complexity of the algorithm is also O(V), where:
V is the number of vertices in the graph.
This space is used for:
The visited map, which stores references to the cloned nodes to avoid duplicating nodes.
The recursion stack used during the DFS traversal, which can go as deep as the number of vertices
in the worst case (in case of a linear graph).

In summary, both the time and space complexities of the cloneGraph function are O(V + E) and O(V), respectively.
 */

/*
Hint:
         dfs(1)
        /
       dfs(2) first check 2 in visited map else create new node add to visited map, iterate over its neighbours
      /
     dfs(3)
    /
    dfs(4)



dfs(1)
     check 1 in visited map no so add to visited map
     check its neighbors call - 2, 4 but get first neighbour and do dfs(2)
     check 2 : has in visited map no so add to visited map
              1, 3 iterate all neighbors but first dfs(1) already there so go to dfs(3)
     check 3 : as in visited map no so add to visited map
               2,4 => 2 already there so go to dfs(4)
     check 4 : 1, 3 already there so backtrack till 1

     at 1 check 4 => already there so backtrack and end



 */

/*
import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node);
    }

    private Node dfs(Node node) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor));
        }

        return clone;
    }
}

public class CloneGraphMain {

    // Utility function to print graph (BFS)
    public static void printGraph(Node node) {
        if (node == null) {
            System.out.println("Graph is empty");
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print("Node " + curr.val + " -> ");

            for (Node neigh : curr.neighbors) {
                System.out.print(neigh.val + " ");
                if (!visited.contains(neigh)) {
                    visited.add(neigh);
                    queue.offer(neigh);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Create sample graph:
        // 1 -- 2
        // |    |
        // 4 -- 3

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        // Connect graph
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        // Clone the graph
        Solution sol = new Solution();
        Node clonedGraph = sol.cloneGraph(n1);

        // Print original graph
        System.out.println("Original Graph:");
        printGraph(n1);

        // Print cloned graph
        System.out.println("\nCloned Graph:");
        printGraph(clonedGraph);
    }
}

 */