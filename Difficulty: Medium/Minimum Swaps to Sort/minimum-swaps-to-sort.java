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
            int n = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[n];
            String[] S = br.readLine().trim().split(" ");
            for(int i = 0; i < n; i++){
                nums[i] = Integer.parseInt(S[i]);
            }
            Solution obj = new Solution();
            int ans = obj.minSwaps(nums);
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
}

class Solution {
    
    public int minSwaps(int nums[]) {
        int n = nums.length;
        int ans = 0;  // To keep track of the total number of swaps
        
        // Step 1: Create an array of Pair objects, each containing
        // the value and its original index in the array
        ArrayList<Pair> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Pair(nums[i], i));
        }
        
        // Step 2: Sort the array of pairs based on the first element (the value of nums)
        Collections.sort(arr, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.first - p2.first;
            }
        });
        
        // Step 3: Initialize a visited array to keep track of visited elements
        boolean[] vis = new boolean[n];
        
        // Step 4: Find cycles in the permutation and calculate the number of swaps
        for (int i = 0; i < n; i++) {
            // If the element is already in its correct place or already visited
            if (arr.get(i).second == i || vis[i]) {
                continue;
            }
            
            // Find the cycle length
            int cycleSize = 0;
            int j = i;
            while (!vis[j]) {
                vis[j] = true;
                j = arr.get(j).second; // Move to the index where the current element should be
                cycleSize++;
            }
            
            // If there is a cycle of size `k`, it takes `k - 1` swaps to arrange it
            if (cycleSize > 1) {
                ans += (cycleSize - 1);
            }
        }
        
        return ans;  // Return the minimum number of swaps
    }
}





