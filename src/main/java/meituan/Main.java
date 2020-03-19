package meituan;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 0; i < N; i++) {
            pq.offer(sc.nextInt());
        }
        for (int i = 0; i < N; i++) {
            pq2.offer(sc.nextInt());
        }
        int m1 = 0, m2 = 0;
        int t = 0;
        while (!pq.isEmpty() && t < 3) {
            m1 += pq.poll();
            m2 += pq2.poll();
            t++;
        }
//        Arrays.sort(arr1);
//        Arrays.sort(arr2);
//        int counter = 0;
//        int res2 = 0;
//        for (int i = N - 1; i >= 0; i--) {
//            if (counter < 3) {
//                res += arr1[i];
//                res2 += arr2[i];
//            }
//            counter++;
//        }
//        System.out.println(res);
//        System.out.println(res2);
        System.out.println(Math.max(m1, m2));
    }
}
