package com.dsa.practise.graphs.representations;

import java.util.ArrayList;
import java.util.List;

/*
 * Adjacency List representation of a graph
 *
 */
public class AdjacencyListRepresentation {
    List<List<Edge>> adjList;
    int vertices;
    AdjacencyListRepresentation(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        AdjacencyListRepresentation adjList = new AdjacencyListRepresentation(5);
        adjList.addUndirectedEdge(0, 1);
        adjList.addUndirectedEdge(1, 2);
        adjList.addUndirectedEdge(2, 3);
        adjList.addUndirectedEdge(3, 4);
        adjList.addUndirectedEdge(4, 1);
        adjList.printGraph();
    }

    void addDirectedEdge(int src, int dest) {
        adjList.get(src).add(new Edge(dest));
    }

    void addUndirectedEdge(int src, int dest) {
        addDirectedEdge(src, dest);
        addDirectedEdge(dest, src);
    }

    void printGraph() {
        for (int i = 0; i < vertices; i++) {
            List<Edge> list = adjList.get(i);
            for (Edge e : list) {
                System.out.print("(" + i + "->" + e.dest + ") ");
            }
            System.out.println();
        }
    }

    static class Edge {
        /* we dont need to store src as it is corresponds to the index of the list in which it is stored */

        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
        
        Edge(int dest) {
            this(dest, 1);
        }
    }
}
