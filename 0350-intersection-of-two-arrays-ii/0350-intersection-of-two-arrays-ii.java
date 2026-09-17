import java.util.*;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> result = new ArrayList<>();

       
        for (int num : nums1) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

       
        for (int num : nums2) {
            if (count.getOrDefault(num, 0) > 0) {
                result.add(num);
                count.put(num, count.get(num) - 1);
            }
        }

        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}