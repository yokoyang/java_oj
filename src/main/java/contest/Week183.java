package contest;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.*;

public class Week183 {
    public static void main(String[] args) {
        Week183 week180 = new Week183();
        System.out.println(week180.numSteps("1"));
    }

    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int now = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            now += nums[i];
            res.add(nums[i]);
            if (now * 2 > sum) {
                break;
            }
        }
        return res;
    }

    public int numSteps(String s) {
        int n = s.length();
        char[] chars = new char[n + 1];
        chars[0] = '0';
        for (int i = 1; i <= n; i++) {
            chars[i] = s.charAt(i - 1);
        }

        int step = 0;
        while (chars.length != 1) {
            if (chars[chars.length - 1] == '1') {
                if (chars.length <= 2 && chars[0] == '0') {
                    return step;
                }
                odd(chars);
            } else {
                chars = even(chars);
            }
            step++;

        }
        return step;
    }

    public int numSteps2(String s) {
        BigInteger b = new BigInteger(s, 2);
        int steps = 0;
        while (!b.equals(BigInteger.ONE)) {
            if (b.testBit(0)) {
                b = b.add(BigInteger.ONE);
            } else {
                b = b.shiftRight(1);
            }
            steps++;
        }
        return steps;
    }

    int odd(char[] chars) {
        chars[chars.length - 1] = '0';
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] == '0') {
                chars[i] = '1';
                return i;
            } else {
                chars[i] = '0';
            }
        }
        return 0;
    }

    char[] even(char[] chars) {
        return Arrays.copyOf(chars, chars.length - 1);
    }

    class Info implements Comparable<Info> {
        int time = 0;
        int continuous = 0;
        char c;

        Info(int t, char cc) {
            time = t;
            c = cc;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(o.time, time);
        }
    }

    StringBuilder result = new StringBuilder();
    Map<Character, int[]> map = new HashMap<>();

    public String longestDiverseString(int a, int b, int c) {
        map.put('a', new int[]{a, 0});
        map.put('b', new int[]{b, 0});
        map.put('c', new int[]{c, 0});
        helper(map);
        return result.toString();
    }

    public void helper(Map<Character, int[]> map) {
        PriorityQueue<Map.Entry<Character, int[]>> q = new PriorityQueue<>((a, b) -> (b.getValue()[0] - a.getValue()[0]));
        for (Map.Entry<Character, int[]> e : map.entrySet()) {
            q.add(e);
        }
        boolean f = false;
        while (!q.isEmpty()) {
            Map.Entry<Character, int[]> current = q.poll();
            if (current.getValue()[0] > 0 && current.getValue()[1] < 2 && !f) {
                result.append(current.getKey());
                map.put(current.getKey(), new int[]{current.getValue()[0] - 1, current.getValue()[1] + 1});
                f = true;
            } else {
                map.put(current.getKey(), new int[]{current.getValue()[0], 0});
            }
        }
        if (f) {
            helper(map);
        }
    }

    public String longestDiverseString2(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        Info[] infos = new Info[3];
        infos[0] = new Info(a, 'a');
        infos[1] = new Info(b, 'b');
        infos[2] = new Info(c, 'c');

        longestFill(infos);
        return result.toString();
    }

    void longestFill(Info[] infos) {
        Arrays.sort(infos);

        boolean find = false;
        for (int i = 0; i < infos.length; i++) {
            if (!find && infos[i].time > 0 && infos[i].continuous < 2) {
                result.append(infos[i].c);
                infos[i].continuous += 1;
                infos[i].time -= 1;
                find = true;
            } else {
                infos[i].continuous = 0;
            }
        }
        if (find) {
            longestFill(infos);
        }
    }
}
