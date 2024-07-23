//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class gfg
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int num = sc.nextInt();
            int k = sc.nextInt();
            
            int arr[] = new int[num];
            for(int i = 0; i < num; i++)
                arr[i] = sc.nextInt();
            
            ArrayList <Integer> res = new Solution().nearlySorted(arr, num, k);
            for (int i = 0; i < res.size (); i++)
                System.out.print (res.get (i) + " ");
            System.out.println();
        }
    }
}


// } Driver Code Ends


class Solution {
    // Function to return the sorted array.
    ArrayList<Integer> nearlySorted(int arr[], int num, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Add the first k+1 elements to the min heap
        for (int i = 0; i < k + 1 && i < num; i++) {
            minHeap.add(arr[i]);
        }
        
        // For the rest of the elements, maintain the min heap of size k+1
        for (int i = k + 1; i < num; i++) {
            ans.add(minHeap.poll());
            minHeap.add(arr[i]);
        }
        
        // Add remaining elements from the heap to the answer list
        while (!minHeap.isEmpty()) {
            ans.add(minHeap.poll());
        }
        
        return ans;
    }
}

