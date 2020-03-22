package kickStart.G2020;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution3 {
    static int N = 0;
    static int K = 0;

    private static int solve(Scanner scanner) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        int[] nums = new int[N];
        PriorityQueue<Integer> gapsPq = new PriorityQueue<>((a, b) -> (b - a));
        int gap = 1;
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
            if (i > 0) {
                gap = nums[i] - nums[i - 1];
                gapsPq.offer(gap);
            }
        }
        while (K-- > 0) {
            int nowMax = gapsPq.poll();
            if (nowMax == 1) {
                return 1;
            }
            int t = nowMax / 2;
            if (nowMax % 2 == 0) {
                //偶数
                gapsPq.offer(t);
                gapsPq.offer(t);
            } else {
                //奇数
                int t2 = nowMax - t;
                gapsPq.offer(t);
                gapsPq.offer(t2);
            }
        }
        if (gapsPq.isEmpty()) {
            return 1;
        }
        return Math.max(1, gapsPq.peek());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
