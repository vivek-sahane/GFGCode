//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class DriverClass {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<iPair>> adj = new ArrayList<ArrayList<iPair>>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<iPair>());
            }

            int i = 0;
            while (i++ < E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                iPair t1 = new iPair(v, w);
                iPair t2 = new iPair(u, w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }

            int src = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            ArrayList<Integer> res = ob.dijkstra(adj, src);

            for (i = 0; i < V; i++) System.out.print(res.get(i) + " ");
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class Solution {
    // Function to find the shortest distance of all the vertices
    // from the source vertex src.
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
 
        int V = adj.size();
        // to have sorted based on distance
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));

        // to store result
        ArrayList<Integer> dist = new ArrayList<>();
        for(int i = 0; i < V; i++) dist.add(Integer.MAX_VALUE);

        dist.set(src, 0);
        pq.add(new Pair(0, src));


        while(!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();

            for(iPair temp : adj.get(node)) {
                int adjNode = temp.first;
                int adjWeight = temp.second;

                if(dis + adjWeight < dist.get(adjNode)) {
                    dist.set(adjNode, dis + adjWeight);
                    pq.add(new Pair(dist.get(adjNode), adjNode));
                }
            }
        }

        return dist;
    }
    
}

class Pair {
    int distance;
    int node;
    
    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}





