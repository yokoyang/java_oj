package Algorithm.DP;

import java.util.Arrays;

/**
 * 使用动态规则来解决0-1背包求解最大价值的问题
 *
 * @version 0.0.1
 */
public class MyDynamicProgammingPackageValue {

    /**
     * 使用动态规则来求物品的最大价值
     *
     * @param weights 物品信息
     * @param values  最大价值
     * @param maxNum  最大数量
     * @param weight  最大重量
     * @return 求最大价值
     */
    public int dynamicProgammingPackageMaxValue(int[] weights, int[] values, int maxNum, int weight) {
        // 用于标识当前层的最大值问题
        int[][] valueStatus = new int[maxNum][weight + 1];

        // 初始化
        for (int i = 0; i < maxNum; i++) {
            for (int j = 0; j <= weight; j++) {
                valueStatus[i][j] = -1;
            }
        }

        // 初始化第一个物品
        valueStatus[0][0] = 0;
        valueStatus[0][weights[0]] = values[0];

        // 使用动态规划来进行求解
        for (int i = 1; i < maxNum; i++) {
            // 求解上一层的背包
            for (int j = 0; j <= weight; j++) {
                // 如果上一层设置了值，在当前物品肯定也会被放入
                if (valueStatus[i - 1][j] >= 0) {
                    valueStatus[i][j] = valueStatus[i - 1][j];
                }
            }
            // 求解当前层的问题,背包重量不能超过最大限制
            for (int j = 0; j <= weight - weights[i]; j++) {
                if (valueStatus[i - 1][j] >= 0) {
                    // 首先检查当前节点是否已经被设置过值，设置过，则需要比较大小
                    int newValue = valueStatus[i - 1][j] + values[i];
                    System.out.println("当前的value:" + newValue);
                    if (newValue > valueStatus[i][j + weights[i]]) {
                        valueStatus[i][j + weights[i]] = newValue;
                    }
                }
            }
        }

        int resultValue = -1;

        for (int i = 0; i <= weight; i++) {
            if (valueStatus[maxNum - 1][i] > resultValue) {
                resultValue = valueStatus[maxNum - 1][i];
            }
        }

        return resultValue;
    }

    /**
     * 使用动态规则来求物品的最大价值
     *
     * @param weights 物品信息
     * @param values  最大价值
     * @param maxNum  最大数量
     * @param weight  最大重量
     * @return 求最大价值
     */
    public int dynamicProgammingPackageMaxValueMem(
            int[] weights, int[] values, int maxNum, int weight) {
        // 用于标识当前层的最大值问题
        int[] valueStatus = new int[weight + 1];

        // 初始化
        for (int j = 0; j <= weight; j++) {
            valueStatus[j] = -1;
        }

        // 初始化第一个物品
        valueStatus[0] = 0;
        valueStatus[weights[0]] = values[0];

        // 使用动态规划来进行求解
        for (int i = 1; i < maxNum; i++) {
            // 求解当前层的问题,背包重量不能超过最大限制
            for (int j = weight - weights[i]; j >= 0; j--) {
                if (valueStatus[j] >= 0) {
                    // 首先检查当前节点是否已经被设置过值，设置过，则需要比较大小
                    int newValue = valueStatus[j] + values[i];
                    System.out.println("当前的value:" + newValue);
                    if (newValue > valueStatus[j + weights[i]]) {
                        valueStatus[j + weights[i]] = newValue;
                    }
                }
            }
        }

        int resultValue = -1;

        for (int i = 0; i <= weight; i++) {
            if (valueStatus[i] > resultValue) {
                resultValue = valueStatus[i];
            }
        }

        return resultValue;
    }

