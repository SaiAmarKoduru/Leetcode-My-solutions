import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        List<Integer> zeroGroups = new ArrayList<>();
        int ones = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.set(zeroGroups.size() - 1,
                            zeroGroups.get(zeroGroups.size() - 1) + 1);
                } else {
                    zeroGroups.add(1);
                }
            }
        }

        int best = 0;
        for (int i = 0; i + 1 < zeroGroups.size(); i++) {
            best = Math.max(best, zeroGroups.get(i) + zeroGroups.get(i + 1));
        }

        return ones + best;
    }
}