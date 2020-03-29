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

    int f1() {
        int a = 1;
        return ++a;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        Solution solution = new Solution();
        System.out.println(solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"},
                "0202"));
//        solution.maxJumps(nums, 2);
    }

    int n;
    HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(canReach(arr, i, d, new HashSet<>()).size(), res);
        }
        return res;
    }

    HashSet<Integer> canReach(int[] arr, int p, int d, HashSet<Integer> visited) {
//        if (dp.get(p) != null) {
//            return dp.get(p);
//        }
        int h = arr[p];
        visited.add(p);
        int l = Math.max(0, p - d);
        int r = Math.min(n - 1, p + d);
        for (int j = p - 1; j >= l; j--) {
            if (arr[j] < h) {
                visited.addAll(canReach(arr, j, d, visited));
            } else {
                break;
            }
        }
        for (int j = p + 1; j <= r; j++) {
            if (arr[j] < h) {
                visited.addAll(canReach(arr, j, d, visited));
            } else {
                break;
            }
        }
        dp.put(p, visited);
        return visited;
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        double[] p = new double[n + 1];
        p[1] = 1.0;
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        boolean[] seen = new boolean[n + 1];
        seen[1] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (t-- > 0) {
            while (!q.isEmpty()) {
                int now = q.poll();
            }
        }
        return 1.0;
    }

    //     542 01 Matrix
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[m][n];
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    seen[i][j] = true;
                    q.offer(new int[]{i, j});
                }
            }
        }
        int step = 0;
        int[] dirX = new int[]{1, -1, 0, 0};
        int[] dirY = new int[]{0, 0, 1, -1};

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.poll();
                int posY = pos[0], posX = pos[1];
                ans[posY][posX] = step;
                for (int i = 0; i < 4; i++) {
                    int nx = posX + dirX[i];
                    int ny = posY + dirY[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || seen[ny][nx]) {
                        continue;
                    }
                    seen[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
            step++;
        }
        return ans;
    }

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet<>();
        String start = "0000";
        if (deadSet.contains(start)) {
            return -1;
        }
        ArrayDeque<String> q = new ArrayDeque<>();
        q.offer(start);
        visited.add(start);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String now = q.poll();
                if (now.equals(target)) {
                    return step;
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j <= 1; j += 2) {
                        char[] nowChars = now.toCharArray();
                        nowChars[i] = (char) ((nowChars[i] - '0' + j + 10) % 10 + '0');
                        String next = new String(nowChars);
                        if (deadSet.contains(next) || visited.contains(next)) {
                            continue;
                        }
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
