//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while (t-- > 0) {

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

            // calling maxSubarraySum() function
            System.out.println(obj.maxSubarraySum(arr));
        }
    }
}

// } Driver Code Ends


// User function Template for Java
class Solution {
    
    long helper(int[] arr, int i, long[] maxEndingHere) {
        // Base case: When i is 0, max subarray sum is the first element itself.
        if (i == 0) {
            maxEndingHere[i] = arr[i];
            return arr[i];
        }
        
        // If we've already computed the max subarray sum for this index, return it
        if (maxEndingHere[i] != Long.MIN_VALUE) {
            return maxEndingHere[i];
        }

        // Recursive case: either take the element alone or extend the previous subarray
        maxEndingHere[i] = Math.max(arr[i], arr[i] + helper(arr, i - 1, maxEndingHere));

        return maxEndingHere[i];
    }
    
    // Function to find the sum of the contiguous subarray with maximum sum.
    long maxSubarraySum(int[] arr) {
        int n =arr.length;
        // Edge case: If the array has only one element, return it
        if (n == 1) {
            return arr[0];
        }

        // Array to store max subarray sums ending at each index
        long[] maxEndingHere = new long[n];
        
        // Initialize the array with a value to denote uncomputed values
        for (int i = 0; i < n; i++) {
            maxEndingHere[i] = Long.MIN_VALUE;
        }

        // Start recursion from the last index and get the result
        helper(arr, n - 1, maxEndingHere);

        // Find the overall maximum from the array maxEndingHere
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, maxEndingHere[i]);
        }
        
        return maxSum;
    }
}
