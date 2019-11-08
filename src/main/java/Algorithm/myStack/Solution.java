package Algorithm.myStack;

import java.util.LinkedList;
import java.util.Stack;

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

    //给定压入栈顺序，判断是否产生某一输出栈
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || popA.length == 0 || pushA.length != popA.length)
            return false;
        Stack<Integer> st = new Stack<>();
        int i = 0;
        int j = 0;
        st.push(pushA[i++]);
        while (j < popA.length) {
            while (popA[j] != st.peek()) {
                if (i >= pushA.length) {
                    return false;
                }
                st.push(pushA[i++]);
            }
            j++;
            st.pop();
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.isValid("([{}])");
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
