//{ Driver Code Starts
// Initial Template for Java

// Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;


// } Driver Code Ends
// User function Template for Java


class Solution {

    String roundToNearest(String str) {
        // Complete the function
        int n1=str.length();
        int l=str.charAt(n1-1)-'0';
        
        if(l<=5){
            return str.substring(0,n1-1)+"0";
        }
        int c=1;
        String n="0";
        int i=n1-2;
        for(;i>=0&&c!=0;i--){
            int k=str.charAt(i)-'0';
            k+=c;
            if(k>=10){
                k=k%10;
                c=1;
            
            }else{
                c=0;
                
            }
            n=(""+k)+n;
            
        }
        if(c!=0){
            return (""+1)+n;
        }
        if(i<0){
            return n;
        }
        return str.substring(0,i+1)+n;
    }
}



//{ Driver Code Starts.

// Driver class
class Array {

    // Driver code
    public static void main(String[] args) throws IOException {
        // Taking input using buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        // looping through all testcases
        while (testcases-- > 0) {

            String str = br.readLine().trim();

            Solution obj = new Solution();

            String res = obj.roundToNearest(str);

            System.out.println(res);
        }
    }
}

// } Driver Code Ends