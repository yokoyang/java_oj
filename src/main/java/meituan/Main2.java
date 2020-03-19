package meituan;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {
    static int remove(int[] nums) {
        List<Integer> list = new LinkedList<>();
        for (int i : nums) {
            list.add(i);
        }
        int maxLen = 1;
        for (int i = 0; i < list.size(); i++) {
            if (i + 1 < list.size() && list.get(i + 1) < list.get(i)) {
                int todel = list.get(i);
                list.remove(i);
                maxLen = Math.max(maxLen, getMaxLen(list));
                list.add(i, todel);
            }
        }
        return maxLen;

    }

    static int getMaxLen(List<Integer> list) {
        int maxLen = 1;
        for (int i = 0; i < list.size(); i++) {
            int pre = i;
            while (i + 1 < list.size() && list.get(i) <= list.get(i + 1)) {
                i++;
            }
            maxLen = Math.max(maxLen, i - pre + 1);
            i++;
        }
        return maxLen;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        int res = remove(nums);
        System.out.println(res);
    }
}
