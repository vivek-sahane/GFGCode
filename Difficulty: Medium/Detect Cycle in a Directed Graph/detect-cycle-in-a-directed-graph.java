//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++) list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new Solution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

class Solution {
    
    private boolean dfs(int node ,ArrayList<ArrayList<Integer>> adj, int[]vis, int[]path){
        vis[node]=1;
        path[node]=1;
        
        for(int it:adj.get(node)){
            if(vis[it]==0){
                if(dfs(it,adj,vis,path)==true)return true;
            }
            else if(path[it]==1){
                return true;
            }
        }
        path[node]=0;
        return false;
    }
    
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[]vis = new int[V];
        int[]path = new int[V];
        
        for(int i=0; i<V;i++){
            if(vis[i]==0){
                if(dfs(i,adj,vis,path)==true)return true;
            }
        }
        return false;
    }
}