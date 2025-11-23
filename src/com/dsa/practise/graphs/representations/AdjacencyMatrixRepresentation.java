package com.dsa.practise.graphs.representations;

public class AdjacencyMatrixRepresentation {
    int v;
    int[][] adjMatrix;
    AdjacencyMatrixRepresentation(int v){
        this.v = v;
        this.adjMatrix = new int[v][v];
    }

    void addEdge(int src, int dest){
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1;
    }

    void printGraph(){
        for (int i=0; i<v; i++){
            for(int j=0; j<v; j++){
                System.out.print(adjMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrixRepresentation adjMatrix = new AdjacencyMatrixRepresentation(5);
        adjMatrix.addEdge(0, 1);
        adjMatrix.addEdge(1, 2);
        adjMatrix.addEdge(2, 3);
        adjMatrix.addEdge(3, 4);
        adjMatrix.addEdge(4, 1);
        adjMatrix.printGraph();
    }
}
