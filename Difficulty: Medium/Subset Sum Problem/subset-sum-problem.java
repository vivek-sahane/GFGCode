//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {

            String input_line[] = read.readLine().trim().split("\\s+");
            int N = input_line.length;
            int arr[] = new int[N];
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            if (ob.isSubsetSum(arr, sum))
                System.out.println("true");
            else
                System.out.println("false");

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    
    private boolean f(int[] arr, int target, int n, int[][] dp) {
        if (target == 0) return true;
        if (n < 0) return false;  // Edge case: no elements left to choose
        
        if (dp[n][target] != -1) return dp[n][target] == 1;

        // take
        boolean take = false;
        if (arr[n] <= target) {
            take = f(arr, target - arr[n], n - 1, dp);
        }
        // not take 
        boolean nottake = f(arr, target, n - 1, dp);

        dp[n][target] = (take || nottake) ? 1 : 0;  // Memoize the result
        return take || nottake;
    }

    static boolean isSubsetSum(int arr[], int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return new Solution().f(arr, target, n - 1, dp);
    }
}

