
class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;

        int[] sorted = score.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int rank = n - i;
            rankMap.put(sorted[i], rank);
        }

        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            int rank = rankMap.get(score[i]);

            if (rank == 1) {
                answer[i] = "Gold Medal";
            } else if (rank == 2) {
                answer[i] = "Silver Medal";
            } else if (rank == 3) {
                answer[i] = "Bronze Medal";
            } else {
                answer[i] = String.valueOf(rank);
            }
        }

        return answer;
    }
}
