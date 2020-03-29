package contest;

import java.util.*;

public class Week181 {
    public static void main(String[] args) {
        Week181 week180 = new Week181();
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        ArrayList<Integer> res = new ArrayList<>(index.length);
        for (int i = 0; i < nums.length; i++) {
            res.add(index[i], nums[i]);
        }
        int[] f = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            f[i] = res.get(i);
        }
        return f;
    }

    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res += sumOfDivisors(n);
        }
        return res;
    }

    private int sumOfDivisors(int num) {
        int res = 0;
        int lastNum = 0;
        for (int i = 2; i * i <= num; i++) {
            int m = num % i;
            if (m == 0) {
                if (lastNum == 0) {
                    lastNum = i;
                } else {
                    lastNum = 0;
                    break;
                }
            }

        }
        if (lastNum > 0 && num / lastNum != lastNum) {
            res += 1 + num + lastNum + num / lastNum;
        }
        return res;
    }

    int m, n;
    int[][][] dir = new int[][][]{
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{1, 0}, {0, 1}},
            {{-1, 0}, {0, -1}},
            {{-1, 0}, {0, 1}},
    };

    public boolean hasValidPath(int[][] grid) {
        boolean res = false;
        int nowRow = 0, nowCol = 0;
        m = grid.length;
        n = grid[0].length;
        boolean[][] visit = new boolean[m][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visit[0][0] = true;
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1];
            int type = grid[row][col] - 1;
            for (int[] d : dir[type]) {
                int nRow = d[0] + row;
                int nCol = d[1] + col;
                if (nRow >= m || nRow < 0 || nCol >= n || nCol < 0 || visit[nRow][nCol]) {
                    continue;
                }
                for (int[] back : dir[grid[nRow][nCol] - 1]) {
                    if (nRow + back[0] == row && nCol + back[1] == col) {
                        visit[nRow][nCol] = true;
                        q.offer(new int[]{nRow, nCol});
                    }
                }
            }
        }
        return visit[m - 1][n - 1];
    }

    long mod = 1_000_000_007;

    public String longestPrefix(String s) {
        int n = s.length();
        int low = 0;
        int high = n - 1;

        long prefixHash = 0;
        long suffixHash = 0;
        //x is used to maintain powers of 26.
        long x = 1;

        int res_high = 0;
        while (low < n - 1 && high >= 0) {
            int c = s.charAt(low) - 'a';
            prefixHash = (prefixHash * 26 + c) % mod;

            c = s.charAt(high) - 'a';
            suffixHash = (suffixHash + x * c) % mod;
            x = (x * 26) % mod;

            if (prefixHash == suffixHash) {
                res_high = low + 1;
            }
            ++low;
            --high;
        }
        String res = s.substring(0, res_high);
        return res;
    }
}
