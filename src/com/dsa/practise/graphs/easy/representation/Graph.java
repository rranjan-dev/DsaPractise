package com.dsa.practise.graphs.easy.representation;

// for INTERVIEWS

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    // Map: Key = Node, Value = List of Neighbors
    Map<Integer, List<Integer>> adj = new HashMap<>();

    public Map<Integer, List<Integer>> getAdj(){
        return this.adj;
    }

    public void addEdge(int u, int v) {
        // "If u isn't in map, put a new ArrayList, then add v to it"
        /*
        This line does two things in one statement:

        Ensures there is a neighbor list for node u in the map adj.
        computeIfAbsent(u, k -> new ArrayList<>()) means: look up key u in adj.

        If u is already present, return the existing List<Integer> stored for u.
        If u is missing, create a new empty ArrayList<Integer>, put it into the map under key u, and return that new list.
        Here, k is just the lambda parameter that would receive the missing key (it will be equal to u), but it isn’t used.
        Adds v into that list.
        The result of computeIfAbsent(...) is a List<Integer>, so calling .add(v) appends v to u’s neighbor list.

        So in plain words: “Get the adjacency list for u, creating and storing an empty one if it doesn’t exist yet, then add v to it.”
         */
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void print() {
        for (var entry : adj.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        int[][] edges = {{0,1}, {1,3}, {3,5}, {3,4}, {4,5}, {5,6}, {0,2}, {2,4}};
        for (int[] edge : edges) g.addEdge(edge[0], edge[1]);
        g.print();
    }
}
