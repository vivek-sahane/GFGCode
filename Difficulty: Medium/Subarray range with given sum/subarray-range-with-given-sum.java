//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;


// } Driver Code Ends
// User function Template for Java

class Solution {
    // Function to count the number of subarrays which add up to the given sum.
    static int subArraySum(int arr[], int tar) {
        HashMap<Long, Long> mp = new HashMap<>();
        int n = arr.length;
        int cnt = 0;
        long prefSum = 0;
        mp.put(0L, 1L);  // Initialize with prefix sum 0 and count 1
        
        for (int i = 0; i < n; i++) {
            prefSum += (long) arr[i];  // Update the prefix sum
            
            // Check if there is a prefix sum that when subtracted gives the target sum
            if (mp.containsKey(prefSum - tar)) {
                cnt += mp.get(prefSum - tar);
            }
            
            // Update the map with the current prefix sum
            mp.put(prefSum, mp.getOrDefault(prefSum, 0L) + 1);
        }
        return cnt;
    }
}



//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
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

            int tar = Integer.parseInt(br.readLine());
            Solution obj = new Solution();
            int res = obj.subArraySum(arr, tar);

            System.out.println(res);
            // System.out.println("~");
        }
    }
}
// } Driver Code Ends