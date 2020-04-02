package meituan;

import java.util.Scanner;

public class Main6 {

    //4 21
//    2 1 4 3

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        int res = 0;
        int[] nums = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int t = sc.nextInt();
            nums[i] = t;
            sum += t;
        }
        int loop = X / sum;
        res += loop * N;
        int left = X % sum;
        int pos = 1;
        while (left > 0) {
            if (nums[pos - 1] <= left) {
                left -= nums[pos - 1];
                res++;
            }
            pos = pos % N + 1;
        }

        System.out.println(res);
    }
}
