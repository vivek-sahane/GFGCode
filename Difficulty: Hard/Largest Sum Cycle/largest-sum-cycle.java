// User function Template for Java

class Solution {
    
    public int ans;

    public void dfs(int node, int[] vis, int[] inRecu, int[] cnt, List<List<Integer>> adj) {
        vis[node] = 1;
        inRecu[node] = 1;

        for(int it : adj.get(node)) {
            if(it == -1) continue;
            if(vis[it] == 1 && inRecu[it]==1) {
                //System.out.println("final: "+it);
                int cycleLen = cnt[node] - cnt[it] + it;
               // System.out.println(cycleLen);
                ans = Math.max(ans, cycleLen);
            }
            else if(vis[it]==0 ) {
               // System.out.println(it);
                cnt[it] = cnt[it] + cnt[node];
                dfs(it, vis, inRecu, cnt, adj);
            }
        }
        inRecu[node] = 0;
        cnt[node] = node;
    }
    
    public long largesSumCycle(int N, int edges[]) {
        int n = edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n; i++) {
            adj.get(i).add(edges[i]);
        }

        ans = 0;

        int[] vis = new int[n];
        int[] inRecursion = new int[n];
        int[] cnt = new int[n];

        for(int i=0; i<n; i++) cnt[i] = i;
        
        for(int i=0; i<n; i++) {
            if(vis[i] == 0) {
                dfs(i, vis, inRecursion, cnt, adj);
            }
        }

        if(ans == 0) return -1;
        return ans;
    }
}