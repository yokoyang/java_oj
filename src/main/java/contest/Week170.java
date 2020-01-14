package contest;

import java.util.HashMap;
import java.util.*;

public class Week170 {
    //    public String freqAlphabets(String s) {
//
//    }
    public static void main(String[] args) {
        Week170 week = new Week170();
    }

    class VideoRecord implements Comparable<VideoRecord> {
        String name;
        int times;

        public VideoRecord(String name, int times) {
            this.name = name;
            this.times = times;
        }

        @Override
        public int compareTo(VideoRecord o) {
            if (this.times - o.times < 0) {
                return -1;
            }
            if (this.times - o.times > 0) {
                return 1;
            }
            return this.name.compareTo(o.name);
        }
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        List<String> res = new ArrayList<>();
        HashMap<String, Integer> record = new HashMap<>();
        q.offer(id);
        HashSet<Integer> seen = new HashSet<>();
        seen.add(id);
        int nowLevel = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int now = q.poll();
                int[] fds = friends[now];
                for (int f : fds) {
                    if (seen.contains(f)) {
                        continue;
                    }
                    seen.add(f);
                    q.offer(f);
                }
            }
            nowLevel++;
            if (nowLevel == level) {
                break;
            }
        }
        while (!q.isEmpty()) {
            int f = q.poll();
            List<String> stringList = watchedVideos.get(f);
            for (String movie : stringList) {
                record.put(movie, record.getOrDefault(movie, 0) + 1);
            }
        }
        List<VideoRecord> videoRecords = new ArrayList<>();
        for (Map.Entry entry : record.entrySet()) {
            String name = (String) entry.getKey();
            int v = (int) entry.getValue();
            videoRecords.add(new VideoRecord(name, v));
        }
        Collections.sort(videoRecords);
        for (VideoRecord v : videoRecords) {
            res.add(v.name);
        }
        return res;
    }

    public int[] xorQueries2(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = query[0];
            int y = query[1];
            int s = Math.min(x, y);
            int e = Math.max(x, y);
            for (int j = s; j <= e; j++) {
                res[i] ^= arr[j];
            }
        }
        return res;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int size = arr.length;
        int[] dp = new int[size + 1];
        for (int i = 0; i < size; i++) {
            dp[i + 1] = dp[i] ^ arr[i];
        }
        int[] ans = new int[queries.length];
        int c = 0;
        for (int[] each : queries) {
            int l = each[0];
            int r = each[1];
            ans[c++] = (dp[r + 1] ^ dp[l]);
        }
        return ans;
    }

    public int minInsertions(String s) {
        return 0;
    }

}
