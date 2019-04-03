package wy2019;

import java.util.*;

public class WY_7 {

    public static void main(String[] arsg) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> H_list = new ArrayList<>();
        ArrayList<Integer> M_list = new ArrayList<>();
        ArrayList<Integer> timeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int h = sc.nextInt();
            H_list.add(h);
            int m = sc.nextInt();
            M_list.add(m);
            timeList.add(h * 60 + m);
        }
        Collections.sort(timeList);
        Collections.reverse(timeList);

        int timeNeed = sc.nextInt();
        int endTimeH = sc.nextInt();
        int endTimeM = sc.nextInt();
        int result = 0;
        int endTime = endTimeH * 60 + endTimeM;
        for (int i = 0; i != timeList.size(); i++) {
            if (timeList.get(i) + timeNeed <= endTime) {
                result = (timeList.get(i));
                break;
            }
        }

        int H = result / 60;
        int M = result % 60;
        System.out.printf("%d %d", H, M);

    }
}
