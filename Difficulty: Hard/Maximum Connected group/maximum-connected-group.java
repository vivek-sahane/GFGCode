//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            Solution obj = new Solution();
            int ans = obj.MaxConnection(grid);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    public DisjointSet(int n){
        for(int i=0; i<=n; i++){
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if(node == parent.get(node)){
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return;
        if(rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        }
        else if(rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v , ulp_u);
        }
        else{
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if(size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        }
        else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Solution {

    public int MaxConnection(int grid[][]) {
        
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        
        //step -1 connect all 1's
        
        for(int row =0; row<n; row++) {
            for(int col=0; col<n; col++) {
                if(grid[row][col] == 0)continue;
                
                int[] dr = {-1,0,1,0};
                int[] dc = {0,-1,0,1};
                for(int ind =0;ind<4;ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];
                    if(isValid(newr, newc,n) && grid[newr][newc] == 1) {
                        int nodeNo = row*n+col;
                        int adjNodeNo = newr*n+newc;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }
        
        // step - 2 Connect all adjacent 1's & store their parent into hashset to avoid edge case
        //get size of each parent in st & add it 
        int mx =0;
        for(int row =0; row<n; row++) {
            for(int col=0; col<n; col++) {
                if(grid[row][col] == 1)continue;
                
                int[] dr = {-1,0,1,0};
                int[] dc = {0,-1,0,1};
                HashSet<Integer> components = new HashSet<>();
                
                for(int ind =0; ind<4;ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];
                    if(isValid(newr, newc,n) && grid[newr][newc] == 1) {
                        components.add(ds.findUPar(newr*n+newc));
                    }
                }
            
                int sizeTotal =0;
                for(Integer parents : components) {
                    sizeTotal += ds.size.get(parents);
                }
                mx = Math.max(mx, sizeTotal+1);
            }
        }
        
        //If matrix contains all 1's
        for(int cellNo =0; cellNo < n*n; cellNo++) {
            mx = Math.max(mx, ds.size.get(ds.findUPar(cellNo)));
        }
        
        return mx;
    }

    private boolean isValid(int row, int col, int n) {
        return row >= 0 && col >= 0 && row < n && col < n;
    }
}






















