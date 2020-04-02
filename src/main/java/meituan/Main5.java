package meituan;

import java.util.*;

public class Main5 {
    static class Info {
        int val;
        int gap = 0;
    }

    //    2 1 3 2 5
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        Info[] nums = new Info[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new Info();
            nums[i].val = sc.nextInt();
        }
        //
        Arrays.sort(nums, (a, b) -> (a.val - b.val));
        for (int i = 1; i < N; i++) {
            nums[i].gap = nums[i].val - nums[i - 1].val;
        }

        int l = 0, r = N - 1;
        int times = 0;
        while (l < r) {
            int small = nums[l].val;
            int big = nums[r].val;
            if (big - small > X) {
                //超过了
                times++;
                if (nums[l + 1].gap < nums[r].gap) {
                    r--;
                } else {
                    l++;
                }
            } else {
                break;
            }
        }
        System.out.println(times);
    }
}
