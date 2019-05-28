package Algorithm.myStack;

import java.util.LinkedList;

public class Solution {

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        char[] input = s.toCharArray();
        for (int i = 0; i != input.length; i++) {
            char now = input[i];
            if (stack.size() > 0) {
                char last = stack.peekLast();
                if (now - last == 1 || now - last == 2) {
                    stack.pollLast();
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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.JumpFloor(10));
    }

    public int JumpFloor(int target) {
        int[] memo = new int[]{1, 2};
        if (target < 3) {
            return target;
        }
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = memo[0] + memo[1];
            memo[0] = memo[1];
            memo[1] = result;
        }
        return result;
    }
}
