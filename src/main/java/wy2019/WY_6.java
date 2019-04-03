package wy2019;

import java.util.ArrayList;
import java.util.Scanner;

public class WY_6 {


    public static void main(String[] arsg) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> x_1 = new ArrayList<>();
        for (int i = 0; i != n; i++) {
            x_1.add(sc.nextInt());
        }

        ArrayList<Integer> y_1 = new ArrayList<>();
        for (int i = 0; i != n; i++) {
            y_1.add(sc.nextInt());
        }
        ArrayList<Integer> x_2 = new ArrayList<>();
        for (int i = 0; i != n; i++) {
            x_2.add(sc.nextInt());
        }
        ArrayList<Integer> y_2 = new ArrayList<>();
        for (int i = 0; i != n; i++) {
            y_2.add(sc.nextInt());
        }
        ArrayList<Integer> x = new ArrayList<>();
        x.addAll(x_1);
        x.addAll(x_2);
        ArrayList<Integer> y = new ArrayList<>();
        y.addAll(y_1);
        y.addAll(y_2);
        int res = 1;
        for (int i : x) {
            for (int j : y) {
                int counter = 0;
                for (int k = 0; k < n; k++) {
                    if (i > x_1.get(k) && j > y_1.get(k) && i >= x_2.get(k) && j >= y_2.get(k)) {
                        counter++;
                    }
                }
                res = Math.max(res, counter);
            }
        }
        System.out.println(res);

    }
}
