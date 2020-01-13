package contest;

import Algorithm.UnionFind.QuickUnionFind;

import java.util.Arrays;
import java.util.HashSet;

public class Week171 {
    public static void main(String[] args) {
        Week171 week171 = new Week171();
//        System.out.println(week171.minFlips(2, 6, 5));
//        System.out.println(week171.makeConnected(100, new int[][]{{17, 51}, {33, 83}, {53, 62}, {25, 34}, {35, 90}, {29, 41}, {14, 53}, {40, 84}, {41, 64}, {13, 68}, {44, 85}, {57, 58}, {50, 74}, {20, 69}, {15, 62}, {25, 88}, {4, 56}, {37, 39}, {30, 62}, {69, 79}, {33, 85}, {24, 83}, {35, 77}, {2, 73}, {6, 28}, {46, 98}, {11, 82}, {29, 72}, {67, 71}, {12, 49}, {42, 56}, {56, 65}, {40, 70}, {24, 64}, {29, 51}, {20, 27}, {45, 88}, {58, 92}, {60, 99}, {33, 46}, {19, 69}, {33, 89}, {54, 82}, {16, 50}, {35, 73}, {19, 45}, {19, 72}, {1, 79}, {27, 80}, {22, 41}, {52, 61}, {50, 85}, {27, 45}, {4, 84}, {11, 96}, {0, 99}, {29, 94}, {9, 19}, {66, 99}, {20, 39}, {16, 85}, {12, 27}, {16, 67}, {61, 80}, {67, 83}, {16, 17}, {24, 27}, {16, 25}, {41, 79}, {51, 95}, {46, 47}, {27, 51}, {31, 44}, {0, 69}, {61, 63}, {33, 95}, {17, 88}, {70, 87}, {40, 42}, {21, 42}, {67, 77}, {33, 65}, {3, 25}, {39, 83}, {34, 40}, {15, 79}, {30, 90}, {58, 95}, {45, 56}, {37, 48}, {24, 91}, {31, 93}, {83, 90}, {17, 86}, {61, 65}, {15, 48}, {34, 56}, {12, 26}, {39, 98}, {1, 48}, {21, 76}, {72, 96}, {30, 69}, {46, 80}, {6, 29}, {29, 81}, {22, 77}, {85, 90}, {79, 83}, {6, 26}, {33, 57}, {3, 65}, {63, 84}, {77, 94}, {26, 90}, {64, 77}, {0, 3}, {27, 97}, {66, 89}, {18, 77}, {27, 43}}));
        System.out.println(week171.minimumDistance("CAKE"));
//        char[] chars = new char[2];
//        System.out.println(chars[0]);
    }


    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!String.valueOf(a).contains("0") && !String.valueOf(b).contains("0"))
                return new int[]{a, b};
        }
        return new int[]{};
    }

    public int minFlips(int a, int b, int c) {
        if ((a | b) == c) {
            return 0;
        }
        int counter = 0;
        while (c > 0 || a > 0 || b > 0) {
            int last = c & 1;
            c = c >> 1;
            int last_1 = a & 1;
            a = a >> 1;
            int last_2 = b & 1;
            b = b >> 1;
            if (last == 1) {
                if (last_1 == 0 && last_2 == 0) {
                    counter++;
                }
            } else {
                if (last_1 == 1) {
                    counter++;
                }
                if (last_2 == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }


    public int makeConnected(int n, int[][] connections) {
        QuickUnionFind quickUnionFind = new QuickUnionFind(n);
        int extraEdge = 0;
        for (int[] edge : connections) {
            int s = edge[0], e = edge[1];
            int r1 = quickUnionFind.findRoot(s);
            int r2 = quickUnionFind.findRoot(e);
            if (r1 == r2) {
                extraEdge++;
            } else {
                quickUnionFind.union(r1, r2);
            }
        }
        int isolation = 0;
        for (int t = 0; t < n; t++) {
            if (quickUnionFind.findRoot(t) == t) {
                isolation++;
            }
        }
        if (isolation - 1 <= extraEdge) {
            return isolation - 1;
        }
        return -1;
    }
    //    并查集问题，不使用路径压缩的方式：
    public int makeConnected2(int n, int[][] connections) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int m = connections.length;
        int components = 0;
        int extraEdge = 0;
        for (int i = 0; i < m; i++) {
            int p1 = findParent(parent, connections[i][0]);
            int p2 = findParent(parent, connections[i][1]);
            if (p1 == p2) {
                extraEdge++;
            } else {
                parent[p1] = p2;
            }
        }
        for (int i = 0; i < n; i++)
            if (parent[i] == i) {
//                独立的
                components++;
            }
        if (extraEdge >= components - 1) {
            return components - 1;
        }
        return -1;
    }

    public static int findParent(int[] par, int i) {
        while (i != par[i]) {
            i = par[i];
        }
        return i;
    }

    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 2) {
            return 0;
        }
        int[][][] dp = new int[n + 1][26][26];
        for (int i1 = 1; i1 <= n; i1++) {
            for (int i2 = 0; i2 < 26; i2++) {
                Arrays.fill(dp[i1][i2], Integer.MAX_VALUE);
            }
        }
        for (int i = 1; i <= n; i++) {
            char now = word.charAt(i - 1);
            int p = now - 'A';
            for (int l = 0; l < 26; l++) {
                for (int r = 0; r < 26; r++) {
                    if (dp[i - 1][l][r] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][p][r] = Math.min(dp[i][p][r], dp[i - 1][l][r] + keyDistance(l, p));
                    dp[i][l][p] = Math.min(dp[i][l][p], dp[i - 1][l][r] + keyDistance(r, p));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                ans = Math.min(ans, dp[n][i][j]);
            }
        }
        return ans;
    }

    private int keyDistance(int x, int y) {
        int xr = x / 6, xc = x % 6;
        int yr = y / 6, yc = y % 6;
        return Math.abs(xr - yr) + Math.abs(xc - yc);
    }
}
