package zhaohang.S2020;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] nums = new int[N];
            int counter = 0;
            int c2 = 0;
            for (int i = 0; i < N; i++) {
                nums[i] = sc.nextInt();
                if (nums[i] >= 1) {
                    counter += 1;
                }
                if (nums[i] >= 2) {
                    c2 += 1;
                }
            }
            if (c2 > 0) {
                System.out.println(counter + 1);
            } else {
                System.out.println(-1);
            }
        }

    }
}
