import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        
        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[0]) {
                allEqual = false;
                break;
            }
        }

        if (allEqual) {
            int sum = 0;
            for (int k = 1; k <= n / k; k++) {
                if (n % k == 0) {
                    sum += k;
                    int other = n / k;
                    if (other != k) {
                        sum += other;
                    }
                }
            }
            return sum;
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int answer = 0;

        
        for (int k = 1; k <= n / k; k++) {
            if (n % k != 0) {
                continue;
            }

            if (isSortable(nums, sorted, k)) {
                answer += k;
            }

            int other = n / k;
            if (other != k && isSortable(nums, sorted, other)) {
                answer += other;
            }
        }

        return answer;
    }

    private boolean isSortable(int[] nums, int[] sorted, int k) {
        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            if (!isRotation(nums, sorted, start, k)) {
                return false;
            }
        }

        return true;
    }

    private boolean isRotation(int[] nums, int[] sorted, int start, int len) {
        int[] lps = new int[len];

        
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && nums[start + i] != nums[start + j]) {
                j = lps[j - 1];
            }

            if (nums[start + i] == nums[start + j]) {
                j++;
            }

            lps[i] = j;
        }

       
        for (int i = 0, j = 0; i < 2 * len - 1; i++) {
            int value = sorted[start + i % len];

            while (j > 0 && value != nums[start + j]) {
                j = lps[j - 1];
            }

            if (value == nums[start + j]) {
                j++;
            }

            if (j == len) {
                return true;
            }
        }

        return false;
    }
}