import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        int n = nums.length;
        int negativeCount = n / 2;
        long score = 0;

        
        for (int i = 0; i < negativeCount; i++) {
            score -= squares[i];
        }

        
        for (int i = negativeCount; i < n; i++) {
            score += squares[i];
        }

        return score;
    }
}