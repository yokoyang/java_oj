package ebay;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[][] qa = new int[m][2];
        for (int i = 0; i < m; i++) {
            qa[i][0] = sc.nextInt();
            qa[i][1] = sc.nextInt();
        }
        int s, e;
        for (int i = 0; i < qa.length; i++) {
            s = qa[i][0];
            e = qa[i][1];
            Integer count = 0;
            HashMap<Integer, Integer> record = new HashMap<>();
            for (int j = s - 1; j <= e - 1; j++) {
                int times = record.getOrDefault(arr[j], 0);
                times += 1;
                record.put(arr[j], times);
            }
            for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
                if (entry.getValue() == 1) {
                    count += 1;
                }
            }
            System.out.println(count);
        }
    }
}
