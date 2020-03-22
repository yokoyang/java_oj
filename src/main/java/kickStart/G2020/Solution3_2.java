package kickStart.G2020;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution3_2 {
    static int N = 0;
    static int K = 0;
    static int maxGap = Integer.MIN_VALUE;

    public static boolean check(int maxdif, int[] gaps) {
        int cnt = 0;
        for (int gap : gaps) {
            if (gap > maxdif) {
                int cc = gap / maxdif;
                if (cc * maxdif >= gap)
                    cnt += cc - 1;
                else {
                    cnt += cc;
                }
            }
        }

        return cnt <= K;
    }

    private static int solve(Scanner scanner) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        int[] nums = new int[N];
        int[] gaps = new int[N - 1];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
            if (i > 0) {
                gaps[i - 1] = nums[i] - nums[i - 1];
                maxGap = Math.max(gaps[i - 1], maxGap);
            }
        }
        int l = 1, r = maxGap;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid, gaps)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return Math.max(1, l);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
