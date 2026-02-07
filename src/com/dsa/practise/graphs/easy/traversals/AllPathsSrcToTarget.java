package com.dsa.practise.graphs.easy.traversals;

import com.dsa.practise.graphs.easy.representation.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
       1----3
      /     | \
     0      |   5 -- 6
      \     | /
       2----4

 */

public class AllPathsSrcToTarget {
    private void modifiedDfs(Graph g, Set<Integer> visited, int src, int target, StringBuilder sb){
        visited.add(src);
        sb.append(src);
        if(src==target){
            System.out.println("possible path: " + sb);
        }else{
            List<Integer> neighbors = g.getAdj().get(src);
            for(Integer neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    modifiedDfs(g, visited, neighbor, target, sb);
                }
            }
        }
        visited.remove(src);
        if(!sb.isEmpty()){
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        int[][] edges = {{0,1}, {0,2}, {1,3}, {2,4}, {3,4}, {3,5}, {4,5}, {5,6}};
        for(int[] edge : edges) g.addEdge(edge[0], edge[1]);
        int src = 0;
        int target=5;
        StringBuilder sb = new StringBuilder();
        Set<Integer> visited = new HashSet<>();
        AllPathsSrcToTarget allPathsSrcToTarget = new AllPathsSrcToTarget();
        allPathsSrcToTarget.modifiedDfs(g, visited, src, target, sb);
    }
}
