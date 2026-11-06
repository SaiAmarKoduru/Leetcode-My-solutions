
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        
        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        
        Map<Integer, Integer> map = new HashMap<>();

        int[] answer = new int[queries.length];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {

            int currentTime = query[0];
            int originalIndex = query[1];

            int startTime = currentTime - x;

            
            while (right < logs.length && logs[right][1] <= currentTime) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                if (map.get(server) == 1) {
                    activeServers++;
                }

                right++;
            }

            
            while (left < right && logs[left][1] < startTime) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    activeServers--;
                    map.remove(server);
                }

                left++;
            }

            
            answer[originalIndex] = n - activeServers;
        }

        return answer;
    }
}
