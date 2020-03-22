package kickStart.G2020;

import java.util.HashMap;
import java.util.Scanner;

/*
2
3 4 5
10 10 100 30
80 50 10 50
1 2 3 1000
3 2 3
80 80
15 50
20 10
 */
public class Solution2_2 {
    static int P = 0;
    static int maxValue = 0;
    static int N = 0;
    static int K = 0;
    static int[][] presums;


    public static void fillData(int[][] nums) {
        presums = new int[N][K + 1];
        for (int i = 0; i < N; i++)
            for (int j = 1; j <= K; j++) {
                presums[i][j] = presums[i][j - 1] + nums[i][j - 1];
            }
    }

    private static int solve(Scanner scanner) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        P = scanner.nextInt();
        maxValue = 0;
        int[][] nums = new int[N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        fillData(nums);
        int[][][] dp = new int[N + 1][K + 1][P + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++)
            for (int j = 0; j <= K; j++) {
                for (int k = j; k <= P; k++) {
                    for (int t = 0; t <= k - j && t <= K; t++) {
                        dp[i][j][k] = presums[i - 1][j] + Math.max(dp[i][j][k], dp[i - 1][t][k - j]);
                    }
                }
                if (i == N) {
                    max = Math.max(dp[N][j][P], max);
                }
            }
        return max;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
