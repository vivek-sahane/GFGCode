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
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


class Solution {
    
    int minimumMultiplications(int[] arr, int start, int end) {
        
        // Base Case : if both are equal then reached to the self node can be done in '0' steps.
        if (start == end) return 0;

        // Our number cannnot exceed the below limit
        // 0 to 9999
        // As , soon as our number go beyond 10^5 then we'll take modulo and again it comes to the range of 0 to 9999.
        int dist[] = new int[100000];
        
        // Initially all the nodes are reachable at a INFINITE distance.
        Arrays.fill (dist , (int)(1e9));

        // '0'th node can be reached within 0 steps.
        dist[0] = 0;

        Queue<Pair> q = new LinkedList<>();
        
        // Inserting the source node , which is reachable with '0' steps.
        q.add (new Pair (0 , start));

        while (!q.isEmpty ()) {

            // 1. Remove
            Pair pr = q.poll ();

            int steps = pr.steps;
            int node = pr.node;

            // 2. We had reached to the end successfully.
            if (node == end) return steps;

            // 3. Getting all the neighbours of a node.
            for (int i = 0 ; i < arr.length ; i++) {
                
                // Multiplying currentNode i.e. newStart with the array elements to get the neighbours.
                int nbrNode = (node * arr[i]) % 100000;
                
                // 4. Relaxation : If we can reach to the nbrNode with fewer steps then relax the distance of nbrNode.
                if ((steps + 1) < dist[nbrNode]) {

                    dist[nbrNode] = steps + 1;
                    q.add (new Pair (dist[nbrNode] , nbrNode));
                }
            }
        }

        // We didn't reached to the end.
        return -1;
    }
}

class Pair {

    int steps;
    int node;

    public Pair (int steps , int node) {

        this.steps = steps;
        this.node = node;
    }
}


