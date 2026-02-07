package com.dsa.practise.graphs.easy.traversals;

import com.dsa.practise.graphs.easy.representation.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
       1----3
      /     | \
     0      |   5 -- 6
      \     | /
       2----4

 */

public class Traversal {
    public void bfsTraversal(int start, Graph graph){
        Map<Integer, List<Integer>> adj = graph.getAdj();
        System.out.println("length of adj " + adj.size());
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start]= true;
        while(!queue.isEmpty()){
           Integer curr = queue.remove();
            System.out.print(curr + "--");
           List<Integer> neighbours = adj.get(curr);
           for(Integer eachNeighbour : neighbours){
               if(!visited[eachNeighbour]){
                   queue.add(eachNeighbour);
                   visited[eachNeighbour]= true;
               }
           }
        }
    }

    public void dfsTraversal(int start, Graph g, Set<Integer> visited) {
        System.out.print(start + " ");
        visited.add(start);
        List<Integer> neighbors = g.getAdj().get(start);
        for (Integer neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsTraversal(neighbor, g, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        int[][] edges = {{0,1}, {1,3}, {3,4}, {4,2},{2,0}, {3,5},{4,5},{5,6}};
        for(int[] edge : edges) graph.addEdge(edge[0], edge[1]);
        Traversal traversal = new Traversal();
       // traversal.bfsTraversal(1, graph);
        traversal.dfsTraversal(1, graph, new HashSet<>());
    }
}
