class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];
        return solve(nums, 0, n - 1, dp) >= 0;
    }

    private int solve(int[] nums, int l, int r, Integer[][] dp) {
        if (l == r) return nums[l];
        if (dp[l][r] != null) return dp[l][r];

        int left = nums[l] - solve(nums, l + 1, r, dp);
        int right = nums[r] - solve(nums, l, r - 1, dp);

        return dp[l][r] = Math.max(left, right);
    }
}