    // items 商品价格，n 商品个数, w 表示满减条件，比如 200
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3 * w + 1];// 超过 3 倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        states[0][items[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3 * w; ++j) {// 不购买第 i 个商品
                if (states[i - 1][j]) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= 3 * w - items[i]; ++j) {// 购买第 i 个商品
                if (states[i - 1][j]) states[i][j + items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j]) break; // 输出结果大于等于 w 的最小值
        }
        if (j == 3 * w + 1) return; // 没有可行解
        for (int i = n - 1; i >= 1; --i) { // i 表示二维数组中的行，j 表示列
            if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j 不变。
        }
        if (j != 0) System.out.print(items[0]);
    }

    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[4][4];

    public int minDist(int i, int j) { // 调用 minDist(n-1, n-1);
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    //    字符串来莱文斯坦距离
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第 0 行:a[0..0] 与 b[0..j] 的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第 0 列:a[0..i] 与 b[0..0] 的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                else minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
            }
        }
        return minDist[n - 1][m - 1];
    }


    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }


    public static void main(String[] args) {
        Solution myDynamicProgammingPackageValue = new Solution();
//        int[] coins = new int[]{186, 419, 83, 408};
        int[] coins = new int[]{1, 5, 5, 11};
//        System.out.println(myDynamicProgammingPackageValue.canPartition(coins));
//        int minDist = myDynamicProgammingPackageValue.coinChange(coins, 6249);
        String str1 = "asbsda";
        String str2 = "as923asasbsda";
        int minDist = myDynamicProgammingPackageValue.lcs(str1.toCharArray(), str1.toCharArray().length, str2.toCharArray(), str2.toCharArray().length);
        System.out.println(minDist);
    }

    static class Solution {
        public boolean canPartition2(int[] nums) {
            int total = 0;
            for (int i : nums) total += i; // compute the total sum of the input array
            if (total % 2 != 0)
                return false; // if the array sum is not even, we cannot partition it into 2 equal subsets
            int max = total / 2; // the maximum for a subset is total/2
            int[][] results = new int[nums.length][max]; // integer matrix to store the results, so we don't have to compute it more than one time
            return isPartitionable(max, 0, 0, nums, results);
        }

        public boolean isPartitionable(int max, int curr, int index, int[] nums, int[][] results) {
            if (curr > max || index > nums.length - 1) {
                return false; // if we passed the max, or we reached the end of the array, return false
            }
            if (curr == max) return true; // if we reached the goal (total/2) we found a possible partition
            if (results[index][curr] == 1) {
                return true; // if we already computed teh result for the index i with the sum current, we retrieve this result (1 for true)
            }
            if (results[index][curr] == 2) {
                return false; // if we already computed teh result for the index i with the sum current, we retrieve this result (2 for false)
            }
            boolean res = isPartitionable(max, curr + nums[index], index + 1, nums, results) || isPartitionable(max, curr, index + 1, nums, results); // else try to find the equal partiion, taking this element, or not taking it
            results[index][curr] = res ? 1 : 2; // store the result for this index and this current sum, to use it in dynamic programming
            return res;
        }

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            //奇数
            if (sum % 2 != 0) {
                return false;
            }
            int half = sum >> 1;
            boolean[][] dp = new boolean[nums.length + 1][half + 1];
            for (int i = 0; i <= nums.length; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i < nums.length + 1; i++) {
                for (int j = 0; j < half + 1; j++) {
                    if (dp[i - 1][j]) {
                        dp[i][j] = true;
                        continue;
                    }
                    if (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]) {
                        dp[i][j] = true;
                    }
                }
            }
            return dp[nums.length][half];
        }

        public int coinChange(int[] coins, int amount) {
            if (amount < 0) {
                return -1;
            }
            if (amount == 0) {
                return 0;
            }
            Arrays.sort(coins);
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (coin > i) {
                        break;
                    }
                    if (dp[i - coin] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                    }
                }
            }

            return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
        }

        public int lcs(char[] a, int n, char[] b, int m) {
            int[][] maxLcs = new int[n][m];
            for (int i = 0; i < n; i++) {
                if (a[i] == b[0]) {
                    maxLcs[i][0] = 1;
                } else if (i != 0) {
                    maxLcs[i][0] = maxLcs[i - 1][0];
                } else {
                    maxLcs[i][0] = 0;
                }
            }
            for (int i = 0; i < m; i++) {
                if (b[i] == a[0]) {
                    maxLcs[0][i] = 1;
                } else if (i != 0) {
                    maxLcs[0][i] = maxLcs[0][i - 1];
                } else {
                    maxLcs[0][i] = 0;
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (a[i] == b[j]) {
                        maxLcs[i][j] = multiMax(maxLcs[i - 1][j - 1] + 1, maxLcs[i - 1][j], maxLcs[i][j - 1]);
                    } else {
                        maxLcs[i][j] = multiMax(maxLcs[i - 1][j - 1], maxLcs[i - 1][j], maxLcs[i][j - 1]);
                    }
                }
            }
            return maxLcs[n - 1][m - 1];
        }

            private int multiMax(int a1, int a2, int a3) {
            return Math.max(Math.max(a1, a2), a3);
        }
    }


}
