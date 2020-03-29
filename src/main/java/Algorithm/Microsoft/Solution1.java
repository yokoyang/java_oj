package Algorithm.Microsoft;


import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        solution.f1(12, 4, new int[]{3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 10, 10});
    }

    int f1(int input1, int input2, int[] input3) {
        int K = input1 / input2;
        int c = 0;
        ArrayList<Integer>[] record = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            record[i] = new ArrayList();
        }
        int index = 0;
        while (c < K) {
            if (record[c].size() >= input2) {
                c++;
            } else {
                if (!record[c].contains(input3[index])) {
                    record[c].add(input3[index]);
                } else {
                    int next = c + 1;
                    while (next < K && record[next].contains(input3[index])) {
                        next++;
                    }
                    if (next >= K) {
                        int before = c - 1;
                        while (before >= 0 && record[before].contains(input3[index])) {
                            before--;
                        }
                        if (before < 0) {
                            return -1;
                        } else {
                            int eachSize = record[before].size();
                            for (int i = eachSize - 1; i >= 0; i--) {
                                int tmp = (int) record[before].get(i);
                                if (!record[c].contains(tmp)) {
                                    record[c].add(0, tmp);
                                    record[before].set(i, input3[index]);
                                    Collections.sort(record[c]);
                                    Collections.sort(record[before]);
                                    break;
                                }
                            }
                        }
                    } else {
                        record[next].add(input3[index]);
                    }
                }
                index++;
            }
        }
        int ans = 0;
        for (int i = 0; i < K; i++) {
            int m1 = (int) record[i].get(0);
            int m2 = (int) record[i].get(record[i].size() - 1);
            if (m1 == m2) {
                return -1;
            }
            ans += (m2 - m1);
        }

        return ans;
    }
}
