//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] adj = new int[m][3];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < 3; j++) {
                    adj[i][j] = sc.nextInt();
                }
            }

            int dist = sc.nextInt();
            Solution obj = new Solution();
            int ans = obj.findCity(n, m, adj, dist);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    int findCity(int n, int m, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        
        // Initialize the distance array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dis[i][j] = 0; // Distance to self is 0
                } else {
                    dis[i][j] = Integer.MAX_VALUE; // Infinite for other nodes initially
                }
            }
        }
        
        // Populate initial distances from edges
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            dis[u][v] = w;
            dis[v][u] = w;
        }
        
        // Floyd-Warshall Algorithm to calculate shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][k] != Integer.MAX_VALUE && dis[k][j] != Integer.MAX_VALUE) {
                        dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                    }
                }
            }
        }
        
        int cntCity = n; // Start with the maximum possible number of cities
        int cityNo = -1;

        // Find the city with the smallest number of reachable cities
        for (int city = 0; city < n; city++) {
            int cnt = 0;
            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (dis[city][adjCity] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= cntCity) { // Tie-breaking: Choose the city with the higher number
                cntCity = cnt;
                cityNo = city;
            }
        }
        return cityNo;
    }
}



