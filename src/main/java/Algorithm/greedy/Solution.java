package Algorithm.greedy;

import java.util.*;

public class Solution {
    //55. Jump Game
    //Input: [2,3,1,1,4]
    //Output: true
    //Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
    //贪心算法，记录能够到达的最大值，如果小于i说明中断了，false，如果能到达size-1 ok

    public boolean canJump(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            return true;
        }
        int maxReach = 0;
        for (int i = 0; i < size; i++) {
            if (maxReach < i) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= size - 1) {
                return true;
            }
        }
        return false;
    }

    //1253.
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        Integer[][] res = new Integer[2][colsum.length]; // 构建一个2维数组
        for (int sum : colsum) { // 循环colsum
            if (sum == 2) { // 列和为2时，upper和lower分别减一
                // 如果upper或lower小于0，返回空list
                if (--upper < 0 || --lower < 0) {
                    return new ArrayList<>();
                }
            }
        }
        for (int i = 0; i < colsum.length; i++) { // 循环colsum
            int sum = colsum[i]; // 当前列和
            if (sum == 0) { // 列和为0
                res[0][i] = 0; // 当前列为上0下0
                res[1][i] = 0;
            } else if (sum == 2) { // 列和为2
                res[0][i] = 1; // 当前列为上1下1
                res[1][i] = 1;
            } else { // 列和为1
                if (--upper >= 0) { // 如果还有upper
                    res[0][i] = 1; // 将1加入上行
                    res[1][i] = 0;
                } else if (--lower >= 0) { // 如果还有lower
                    res[0][i] = 0; // 将1加入下行
                    res[1][i] = 1;
                } else { // upper和lower都没了，返回空list
                    return new ArrayList<>();
                }
            }
        }
        // 如果upper或lower还有剩余，返回空list
        if (upper > 0 || lower > 0) {
            return new ArrayList<>();
        }
        // 构造返回结果
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(res[0]));
        list.add(Arrays.asList(res[1]));
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.carPooling(new int[][]{{3, 2, 8}, {4, 4, 6}, {10, 8, 9}}, 11);
        System.out.println(res);
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (t1, t2) -> (t1[1] - t2[1]));
        int left_cap = capacity;
        int now_pos = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> (t1[1] - t2[1]));
        for (int i = 0; i < trips.length; i++) {

            now_pos = trips[i][1];
            while (pq.size() > 0) {
                int[] near = pq.peek();
                if (near[1] <= now_pos) {
                    left_cap += near[0];
                    pq.poll();
                } else {
                    break;
                }
            }

            left_cap -= trips[i][0];
            if (left_cap < 0) {
                return false;
            }
            pq.offer(new int[]{trips[i][0], trips[i][2]});
        }
        return true;
    }

    //    45. Jump Game II
    //Given an array of non-negative integers, you are initially positioned at the first index of the array.
    //
    //Each element in the array represents your maximum jump length at that position.
    //
    //Your goal is to reach the last index in the minimum number of jumps.
    //    Note:
    //You can assume that you can always reach the last index.
    public int jump2(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int nowPos = 0;
        int maxReach = 0;
        int nowCanReach = 0;
        int maxIndex = 0;
        int step = 0;
        while (nowPos <= nums.length - 1) {
            nowCanReach = nowPos + nums[nowPos];
            if (nowCanReach >= nums.length - 1) {
                step++;
                break;
            }
            maxReach = 0;
            for (int j = nowPos + 1; j <= nowCanReach; j++) {
                int nextMaxReach = j + nums[j];
                if (nextMaxReach > maxReach) {
                    maxIndex = j;
                    maxReach = nextMaxReach;
                }
            }
            step++;
            nowPos = maxIndex;
        }
        return step;
    }

    public int jump(int[] A) {
        int steps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                steps++;
                curEnd = curFarthest;
            }
        }
        return steps;
    }

}
