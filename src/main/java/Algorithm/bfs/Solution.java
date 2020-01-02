package Algorithm.bfs;

import java.util.*;

public class Solution {
    class State {
        int x;
        int y;
        int k;

        public State(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    //    1293. Shortest Path in a Grid with Obstacles Elimination
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] seen = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                seen[i][j] = -1;
            }
        }
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        int step = -1;
        ArrayDeque<State> pq = new ArrayDeque<>();
        State state = new State(0, 0, 0);
        pq.offer(state);
        seen[0][0] = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            while (size-- > 0) {
                State cur = pq.poll();
                if (cur.x == m - 1 && cur.y == n - 1) {
                    return step;
                }
                for (int i = 0; i < 4; i++) {
                    int x = cur.x + dirs[i];
                    int y = cur.y + dirs[i + 1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    int o = cur.k + grid[x][y];
                    if (o > k) {
                        continue;
                    }
                    if (seen[x][y] != -1 && seen[x][y] <= o) {
                        continue;
                    }
                    seen[x][y] = o;
                    pq.offer(new State(x, y, o));
                }
            }
            step++;
        }
        return step;
    }

    //    1298. Maximum Candies You Can Get from Boxes
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int[] found = new int[status.length];
        int[] hasKey = new int[status.length];
        for (int i = 0; i < status.length; i++) {
            hasKey[i] = status[i];
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int b : initialBoxes) {
            found[b] = 1;
            if (hasKey[b] > 0) {
                q.offer(b);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int b = q.poll();
            ans += candies[b];
            for (int t : containedBoxes[b]) {
                found[t] = 1;
                if (hasKey[t] > 0) {
                    q.offer(t);
                }
            }
            for (int k : keys[b]) {
                if (hasKey[k] == 0 && found[k] > 0) {
                    q.offer(k);
                }
                hasKey[k] = 1;
            }
        }
        return ans;
    }
}
