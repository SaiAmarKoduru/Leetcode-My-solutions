class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        
        long[] prefixSum = new long[n + 1];

       
        int[] prefixCount = new int[n + 1];

      
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

       
        long[] prefixValue = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i];
            prefixCount[i + 1] = prefixCount[i];
            prefixValue[i + 1] = prefixValue[i];

            int d = s.charAt(i) - '0';

            if (d != 0) {
                prefixSum[i + 1] += d;
                prefixCount[i + 1]++;
                prefixValue[i + 1] =
                        (prefixValue[i] * 10 + d) % MOD;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            long sum = prefixSum[r + 1] - prefixSum[l];

            int cnt = prefixCount[r + 1] - prefixCount[l];

            long left =
                    (prefixValue[l] * pow10[cnt]) % MOD;

            long x =
                    (prefixValue[r + 1] - left + MOD) % MOD;

            ans[i] = (int)((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}