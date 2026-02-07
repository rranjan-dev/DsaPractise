package com.dsa.practise.graphs.representations;

import java.util.ArrayList;
import java.util.List;

/*
       1----3
      /     | \
     0      |   5 -- 6
      \     | /
       2----4


 */

public class Graph {
    int vertices;
    List<List<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(Edge edge) {
        adjacencyList.get(edge.src).add(edge.dest);
        adjacencyList.get(edge.dest).add(edge.src);
    }

    public static class Edge {
        private int src;
        private int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(3, 5));
        graph.addEdge(new Edge(3, 4));
        graph.addEdge(new Edge(4, 5));
        graph.addEdge(new Edge(5, 6));
        graph.addEdge(new Edge(0, 2));
        graph.addEdge(new Edge(2, 4));
        for (int i = 0; i < graph.vertices; i++) {
            System.out.print("nodes adjacent to " + i + " are : ");
            for (int j = 0; j < graph.adjacencyList.get(i).size(); j++) {
                System.out.print("(" + i + "," + graph.adjacencyList.get(i).get(j) + ") ");
            }
            System.out.println();
        }
    }
}
