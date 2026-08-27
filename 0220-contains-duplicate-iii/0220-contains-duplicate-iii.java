import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long current = nums[i];

            
            Long candidate = set.ceiling(current - valueDiff);

            if (candidate != null && candidate <= current + valueDiff) {
                return true;
            }

            set.add(current);

            
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}