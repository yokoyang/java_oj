package Algorithm.treeMap;

import java.util.Map;
import java.util.TreeMap;

public class Solution {

    //    1296. Divide Array in Sets of K Consecutive Numbers
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int n : nums)
            m.put(n, 1 + m.getOrDefault(n, 0));
        while (!m.isEmpty()) {
            Map.Entry<Integer, Integer> e = m.pollFirstEntry();
            int key = e.getKey(), val = e.getValue();
            while (val-- > 0) {
                for (int key1 = key + 1; key1 < key + k; ++key1) {
                    int times = m.getOrDefault(key1, -1);
                    if (times <= 0) {
                        return false;
                    }
                    m.put(key1, times - 1);
                    m.remove(key, 0);
                }
            }
        }
        return true;
    }

    public int[] sumZero(int n) {
        int[] res = new int[n];
        if (n == 1) {
            res[0] = 0;
            return res;
        }
        //jishu
        int pos, v = 1;
        if ((n & 1) == 1) {
            res[0] = 0;
            n -= 1;
            pos = 1;

        } else {
            pos = 0;
        }
        while (n > 0) {
            res[pos] = v;
            res[pos + 1] = v * (-1);
            pos += 2;
            v++;
            n -= 2;
        }
        return res;
    }
}
