package contest;

import Algorithm.UnionFind.QuickUnionFind;

import java.util.*;

public class Week173 {
    class Rest {
        int id;
        int rating;
        int veganFriendly;
        int price;
        int distance;

        public Rest(int id, int rating, int veganFriendly, int price, int distance) {
            this.id = id;
            this.rating = rating;
            this.veganFriendly = veganFriendly;
            this.price = price;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Week173 week171 = new Week173();
//        int t = week171.minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2);
        int t = week171.minDifficulty(new int[]{3}, 1);
        System.out.println(t);
    }

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        ArrayList<Rest> restList = new ArrayList<>();
        for (int[] each : restaurants) {
            if (each[3] > maxPrice) {
                continue;
            }
            if (each[4] > maxDistance) {
                continue;
            }
            if (veganFriendly == 1 && each[2] == 0) {
                continue;
            }
            restList.add(new Rest(each[0], each[1], each[2], each[3], each[4]));
        }
        Comparator c = (o1, o2) -> {
            int r = ((Rest) o2).rating - ((Rest) o1).rating;
            if (r != 0) {
                return r;
            }
            return ((Rest) o2).id - ((Rest) o1).id;
        };
        restList.sort(c);
        List<Integer> res = new ArrayList<>();
        for (Rest r : restList) {
            res.add(r.id);
        }
        return res;
    }

    public int removePalindromeSub(String s) {
        if (s.isEmpty()) {
            return 0;
        } else if (s.equals(new StringBuilder(s).reverse().toString())) {
            return 1;
        }
        return 2;
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], 100000000);
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            dis[x][y] = w;
            dis[y][x] = w;
        }
        for (int i = 0; i < n; i++) {
            dis[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][k] + dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                    }
                }
            }
        }
        int max = Integer.MAX_VALUE, ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= max) {
                max = count;
                ans = i;
            }
        }
        return ans;
    }

    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[n + 1][d];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = getMax(jobDifficulty, 0, i - 1);
        }
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k < d; k++) {
                for (int j = 1; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + getMax(jobDifficulty, j, i - 1));
                }
            }
        }
        return dp[n][d - 1];
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= d; k++) {
                int md = 0;
                for (int j = i - 1; j >= k - 1; j--) {
                    md = Math.max(md, jobDifficulty[j]);
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + md);
                }
            }
        }
        return dp[n][d];
    }

    private int getMax(int[] jobDifficulty, int s, int e) {
        int max = -1;
        for (int i = s; i <= e; i++) {
            max = Math.max(jobDifficulty[i], max);
        }
        return max;
    }
}
