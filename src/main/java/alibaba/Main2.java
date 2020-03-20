package alibaba;

import java.util.*;

public class Main2 {
    static class Data implements Comparable<Data> {
        Character start, end;
        int len = 0;

        Data(String input) {
            start = input.charAt(0);
            end = input.charAt(input.length() - 1);
            len = input.length();
        }

        @Override
        public int compareTo(Data data) {
            if (end == data.end) {
                return Character.compare(start, data.start);
            } else {
                return end - data.end;
            }
        }
    }
    /**
4
aaa
bcd
bcdef
zzz
     **/

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
        Arrays.sort(dataList);
        int[] dp = new int[26];
        for (int i = 0; i < N; i++) {
            int l = dataList[i].start - 'a';
            int r = dataList[i].end - 'a';
            int size = dataList[i].len;
            int maxBefore = 0;
            for (int j = 0; j <= l; j++) {
                maxBefore = Math.max(dp[j], maxBefore);
            }
            maxBefore += size;
            dp[r] = Math.max(dp[r], maxBefore);
        }
        for (int v : dp) {
            maxLen = Math.max(maxLen, v);
        }
        System.out.println(maxLen);
    }
}
