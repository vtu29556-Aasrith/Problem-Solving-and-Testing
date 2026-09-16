import java.io.*;
import java.util.*;

public class Solution {

    static int[] manacher(int[] a) {
        int n = a.length;
        int[] radius = new int[n];

        int center = 0;
        int right = 0;

        for (int i = 0; i < n; i++) {

            if (i < right) {
                radius[i] = Math.min(
                        radius[2 * center - i],
                        right - i
                );
            } else {
                radius[i] = 1;
            }

            while (i - radius[i] >= 0 &&
                   i + radius[i] < n &&
                   a[i - radius[i]] == a[i + radius[i]]) {
                radius[i]++;
            }

            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }

        // Convert radius to number of characters
        for (int i = 0; i < n; i++) {
            radius[i]--;
        }

        return radius;
    }

    static class SparseTable {

        int[][] table;
        int[] log;

        SparseTable(int[] arr) {

            int n = arr.length;

            log = new int[n + 1];

            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }

            int levels = log[n] + 1;

            table = new int[levels][n];

            System.arraycopy(arr, 0, table[0], 0, n);

            for (int k = 1; k < levels; k++) {

                int len = 1 << k;
                int half = len >> 1;

                for (int i = 0; i + len <= n; i++) {
                    table[k][i] = Math.max(
                            table[k - 1][i],
                            table[k - 1][i + half]
                    );
                }
            }
        }

        int query(int left, int right) {

            if (left > right) {
                return 0;
            }

            int length = right - left + 1;
            int k = log[length];

            return Math.max(
                    table[k][left],
                    table[k][right - (1 << k) + 1]
            );
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        String s = br.readLine().trim();

        /*
         * Duplicate the string.
         *
         * Example:
         * abcde
         *
         * becomes:
         * abcdeabcde
         */
        String doubled = s + s;

        /*
         * Manacher transformation:
         *
         * abc
         *
         * becomes:
         *
         * #a#b#c#
         *
         * We use 26 as the separator because
         * input contains only a-z.
         *
         * Length = 4*n + 1
         */
        int transformedLength = 4 * n + 1;

        int[] transformed = new int[transformedLength];

        Arrays.fill(transformed, 26);

        for (int i = 0; i < 2 * n; i++) {
            transformed[2 * i + 1] =
                    doubled.charAt(i) - 'a';
        }

        /*
         * Find palindrome radius at every position.
         */
        int[] radius = manacher(transformed);

        /*
         * Range Maximum Query over radius[].
         */
        SparseTable rmq = new SparseTable(radius);

        StringBuilder output = new StringBuilder();

        /*
         * There are n rotations.
         */
        for (int rotation = 0; rotation < n; rotation++) {

            /*
             * Start and end of this rotation
             * in the transformed string.
             */
            int left = 2 * rotation + 1;

            int right =
                    2 * (rotation + n - 1) + 1;

            /*
             * Binary search the maximum palindrome length.
             */
            int low = 1;
            int high = n;
            int answer = 1;

            while (low <= high) {

                int mid = (low + high) / 2;

                /*
                 * A palindrome of length mid can have
                 * its center anywhere from:
                 *
                 * left + mid - 1
                 *
                 * to:
                 *
                 * right - mid + 1
                 *
                 * If the maximum radius in this range
                 * is >= mid, then such a palindrome exists.
                 */
                int centerLeft =
                        left + mid - 1;

                int centerRight =
                        right - mid + 1;

                if (centerLeft <= centerRight &&
                    rmq.query(centerLeft, centerRight) >= mid) {

                    answer = mid;
                    low = mid + 1;

                } else {
                    high = mid - 1;
                }
            }

            output.append(answer).append('\n');
        }

        System.out.print(output);
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna