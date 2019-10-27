package Algorithm.sword;

import java.util.Arrays;

public class BuyBook_1_4 {

    public static float minCost(Integer[] num) {
        float c5 = Float.MAX_VALUE, c4 = Float.MAX_VALUE, c3 = Float.MAX_VALUE, c2 = Float.MAX_VALUE, c1 = Float.MAX_VALUE;//剩余找最小花费。
        /**
         * 状态转移方程
         */
        if (num[0] == 0) return 0;
        if (num[4] >= 1) {
            Integer[] tem = newNum(num, 4);
            c5 = (float) (40 * 0.75 + minCost(tem));
        }
        if (num[3] >= 1) {
            Integer[] tem = newNum(num, 3);
            c4 = (float) (32 * 0.8 + minCost(tem));
        }
        if (num[2] >= 1) {
            Integer[] tem = newNum(num, 2);
            c3 = (float) (24 * 0.9 + minCost(tem));
        }
        if (num[1] >= 1) {
            Integer[] tem = newNum(num, 1);
            c2 = (float) (16 * 0.95 + minCost(tem));
        }
        Integer[] tem = newNum(num, 0);
        c1 = (float) (8 + minCost(tem));
        return findMin(c1, c2, c3, c4, c5);
    }

    public static Integer[] newNum(Integer[] num, int n) {
        Integer[] temp = new Integer[num.length];//深复制
        for (int i = 0; i < temp.length; i++) temp[i] = num[i];
        for (int i = 0; i <= n; i++) temp[i] = temp[i] - 1;
        //重写排序
        Arrays.sort(temp, (o1, o2) -> o2 - o1);
        return temp;
    }

    public static float findMin(float c1, float c2, float c3, float c4, float c5) {
        float min = c1;
        if (c2 < min) min = c2;
        if (c3 < min) min = c3;
        if (c4 < min) min = c4;
        if (c5 < min) min = c5;
        return min;
    }


//    private float buy(int[] n) {
////        double cost = 0;
////        int sum = 0;
////        for (int i = 0; i < n.length; i++) {
////            sum += n[i];
////        }
////        if (sum == 0) {
////            return 0;
////        }
////
////        if (n[4] >= 1) {
////            int[] t = new int[5];
////            for (int i = 0; i < n.length; i++) {
////                t[i] = n[i] - 1;
////            }
////            cost = ((5 * 8 * 0.75) + buy(t));
////        } else if (n[3] >= 1) {
////            int[] t = new int[5];
////            t[0] = n[0] - 1;
////            t[1] = n[1] - 1;
////            t[2] = n[2] - 1;
////            t[3] = n[3] - 1;
////            t[4] = n[4];
////            cost = ((4 * 8 * 0.8) + buy(t));
////        } else if (n[2] >= 1) {
////            int[] t = new int[5];
////            t[0] = n[0] - 1;
////            t[1] = n[1] - 1;
////            t[2] = n[2] - 1;
////            t[3] = n[3];
////            t[4] = n[4];
////            cost = ((3 * 8 * 0.9) + buy(t));
////        } else if (n[1] >= 1) {
////            int[] t = new int[5];
////            t[0] = n[0] - 1;
////            t[1] = n[1] - 1;
////            t[2] = n[2];
////            t[3] = n[3];
////            t[4] = n[4];
////            cost = ((2 * 8 * 0.95) + buy(t));
////        } else if (n[0] >= 1) {
////            int[] t = new int[5];
////            for (int i = 0; i < n.length; i++) {
////                t[i] = n[i];
////            }
////            t[0] = n[0] - 1;
////            cost = (8 + buy(t));
////        }
////        return cost;
////    }

    public static void main(String[] args) {
        Integer[] res = {9, 6, 5, 4, 3};
        System.out.println(minCost(res));
    }
}
