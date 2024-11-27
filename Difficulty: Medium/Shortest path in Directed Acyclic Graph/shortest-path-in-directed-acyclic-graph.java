//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] edge = new int[m][3];
            for (int i = 0; i < m; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
                edge[i][2] = sc.nextInt();
            }
            Solution obj = new Solution();
            int res[] = obj.shortestPath(n, m, edge);
            for (int i = 0; i < n; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    
    private void topoSort(int node, List<List<Pair>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;
        for (Pair neighbor : adj.get(node)) {
            int v = neighbor.first;
            if (vis[v] == 0) {
                topoSort(v, adj, vis, st);
            }
        }
        st.push(node);
    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        // Adjacency list representation of graph
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Fill adjacency list
        for (int i = 0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new Pair(v, wt));
        }

        // Perform topological sort
        int[] vis = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                topoSort(i, adj, vis, st);
            }
        }

        // Initialize distances with a large value
        int[] dist = new int[N];
        Arrays.fill(dist, (int)(1e9));
        dist[0] = 0;

        // Process nodes in topological order
        while (!st.isEmpty()) {
            int node = st.pop();
            if (dist[node] != (int)(1e9)) { // If the node is reachable
                for (Pair neighbor : adj.get(node)) {
                    int v = neighbor.first;
                    int wt = neighbor.second;
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        // Replace unreachable distances with -1
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int)(1e9)) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}


