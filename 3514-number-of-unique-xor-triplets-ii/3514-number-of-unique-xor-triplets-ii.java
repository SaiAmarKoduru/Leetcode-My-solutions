class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] one = new boolean[MAX];
        boolean[] two = new boolean[MAX];
        boolean[] three = new boolean[MAX];

        for (int x : nums) {
            one[x] = true;
        }

        for (int i = 0; i < MAX; i++) {
            if (!one[i]) continue;
            for (int x : nums) {
                two[i ^ x] = true;
            }
        }

        for (int i = 0; i < MAX; i++) {
            if (!two[i]) continue;
            for (int x : nums) {
                three[i ^ x] = true;
            }
        }

        int ans = 0;
        for (boolean b : three) {
            if (b) ans++;
        }

        return ans;
    }
}