package contest;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Week182 {
    public static void main(String[] args) {
        Week182 week180 = new Week182();
//        System.out.println(week180.findLucky(new int[]{2, 2, 2, 3, 3}));
        Pair<Integer, String> pair = new Pair<>(1, "One");
        Integer key = pair.getKey();
        String value = pair.getValue();
        System.out.println(key);
        System.out.println(value);
//        UndergroundSystem undergroundSystem = new UndergroundSystem();
//        undergroundSystem.checkIn(45, "Leyton", 3);
//        undergroundSystem.checkIn(32, "Paradise", 8);
//        undergroundSystem.checkIn(27, "Leyton", 10);
//        undergroundSystem.checkOut(45, "Waterloo", 15);
//        undergroundSystem.checkOut(27, "Waterloo", 20);
//        undergroundSystem.checkOut(32, "Cambridge", 22);
//        undergroundSystem.getAverageTime("Paradise", "Cambridge");       // return 14.0. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
//        undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.0. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.0
//        undergroundSystem.checkIn(10, "Leyton", 24);
//        undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.0
//        undergroundSystem.checkOut(10, "Waterloo", 38);
//        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));        // return 12.0
    }

    public int findLucky(int[] arr) {
        int res = -1;
        int[] times = new int[501];
        for (int i = 0; i < arr.length; i++) {
            times[arr[i]]++;
        }
        for (int i = 1; i < times.length; i++) {
            if (i == times[i]) {
                res = i;
            }
        }
        return res;
    }

    public int numTeams2(int[] rating) {
        int res = 0;
        for (int i = 1; i < rating.length - 1; ++i) {
            int[] less = new int[2];
            int[] greater = new int[2];
            for (int j = 0; j < rating.length; ++j) {
                if (rating[i] < rating[j])
                    ++less[j > i ? 1 : 0];
                if (rating[i] > rating[j])
                    ++greater[j > i ? 1 : 0];
            }
            res += less[0] * greater[1] + greater[0] * less[1];
        }
        return res;
    }

    public int numTeams(int[] rating) {
        int n = rating.length;
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int now = rating[i];
            int c_l = 0;
            for (int l = i - 1; l >= 0; l--) {
                if (rating[l] < now) {
                    c_l++;
                }
            }
            int c_r = 0;
            for (int r = i + 1; r < n; r++) {
                if (rating[r] > now) {
                    c_r++;
                }
            }
            ans += (c_l * c_r);
            c_l = 0;
            for (int l = i - 1; l >= 0; l--) {
                if (rating[l] > now) {
                    c_l++;
                }
            }
            c_r = 0;
            for (int r = i + 1; r < n; r++) {
                if (rating[r] < now) {
                    c_r++;
                }
            }
        }
        return ans;
    }

    static class UndergroundSystem {
        class Info {
            String s_name = "";
            int time = 0;

            Info(String s, int t) {
                s_name = s;
                time = t;
            }
        }

        class TimeInfo {
            int total_time = 0;
            int times = 0;
        }

        HashMap<String, TimeInfo> record;
        HashMap<Integer, Info> userInfo;

        public UndergroundSystem() {
            record = new HashMap<>();
            userInfo = new HashMap<>();
        }

        private String mergeKey(String s, String end) {
            return s + "!" + end;
        }

        public void checkIn(int id, String stationName, int t) {
            userInfo.put(id, new Info(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Info info = userInfo.get(id);
            String key = mergeKey(info.s_name, stationName);
            TimeInfo t_before = record.getOrDefault(key, new TimeInfo());
            t_before.times += 1;
            t_before.total_time += (t - info.time);
            record.put(key, t_before);
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = mergeKey(startStation, endStation);
            TimeInfo t_before = record.get(key);
            int t = t_before.times;
            double cost = (double) t_before.total_time;
            return cost / t;
        }
    }

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        if (s1.startsWith(evil) && s2.startsWith(evil)) {
            return 0;
        }

        return 0;
    }
}
