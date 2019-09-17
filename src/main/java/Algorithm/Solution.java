package Algorithm;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] t = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        s.minPathSum(t);
//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(15);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(20);
//        System.out.println(s.isValidBST(root));
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }

        }
        return maxans;
    }

    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public int evalRPN(String[] tokens) {
        HashSet<String> operations = new HashSet<>();
        operations.add("*");
        operations.add("/");
        operations.add("+");
        operations.add("-");
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String now = tokens[i];
            if (operations.contains(now)) {
                //operation
                Integer f2 = stack.pollLast();
                Integer f1 = stack.pollLast();
                Integer temp;
                if (now.equals("+")) {
                    temp = f1 + f2;
                } else if (now.equals("-")) {
                    temp = f1 - f2;
                } else if (now.equals("*")) {
                    temp = f1 * f2;
                } else {
                    temp = f1 / f2;
                }
                stack.offerLast(temp);
            } else {
                stack.offerLast(Integer.valueOf(now));
            }
        }
        int result = stack.poll();
//        System.out.println(result);
        return result;
    }

    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        String have = scanner.next();
        int sum = 0;
        List<HashMap<Character, Integer>> recordList = new ArrayList<>();
        char[] allCharList = have.toCharArray();
        for (int i = 0; i < allCharList.length; i++) {
            HashMap<Character, Integer> record = new HashMap<>();
            if (i != 0) {
                record.putAll(recordList.get(recordList.size() - 1));
            }
            int t = record.getOrDefault(allCharList[i], 0);
            record.put(allCharList[i], ++t);
            recordList.add(record);
        }
        for (int i = 1; i <= Q; i++) {
            int counter = 0;
            int Li = scanner.nextInt();
            int Ri = scanner.nextInt();
            Map<Character, Integer> record = recordList.get(Ri - 1);
            for (Map.Entry entry : record.entrySet()) {
                Character letter = (Character) entry.getKey();
                int frequence = (int) entry.getValue();
                int before = 0;
                if (Li > 1) {
                    before = recordList.get(Li - 2).getOrDefault(letter, 0);

                }
                if ((frequence - before) % 2 != 0) {
                    //odd
                    counter++;
                }
                if (counter > 1) {
                    break;
                }
            }
            if (counter <= 1) {
                sum++;
            }

        }
        return sum;
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        memo[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            memo[i][0] = memo[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            memo[0][j] = memo[0][j - 1] + grid[0][j];
        }
        if (m * n < 4) {
            return grid[0][0] * grid[m][n];
        }
        int min = Math.min(m, n);
        for (int k = 1; k < min; k++) {
            for (int col = k; col < n; col++) {
                memo[k][col] = Math.min(memo[k][col - 1], memo[k - 1][col]) + grid[k][col];
            }
            for (int row = k; row < m; row++) {
                memo[row][k] = Math.min(memo[row][k - 1], memo[row - 1][k]) + grid[row][k];
            }
        }
        return memo[m - 1][n - 1];
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
