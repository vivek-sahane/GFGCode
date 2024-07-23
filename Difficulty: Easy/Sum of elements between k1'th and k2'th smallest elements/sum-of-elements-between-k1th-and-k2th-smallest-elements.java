//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main(String[] args) throws IOException
	{
	        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t =
            Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            long n = Long.parseLong(br.readLine().trim());
            long a[] = new long[(int)n];
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(inputLine[i]);
            }
            
            StringTokenizer stt = new StringTokenizer(br.readLine());
            long k1 = Long.parseLong(stt.nextToken());
            long k2 = Long.parseLong(stt.nextToken());
            
            Solution obj = new Solution();
            System.out.println( obj.sumBetweenTwoKth(a, n, k1, k2) );
            
        }
	}
}
// } Driver Code Ends


class Solution {
  public static long sumBetweenTwoKth(long A[], long N, long K1, long K2) {
    // Your code goes here
    PriorityQueue<Long> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      pq.add(A[i]);
    }
    long ans = 0;
    int i = 1;
    int j = 1;
    while (j < K2) {
      if (i > K1) {
        ans += pq.peek();
        // System.out.println(pq.peek());
      }
      pq.poll();
      i++;
      j++;
    }
    return ans;
  }
}




