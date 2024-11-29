//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String s[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                s = br.readLine().trim().split(" ");
                edges[i][0] = Integer.parseInt(s[0]);
                edges[i][1] = Integer.parseInt(s[1]);
                edges[i][2] = Integer.parseInt(s[2]);
            }

            List<Integer> list = new Solution().shortestPath(n, m, edges);

            ot.println(list.get(0));
            if (list.get(0) != -1 && !check(list, edges)) ot.println(-1);
        }
        ot.close();
    }

    static boolean check(List<Integer> list, int edges[][]) {
        Set<Integer> hs = new HashSet<>();
        Map<Integer, Map<Integer, Integer>> hm = new HashMap<>();
        for (int i = 1; i < list.size(); i++) hs.add(list.get(i));
        for (int x[] : edges) {
            if (hs.contains(x[0]) || hs.contains(x[1])) {
                if (!hm.containsKey(x[0])) hm.put(x[0], new HashMap<>());
                if (!hm.containsKey(x[1])) hm.put(x[1], new HashMap<>());
                hm.get(x[0]).put(x[1], x[2]);
                hm.get(x[1]).put(x[0], x[2]);
            }
        }
        int sum = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            if (!hm.containsKey(list.get(i)) ||
                !hm.get(list.get(i)).containsKey(list.get(i + 1)))
                return false;
            sum += hm.get(list.get(i)).get(list.get(i + 1));
        }
        return sum == list.get(0);
    }
}

// } Driver Code Ends

class Solution {
    class pair{
        int v;
        int w;
        pair(int v,int w){
            this.v = v;
            this.w = w;
        }
    }
    class pqpair{
        int distance;
        int node;
        pqpair(int distance,int node){
            this.distance = distance;
            this.node = node;
        }
    }
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        //  Code Here.
        ArrayList<ArrayList<pair>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj.get(u).add(new pair(v,w));
            adj.get(v).add(new pair(u,w));
        }
        int dist[] = new int[n+1];
        int parent[] = new int[n+1];
        for(int i=0;i<=n;i++){
            dist[i] = (int)(1e9);
        }
        PriorityQueue<pqpair> pq = new PriorityQueue<>((a,b) -> a.distance-b.distance);
        dist[1] = 0;
        parent[1] = 1;
        pq.add(new pqpair(0,1));
        while(!pq.isEmpty()){
            int distance = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();
            for(int i=0;i<adj.get(node).size();i++){
                int edgeweight = adj.get(node).get(i).w;
                int adjnode = adj.get(node).get(i).v;
                if((edgeweight+distance) < dist[adjnode]){
                    parent[adjnode] = node;
                    dist[adjnode] = edgeweight+distance;
                    pq.add(new pqpair(dist[adjnode],adjnode));
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        if(dist[n] == (1e9)){
            path.add(-1);
            return path;
        }
        int node = n;
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        path.add(dist[n]);
        Collections.reverse(path);
        return path;
    }
}



