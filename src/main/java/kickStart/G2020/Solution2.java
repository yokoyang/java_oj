package kickStart.G2020;

import java.util.HashMap;
import java.util.PriorityQueue;
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
public class Solution2 {
    static int P = 0;
    static int maxValue = 0;
    static int N = 0;
    static int K = 0;
    static HashMap<String, Integer> record;

    private static String getKey(int i, int d, int c) {
        return i + "," + d + "," + c;
    }

    private static int solve(Scanner scanner) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        P = scanner.nextInt();
        maxValue = 0;
        record = new HashMap<>();
        int[][] nums = new int[N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        int[] eachDep;
        for (int i = 0; i < N; i++) {
            eachDep = new int[N];
            trace(nums, 0, 0, i, eachDep);
        }
        return maxValue;
    }

    static void trace(int[][] nums, int nowSum, int nowCounter, int nowIndex, int[] eachDep) {
        if (nowCounter >= P) {
            maxValue = Math.max(maxValue, nowSum);
            return;
        }

        if (eachDep[nowIndex] >= K) {
            return;
        }
        int val = nums[nowIndex][eachDep[nowIndex]];
        eachDep[nowIndex]++;
        nowSum += val;
        String key = getKey(nowIndex, eachDep[nowIndex], nowCounter);
        Integer pre = record.get(key);
        if(pre==null){
            record.put(key,nowSum);
        }
        else{
            return;
        }
        for (int i = 0; i < N; i++) {
            trace(nums, nowSum, nowCounter + 1, i, eachDep);
        }
        eachDep[nowIndex]--;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
