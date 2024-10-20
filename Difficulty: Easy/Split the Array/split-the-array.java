//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;


// } Driver Code Ends
// User function Template for Java

class Solution {

    public static int countgroup(int arr[]) {
        int n = arr.length;
        int MOD = 1000000007;

        // Step 1: Calculate the XOR of the entire array
        int totalXOR = 0;
        for (int num : arr) {
            totalXOR ^= num;
        }

        // If the total XOR is not 0, it's impossible to split into two groups
        if (totalXOR != 0) {
            return 0;
        }

        // Step 2: Count the number of ways to split (2^(n-1) % MOD) and exclude the trivial case
        long result = 1;
        for (int i = 0; i < n - 1; i++) {
            result = (result * 2) % MOD;
        }

        // Subtract 1 to remove the trivial split where all elements are in one group
        result = (result - 1 + MOD) % MOD;

        return (int) result;
    }
}




//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            // int k = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String[] tokens = line.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> array = new ArrayList<>();

            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                array.add(Integer.parseInt(token));
            }

            int[] arr = new int[array.size()];
            int idx = 0;
            for (int i : array) arr[idx++] = i;
            Solution obj = new Solution();
            int ans = obj.countgroup(arr);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends