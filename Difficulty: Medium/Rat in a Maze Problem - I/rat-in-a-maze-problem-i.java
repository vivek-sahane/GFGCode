//{ Driver Code Starts
// Initial Template for Java

import java.util.*;

class Rat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) a[i][j] = sc.nextInt();

            Solution obj = new Solution();
            ArrayList<String> res = obj.findPath(a);
            Collections.sort(res);
            if (res.size() > 0) {
                for (int i = 0; i < res.size(); i++) System.out.print(res.get(i) + " ");
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}

// } Driver Code Ends

class Solution {
    ArrayList<String> ans;

    public void helper(int sr, int sc, int er, int ec, String path, boolean[][] isVisited, int[][] mat) {
        // Boundary and condition checks
        if (sr < 0 || sc < 0 || sr > er || sc > ec || isVisited[sr][sc] || mat[sr][sc] == 0) return;

        // Destination reached
        if (sr == er && sc == ec) {
            ans.add(path);
            return;
        }

        // Mark the cell as visited
        isVisited[sr][sc] = true;

        // Move right
        helper(sr, sc + 1, er, ec, path + "R", isVisited, mat);
        // Move down
        helper(sr + 1, sc, er, ec, path + "D", isVisited, mat);
        // Move left
        helper(sr, sc - 1, er, ec, path + "L", isVisited, mat);
        // Move up
        helper(sr - 1, sc, er, ec, path + "U", isVisited, mat);

        // Backtrack - Unmark the cell as visited
        isVisited[sr][sc] = false;
    }

    public ArrayList<String> findPath(int[][] mat) {
        ans = new ArrayList<>();
        int n = mat.length;

        // Check if the source or destination is blocked
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0) return ans;

        boolean[][] isVisited = new boolean[n][n];
        helper(0, 0, n - 1, n - 1, "", isVisited, mat);

        return ans;
    }
}
