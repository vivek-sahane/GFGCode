//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
// Position this line where user code will be pasted.

class GFG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    int x = sc.nextInt();
                    temp.add(x);
                }
                adj.add(temp);
            }

            Solution obj = new Solution();
            System.out.println(obj.countPaths(n, adj));
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


class Pair
{
    long dist;
    int node;
    Pair(long dist,int node)
    {
        this.dist=dist;
        this.node=node;
    }
}
class Solution {

    static int countPaths(int n, List<List<Integer>> roads) {
        ArrayList < ArrayList < Pair >> adj = new ArrayList < > ();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList < > ());
        }
        int m = roads.size();
        for (int i = 0; i < m; i++) {
            int u = roads.get(i).get(0);
            int v = roads.get(i).get(1);
            int w = roads.get(i).get(2);
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }
        int ways[]=new int[n];
        long dist[]=new long[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Pair> q=new PriorityQueue<>((x, y) -> Long.compare(x.dist, y.dist));
        dist[0]=0;
        ways[0] = 1;
        q.add(new Pair((long)0,0));
        int mod = (int)(1e9 + 7);
        while(!q.isEmpty())
        {
            Pair p=q.poll();
            int node=p.node;
            long d=p.dist;
            if (d > dist[node]) continue;

            for(Pair x:adj.get(node))
            {
                int neighbor=x.node;
                long dst=x.dist;
                if(d+dst<dist[neighbor])
                {
                    dist[neighbor]=dst+d;
                    q.add(new Pair(dist[neighbor],neighbor));
                    ways[neighbor] = ways[node]%mod;
                }
                else if(d+dst==dist[neighbor])
                {
                    ways[neighbor] = (ways[neighbor] + ways[node]) %mod;
                }
            }
        }
        return ways[n - 1] ;
    }
}


