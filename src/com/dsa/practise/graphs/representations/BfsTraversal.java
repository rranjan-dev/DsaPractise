package com.dsa.practise.graphs.representations;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsTraversal {
    private static String bfsTraversal(AdjacencyListRepresentation adjacencyList, int startIndex) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[adjacencyList.adjList.size()];
        queue.add(startIndex);
        visited[startIndex] = true;
        while(!queue.isEmpty()){
            Integer curr = queue.remove();
            List<AdjacencyListRepresentation.Edge> i = adjacencyList.adjList.get(curr);
            for (AdjacencyListRepresentation.Edge e : i) {
                int ele = e.dest;
                if(!visited[ele]){
                    queue.add(ele);
                    visited[ele] = true;
                }
            }
            sb.append(curr).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyListRepresentation adjacencyList = new AdjacencyListRepresentation(5);
        adjacencyList.addUndirectedEdge(0,1);
        adjacencyList.addUndirectedEdge(1,2);
        adjacencyList.addUndirectedEdge(2,3);
        adjacencyList.addUndirectedEdge(3,4);
        adjacencyList.addUndirectedEdge(4,1);
        String bfsOutput = bfsTraversal(adjacencyList, 0);
        System.out.println("bfs Output is  : " + bfsOutput);
    }
}
