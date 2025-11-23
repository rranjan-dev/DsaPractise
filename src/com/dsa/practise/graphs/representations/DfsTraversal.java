package com.dsa.practise.graphs.representations;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * DFS Traversal - Multiple Implementations
 *
 * Graph Structure:
 * 0: 1, 3
 * 1: 0, 2
 * 2: 1, 3, 7, 8
 * 3: 2, 0, 4
 * 4: 3, 5, 6
 * 5: 4
 * 6: 4
 * 7: 2
 * 8: 2
 */

public class DfsTraversal {

    // ========== MY IMPLEMENTATION: PEEK based ==========
    /*
        Peek-based (yours):
        Loop finds the first unvisited neighbor
        Pushes ONLY that one
        Breaks out of loop
        Next iteration explores that one neighbor
     */
    private static Set<Integer> dfsTraversal(AdjacencyListRepresentation adjacencyListRepresentation, int startIndex) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> output = new LinkedHashSet<>();
        boolean[] visited = new boolean[adjacencyListRepresentation.vertices];
        stack.push(startIndex);
        visited[startIndex] = true;
        output.add(startIndex); // In DFS approach always add the vertax to the output when u visit it at the first time itself.
        while (!stack.isEmpty()) {
            boolean isUnvisitedNodePresent = false;
            Integer curr = stack.peek();
            /*
            you can combine below if-else blocks as if adjacencyListRepresentation.adjList.get(curr) get empty the enhanced for loop
            in else block will NOT run as as this enhanced for loop always check for hasNext() each time before executing the loop
            body contents.
            */
            if (adjacencyListRepresentation.adjList.get(curr).isEmpty()) {
                stack.pop();
            } else {
                for (AdjacencyListRepresentation.Edge e : adjacencyListRepresentation.adjList.get(curr)) {
                    if (!visited[e.dest]) {
                        isUnvisitedNodePresent = true;
                        stack.push(e.dest);
                        visited[e.dest] = true;
                        output.add(e.dest); // In DFS approach always add the vertax to the output when u visit it at the first time itself.
                        break;
                    }
                }
                if (!isUnvisitedNodePresent) {
                    stack.pop();
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        AdjacencyListRepresentation adjacencyListRepresentation = new AdjacencyListRepresentation(9);
        adjacencyListRepresentation.addUndirectedEdge(0, 1);
        adjacencyListRepresentation.addUndirectedEdge(1, 2);
        adjacencyListRepresentation.addUndirectedEdge(2, 3);
        adjacencyListRepresentation.addUndirectedEdge(3, 0);
        adjacencyListRepresentation.addUndirectedEdge(3, 4);
        adjacencyListRepresentation.addUndirectedEdge(4, 5);
        adjacencyListRepresentation.addUndirectedEdge(4, 6);
        adjacencyListRepresentation.addUndirectedEdge(2, 7);
        adjacencyListRepresentation.addUndirectedEdge(2, 8);
        Set<Integer> output = dfsTraversal(adjacencyListRepresentation, 0);
        output.forEach((e) -> System.out.println(e + " "));
    }

    // ========== IMPROVED IMPLEMENTATION 1: Simpler Iterative (Pop-based) ==========

    /**
     * Simpler iterative DFS - pops immediately and checks if visited
     * Pop-based (standard):
     * Loop pushes ALL unvisited neighbors to stack
     * But due to LIFO, only the LAST pushed is explored next
     * The rest wait in the stack until backtracking
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for stack and visited array
     */
    private static List<Integer> dfsIterativeSimple(AdjacencyListRepresentation graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int curr = stack.pop();

            // Skip if already visited
            if (visited[curr]) {
                continue;
            }

            // Visit the node
            visited[curr] = true;
            result.add(curr);
            
            /* In below Code, Push all unvisited neighbors (in reverse order for left-to-right traversal like [0, 1, 2, 3, 4, 5, 6, 7, 8],
             we can also do forward order for right-to-left traversal like [0, 3, 4, 6, 5, 2, 8, 7, 1]). Both are valid!
            */
            List<AdjacencyListRepresentation.Edge> neighbors = graph.adjList.get(curr);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i).dest;
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }

        return result;
    }

    // ========== IMPROVED IMPLEMENTATION 2: Recursive DFS ==========

    /**
     * Recursive DFS - most intuitive and elegant
     * <p>
     * Advantages:
     * - Most natural representation of DFS
     * - Simplest to understand and implement
     * - No explicit stack management needed
     * - Mimics the mathematical definition perfectly
     * <p>
     * Disadvantages:
     * - May cause stack overflow for very deep graphs
     * <p>
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for recursion stack and visited array
     */
    private static void dfsRecursiveHelper(AdjacencyListRepresentation graph, int startVertax,
                                           boolean[] visited, List<Integer> result) {
        // Visit the current startVertax
        visited[startVertax] = true;
        result.add(startVertax);

        // Recursively visit all unvisited neighbors
        for (AdjacencyListRepresentation.Edge edge : graph.adjList.get(startVertax)) {
            if (!visited[edge.dest]) {
                dfsRecursiveHelper(graph, edge.dest, visited, result);
            }
        }
    }

    private static List<Integer> dfsRecursive(AdjacencyListRepresentation graph, int startVertax) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        dfsRecursiveHelper(graph, startVertax, visited, result);
        return result;
    }

    // ========================================================================================================

    /**
     * DFS that handles disconnected graphs (multiple components)
     * <p>
     * Returns a list of lists, where each inner list is a connected component
     * <p>
     * Useful for:
     * - Finding connected components
     * - Social network analysis (friend groups)
     * - Island problems
     */
    private static List<List<Integer>> dfsAllComponents(AdjacencyListRepresentation graph) {
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];

        // Try starting DFS from each unvisited vertex
        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfsComponentHelper(graph, i, visited, component);
                if (!component.isEmpty()) {
                    components.add(component);
                }
            }
        }

        return components;
    }

    // ========== ADVANCED IMPLEMENTATION 4: DFS for Disconnected Graphs ==========

    private static void dfsComponentHelper(AdjacencyListRepresentation graph, int vertex,
                                           boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);

        for (AdjacencyListRepresentation.Edge edge : graph.adjList.get(vertex)) {
            if (!visited[edge.dest]) {
                dfsComponentHelper(graph, edge.dest, visited, component);
            }
        }
    }
}
