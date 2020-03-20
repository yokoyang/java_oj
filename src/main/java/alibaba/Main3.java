package alibaba;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main3 {
    static class Data implements Comparable<Data> {
        String str;
        Character start, end;
        int len = 0;

        Data(String input) {
            str = input;
            start = str.charAt(0);
            end = str.charAt(str.length() - 1);
            len = str.length();
        }

        @Override
        public int compareTo(Data data) {
            return Character.compare(end, data.start);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Data[] dataList = new Data[N];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            Data d = new Data(s);
            dataList[i] = d;
        }
        int maxLen = 0;
        int sum = 0;
        Arrays.sort(dataList);
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> (b.end - a.end));
        for (Data d : dataList) {
            if (pq.isEmpty()) {
                pq.offer(d);
                sum += d.len;
            } else {
                Data last = pq.peek();
                if (last.end <= d.start) {
                    pq.offer(d);
                    sum += d.len;
                }
                if (last.end > d.start) {
                    sum -= last.len;
                    pq.poll();
                    sum += d.len;
                }
            }
            maxLen = Math.max(maxLen, sum);
        }
        System.out.println(maxLen);
    }
}
