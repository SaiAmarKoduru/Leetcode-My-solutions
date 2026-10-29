
import java.util.*;

class Solution {
    private int[][] up;
    private int[] depth;
    private int[] tin;
    private int[] tout;
    private int LOG;
    private Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        LOG = 32 - Integer.numberOfLeadingZeros(n);

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

       
        int capacity = 2 * n + 5;
        int[] stackNode = new int[capacity];
        int[] stackState = new int[capacity];

        int top = 0;
        int timer = 0;

        stackNode[top] = 0;
        stackState[top++] = 0;
        parent[0] = 0;
        up[0][0] = 0;

        while (top > 0) {
            int node = stackNode[--top];
            int state = stackState[top];

            if (state == 0) {
                
                tin[node] = timer++;

                
                stackNode[top] = node;
                stackState[top++] = 1;

                
                for (int i = graph[node].size() - 1; i >= 0; i--) {
                    int child = graph[node].get(i);

                    if (parent[child] != -1) {
                        continue;
                    }

                    parent[child] = node;
                    depth[child] = depth[node] + 1;
                    up[0][child] = node;

                    stackNode[top] = child;
                    stackState[top++] = 0;
                }
            } else {
                
                tout[node] = timer - 1;
            }
        }

        
        for (int k = 1; k < LOG; k++) {
            for (int node = 0; node < n; node++) {
                up[k][node] = up[k - 1][up[k - 1][node]];
            }
        }

        
        char[] chars = s.toCharArray();
        bit = new Fenwick(n);

        for (int node = 0; node < n; node++) {
            int mask = charMask(chars[node]);
            rangeXor(tin[node], tout[node], mask);
        }

        
        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.trim().split("\\s+");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                if (chars[node] == newChar) {
                    continue;
                }

                int difference =
                        charMask(chars[node]) ^ charMask(newChar);

                
                rangeXor(tin[node], tout[node], difference);
                chars[node] = newChar;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                
                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ charMask(chars[ancestor]);

                
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int charMask(char c) {
        return 1 << (c - 'a');
    }

    private void rangeXor(int left, int right, int mask) {
        bit.update(left, mask);
        bit.update(right + 1, mask);
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        
        int difference = depth[u] - depth[v];

        for (int k = LOG - 1; k >= 0; k--) {
            if ((difference & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        
        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        
        void update(int index, int value) {
            for (int i = index + 1; i < tree.length;
                 i += i & -i) {
                tree[i] ^= value;
            }
        }

        
        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0;
                 i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
