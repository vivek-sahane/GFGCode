//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++){
                String[] S = br.readLine().trim().split(" ");
                for(int j = 0; j < m; j++){
                    grid[i][j] = Integer.parseInt(S[j]);
                }
            }
            Solution obj = new Solution();
            int[][] ans = obj.nearest(grid);
            for(int i = 0; i < ans.length; i++){
                for(int j = 0; j < ans[i].length; j++){
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
// } Driver Code Ends

class Pair {
    int first;
    int second;
    int third;
    
    public Pair(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class Solution {
    // Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // visited and distance matrix
        int vis[][] = new int[n][m];
        int dist[][] = new int[n][m];
        // Queue to store <coordinates, steps>
        Queue<Pair> q = new LinkedList<>();
        
        // Traverse the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Start BFS if the cell contains 1
                if (grid[i][j] == 1) {
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 1;
                } else {
                    // Mark unvisited cells
                    vis[i][j] = 0;
                }
            }
        }

        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, +1, 0, -1};

        // BFS traversal
        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            int steps = q.peek().third;
            q.remove();
            dist[row][col] = steps;
            
            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                
                // Check for valid unvisited cell
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0) {
                    vis[nrow][ncol] = 1;
                    q.add(new Pair(nrow, ncol, steps + 1));
                }
            }
        }

        // Return the distance matrix
        return dist;
    }
}

