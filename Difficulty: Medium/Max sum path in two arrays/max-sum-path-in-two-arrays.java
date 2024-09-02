//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        for (int k = 0; k < t; k++) {
            List<Integer> arr1 = new ArrayList<>();
            String input = sc.nextLine();
            Scanner lineScanner = new Scanner(input);
            while (lineScanner.hasNextInt()) {
                arr1.add(lineScanner.nextInt());
            }
            lineScanner.close();

            List<Integer> arr2 = new ArrayList<>();
            input = sc.nextLine();
            lineScanner = new Scanner(input);
            while (lineScanner.hasNextInt()) {
                arr2.add(lineScanner.nextInt());
            }
            lineScanner.close();

            Solution ob = new Solution();
            int ans = ob.maxPathSum(arr1, arr2);
            System.out.println(ans);
        }

        sc.close();
    }
}

// } Driver Code Ends



class Solution {

    private int helper(List<Integer> arr1, List<Integer> arr2) {
        int sum1 = 0, sum2 = 0, maxSum = 0;
        int n = arr1.size(), m = arr2.size();
        int i = 0, j = 0;

        // Traverse both arrays
        while (i < n && j < m) {
            // If arr1[i] is smaller, add it to sum1
            if (arr1.get(i) < arr2.get(j)) {
                sum1 += arr1.get(i);
                i++;
            }
            // If arr2[j] is smaller, add it to sum2
            else if (arr1.get(i) > arr2.get(j)) {
                sum2 += arr2.get(j);
                j++;
            }
            // If arr1[i] equals arr2[j], add the max of the two sums to maxSum
            else {
                maxSum += Math.max(sum1, sum2) + arr1.get(i);
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        // Add remaining elements
        while (i < n) {
            sum1 += arr1.get(i);
            i++;
        }

        while (j < m) {
            sum2 += arr2.get(j);
            j++;
        }

        // Add the max of remaining sums to maxSum
        maxSum += Math.max(sum1, sum2);

        return maxSum;
    }

    public int maxPathSum(List<Integer> arr1, List<Integer> arr2) {
        // Assuming arr1 and arr2 are not necessarily of the same size
        return helper(arr1, arr2);
    }
}
