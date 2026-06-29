
import java.util.*;
class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        long ans = 0;
        int current = mul;
        int n = nums.length;

        for (int i = n-1; i >= n-k; i--){
            if(current > 1){
                ans += 1L * nums[i] * current;
            }else{
                ans += nums[i];
            }
            current--;
        }
        return ans;
    }
}