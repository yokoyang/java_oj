package Algorithm.graph;

import java.util.*;

public class Floyd {
    public int floyd(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            dp[from][to] = edge[2];
            dp[to][from] = edge[2];
        }
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    dp[u][v] = Math.min(dp[u][k] + dp[k][v], dp[u][v]);
                }
            }
        }
        return 0;
    }
}
