package zhaohang;

import java.util.*;

public class MatrixMax {
    public int maxCal(int[][] data, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                MatrixDataInfo mInfo = new MatrixDataInfo(data[i][j], i, j);
                if (matrixDataInfoPriorityQueue.size() < k) {
                    matrixDataInfoPriorityQueue.offer(mInfo);
                } else {
                    MatrixDataInfo minOne = matrixDataInfoPriorityQueue.peek();
                    if (minOne.v < mInfo.v) {
                        matrixDataInfoPriorityQueue.poll();
                        matrixDataInfoPriorityQueue.offer(mInfo);
                    }
                }
            }
        }
        List<MatrixDataInfo> matrixDataInfoList = new ArrayList<>(matrixDataInfoPriorityQueue);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrixDataInfoList.size(); i++) {
            for (int j = i + 1; j < matrixDataInfoList.size(); j++) {
                MatrixDataInfo m1 = matrixDataInfoList.get(i);
                MatrixDataInfo m2 = matrixDataInfoList.get(j);
                if (detect(m1, m2)) {
                    continue;
                }
                max = Math.max(m1.v * m2.v, max);
            }
        }
        return max;
    }

    private boolean detect(MatrixDataInfo m1, MatrixDataInfo m2) {
        if (m1.col == m2.col || m1.row == m2.row) {
            return true;
        }
        return false;
    }

    class MatrixDataInfo implements Comparable<MatrixDataInfo> {
        int v;
        int row;
        int col;

        public MatrixDataInfo(int v, int row, int col) {
            this.v = v;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(MatrixDataInfo o) {
            if (this.v - o.v < 0) {
                return -1;
            } else if (this.v - o.v > 0) {
                return 1;
            }
            return 0;
        }
    }

    PriorityQueue<MatrixDataInfo> matrixDataInfoPriorityQueue = new PriorityQueue<>();
    int k = 4;

    public static void main(String[] args) {
        int[] zeroArray = new int[0];
        int[] zeroArray2 = new int[]{};

        MatrixMax m = new MatrixMax();
        m.isValid("aab");
        int[][] data = {{1, 2, 3,}, {4, 5, 6}};
        int result = m.maxCal(data, 2, 3);
        System.out.println(result);
    }

    private float maxTolast(int[] nums) {
        Arrays.sort(nums);
        return 0.0f;
    }

    public boolean isValid(String s) {
        Map<Character, Character> operations = new HashMap<>();
        operations.put(')', '(');
        operations.put(']', '[');
        Character t = operations.get('a');
        System.out.println(t);
        LinkedList<Character> stack = new LinkedList<>();

        char[] input = s.toCharArray();
        for (int i = 0; i != input.length; i++) {
            char now = input[i];

            if (stack.size() > 0) {
                char last = stack.peekLast();
                if (now - last == 1 || now - last == 2) {
                    stack.removeLast();
                    continue;
                }
            }
            stack.addLast(now);
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;

    }

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i + 1);
        }
        return result;
    }
}
