import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] countDiv = new long[max + 1];

        for (int d = 1; d <= max; d++) {
            long cnt = 0;
            for (int m = d; m <= max; m += d) {
                cnt += freq[m];
            }
            countDiv[d] = cnt;
        }

        long[] gcdCount = new long[max + 1];

        for (int d = max; d >= 1; d--) {
            long pairs = countDiv[d] * (countDiv[d] - 1) / 2;

            for (int m = d + d; m <= max; m += d) {
                pairs -= gcdCount[m];
            }

            gcdCount[d] = pairs;
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + gcdCount[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i] + 1;

            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (prefix[mid] >= q) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            ans[i] = lo;
        }

        return ans;
    }
}