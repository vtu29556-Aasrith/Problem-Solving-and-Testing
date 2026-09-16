import java.util.*;

public class Solution {

    public static List<Integer> maxSubarray(List<Integer> arr) {

        // Maximum subarray sum (Kadane's Algorithm)
        int maxSubarray = arr.get(0);
        int current = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {
            current = Math.max(arr.get(i), current + arr.get(i));
            maxSubarray = Math.max(maxSubarray, current);
        }

        // Maximum subsequence sum
        int maxSubsequence = 0;
        int maxElement = arr.get(0);

        for (int x : arr) {
            if (x > 0) {
                maxSubsequence += x;
            }

            maxElement = Math.max(maxElement, x);
        }

        // If all numbers are negative
        if (maxSubsequence == 0) {
            maxSubsequence = maxElement;
        }

        return Arrays.asList(maxSubarray, maxSubsequence);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            List<Integer> arr = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                arr.add(sc.nextInt());
            }

            List<Integer> result = maxSubarray(arr);

            System.out.println(result.get(0) + " " + result.get(1));
        }

        sc.close();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna