package contest;

import java.sql.Array;
import java.util.*;

public class Week175 {

    List<String> res;

    public List<String> letterCasePermutation(String S) {
        res = new ArrayList<>();
        dfs(new StringBuilder(), 0, S);
        return res;
    }

    void dfs(StringBuilder sb, int pos, String S) {
        if (sb.length() == S.length()) {
            res.add(sb.toString());
            return;
        }
        Character now = S.charAt(pos);
        String sub = now.toString();
        if (!Character.isDigit(now)) {
            sb.append(sub.toLowerCase());
            pos++;
            dfs(sb, pos, S);
            sb.deleteCharAt(sb.length() - 1);
            pos--;

            sb.append(sub.toUpperCase());
            pos++;
            dfs(sb, pos, S);
            sb.deleteCharAt(sb.length() - 1);
            pos--;
        } else {
            sb.append(sub);
            pos++;
            dfs(sb, pos, S);
            sb.deleteCharAt(sb.length() - 1);
            pos--;
        }

    }

    public static void main(String[] args) {
        char c = 'b';
        char B = (char) (c - 32);
        char b = (char) (B + 32);
        System.out.println(B);
        System.out.println(b);
//        Week175 week175 = new Week175();
//        System.out.println(week175.letterCasePermutation("a1b2"));
//        week175.maxStudents(new char[][]{{'#', '.', '#', '#', '.', '#'}, {'.', '#', '#', '#', '#', '.'}, {'#', '.', '#', '#', '.', '#'}});
        //        TweetCounts tweetCounts = new TweetCounts();
//        tweetCounts.recordTweet("tweet3", 0);
//        tweetCounts.recordTweet("tweet3", 60);
//        tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
//        tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
//        tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
//        tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
//        tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.
//        int t = week171.minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2);
    }

    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[i] * 2 == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int minSteps(String s, String t) {
        int[] r1 = new int[26];
        int[] r2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            r1[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            r2[t.charAt(i) - 'a']++;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res += Math.abs(r1[i] - r2[i]);
        }
        return res / 2;
    }

    String getKey(char[][] seats) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                sb.append(seats[i][j]);
            }
        }
        return sb.toString();
    }

    HashMap<String, Integer> seen;
    int m, n;
    Map<String, Integer> memo;

    public int maxStudents(char[][] seats) {
        m = seats.length;
        if (m == 0) return 0;
        n = seats[0].length;

        memo = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char[] row : seats) {
            sb.append(row);
        }
        return dfs(sb.toString());
    }

    public int dfs(String state) {
        if (memo.containsKey(state)) return memo.get(state);
        int max = 0;
        char[] C = state.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (C[i * n + j] == '.') {
                    //the case we choose not to place a student, but please don't branch here in the next state
                    C[i * n + j] = 'x';
                    max = Math.max(max, dfs(new String(C)));

                    if (j + 1 < n) {
                        if (i < m - 1 && C[(i + 1) * n + j + 1] == '.') {
                            C[(i + 1) * n + j + 1] = 'x';
                        }
                        if (C[i * n + j + 1] == '.') {
                            C[i * n + j + 1] = 'x';
                        }
                    }
                    if (j - 1 >= 0) {
                        if (i < m - 1 && C[(i + 1) * n + j - 1] == '.') {
                            C[(i + 1) * n + j - 1] = 'x';
                        }
                        if (C[i * n + j - 1] == '.') {
                            C[i * n + j - 1] = 'x';
                        }
                    }
                    max = Math.max(max, 1 + dfs(new String(C)));
                }
            }
        }
        memo.put(state, max);
        return max;
    }


//    private boolean check(char[][] seats, int i, int j, int m, int n) {
//        if (seats[i][j] != '.') {
//            return false;
//        }
//        // left
//        if (j > 0 && seats[i][j - 1] == 'X') {
//            return false;
//        }
//        //right
//        if (j + 1 < n && seats[i][j + 1] == 'X') {
//            return false;
//        }
//        //        upper left
//        if (i - 1 >= 0 && j > 0 && seats[i - 1][j - 1] == 'X') {
//            return false;
//        }
////        upper right
//        if (i - 1 >= 0 && j + 1 < n && seats[i - 1][j + 1] == 'X') {
//            return false;
//        }
//        return true;
//    }

    private void others(char[][] seats, int i, int j, int m, int n) {
        if (j - 1 >= 0 && seats[i][j - 1] == '.') {
            seats[i][j - 1] = 'X';
        }
        if (j + 1 < m && seats[i][j + 1] == '.') {
            seats[i][j + 1] = 'X';
        }
        if (i + 1 >= m) {
            return;
        }
        if (j - 1 >= 0 && seats[i + 1][j - 1] == '.') {
            seats[i + 1][j - 1] = 'X';
        }
        if (j + 1 < n && seats[i + 1][j + 1] == '.') {
            seats[i + 1][j + 1] = 'X';
        }
    }
}


class TweetCounts {
    HashMap<String, List<Integer>> records;

    public TweetCounts() {
        records = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        List<Integer> t = records.getOrDefault(tweetName, new ArrayList<>());
        t.add(time);
        Collections.sort(t);
        records.put(tweetName, t);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> t = records.getOrDefault(tweetName, new ArrayList<>());
        int gap = 0;
        if (freq.equals("hour")) {
            gap = 3600;
        } else if (freq.equals("minute")) {
            gap = 60;
        } else {
//            day
            gap = 3600 * 60;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; startTime + gap * i <= endTime; i++) {
            int c = 0;
            int s = startTime + gap * i;
            int e = Math.min(startTime + gap * (i + 1) - 1, endTime);

            for (int j = 0; j < t.size(); j++) {
                int v = t.get(j);
                if (s <= v && v <= e) {
                    c++;
                }
                if (v >= e) {
                    break;
                }
            }
            res.add(c);
        }
        return res;
    }

}