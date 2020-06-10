package com.example.codetesting.Algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DfsBfs {
    /**
     * Dfs(깊이 우선 탐색), Bfs(너비 우선 탐색)
     */
    public static void example(){

        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();
        int line = sc.nextInt();
        int startNumber = sc.nextInt();
        int startIndex = startNumber - 1;

        int[][] a = new int[row][row];


        for(int i=1;i<=line;i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            a[r-1][c-1] = 1;
            a[c-1][r-1] = 1;

        }

        dfs(a, new int[row], startIndex);
        System.out.println();
        bfs(a, new int[row], startIndex);
    }

    /**
     * 인접행렬 DFS (재귀)
     * @param a
     * @param visited
     * @param startIndex
     */
    private static void dfs(int[][] a, int[] visited, int startIndex) {
        visited[startIndex] = 1;
        System.out.print((startIndex+1) + " ");
        for(int i=0;i<a[0].length;i++){
            if(a[startIndex][i] == 1 && visited[i] == 0){
                dfs(a, visited, i);
            }
        }
    }

    /**
     * 인접행렬 BFS (큐)
     * @param a
     * @param visited
     * @param startIndex
     */
    private static void bfs(int[][] a, int[] visited, int startIndex){

        visited[startIndex] = 1;
        Queue<Integer> queue =  new LinkedList<>();
        queue.add(startIndex);

        while(!queue.isEmpty()){
            int num = queue.poll();
            visited[num] = 1;
            System.out.print((num+1) + " ");
            for(int i=0;i<a[0].length;i++){
                if(a[num][i] ==1 && visited[i] == 0){
                    queue.add(i);
                    visited[i] = 1;
                }
            }
        }
    }
}


