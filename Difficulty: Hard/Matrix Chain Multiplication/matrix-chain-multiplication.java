//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Geeks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int g = 0; g < t; g++) {
            String[] str = (br.readLine()).trim().split(" ");
            int arr[] = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            System.out.println(new Solution().matrixMultiplication(arr));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    static int matrixMultiplication(int arr[]) {
        
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] row:dp) {
            Arrays.fill(row,-1);
        }
        
        for(int i=1;i<n;i++) {
            dp[i][i]=0;
        }
        
        for(int i=n-1; i>=1; i--) {
            for(int j=i+1; j<n; j++) {
                int minOper = Integer.MAX_VALUE;
                
                for(int k=i;k<=j-1;k++) {
                    int opr = dp[i][k] + dp[k+1][j] + arr[i-1] * arr[k] * arr[j];
                    
                    minOper = Math.min(minOper, opr);
                }
                dp[i][j] = minOper;
            }
        }
        return dp[1][n-1];
    }
}