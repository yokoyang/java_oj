package wy2019;

import java.util.ArrayList;
import java.util.Scanner;

public class WY_8 {
    private static ArrayList<Integer> vList = new ArrayList<>();

    private static long DP(int i, long now_w) {
        if (now_w <= 0) {
            return 0;
        }
        long result;
        if (i == -1) {
            return 1;
        }
        if (i == 0) {
            if (now_w > vList.get(i)) {
                return 2;
            } else {
                return 1;
            }
        }
        long sum = 0;
        for (int j = 0; j <= i; j++) {
            sum += vList.get(j);
        }
        if (now_w >= sum) {
            return (long) Math.pow(2, i+1);
        }
        result = DP(i - 1, now_w) + DP(i - 1, now_w - vList.get(i));
        return result;
    }

    public static void main(String[] arsg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long w = scanner.nextLong();
        long total = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int v_i = scanner.nextInt();
            vList.add(v_i);
            total += v_i;
        }
        if (total <= w) {
            res = (int) Math.pow(2, n);
        } else {
            res = DP(vList.size() - 1, w);
        }
        System.out.println(res);

    }
}
