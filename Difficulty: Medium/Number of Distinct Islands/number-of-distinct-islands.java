//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            Solution ob = new Solution();
            int ans = ob.countDistinctIslands(grid);
            System.out.println(ans);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair pair = (Pair) o;
    return first == pair.first && second == pair.second;
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }
}

class Solution {
  private void dfs(int row, int col, int[][] vis, int[][] grid, List<Pair> vec, int row0, int col0) {
    // mark the cell as visited
    vis[row][col] = 1;

    // store relative coordinates as Pair
    vec.add(new Pair(row - row0, col - col0));
    int n = grid.length;
    int m = grid[0].length;

    // delta row and delta column
    int delrow[] = {-1, 0, +1, 0}; 
    int delcol[] = {0, -1, 0, +1}; 

    // traverse all 4 neighbors
    for (int i = 0; i < 4; i++) {
      int nrow = row + delrow[i];
      int ncol = col + delcol[i];
      // check for valid unvisited land neighbor coordinates
      if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
        vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
        dfs(nrow, ncol, vis, grid, vec, row0, col0);
      }
    }
  }

  int countDistinctIslands(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] vis = new int[n][m];
    HashSet<List<Pair>> st = new HashSet<>();

    // traverse the grid
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // if not visited and is a land cell
        if (vis[i][j] == 0 && grid[i][j] == 1) {
          List<Pair> vec = new ArrayList<>();
          dfs(i, j, vis, grid, vec, i, j);
          // store the island shape in HashSet
          st.add(vec);
        }
      }
    }
    return st.size();
  }
}




