package Algorithm.myStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    //1249. Minimum Remove to Make Valid Parentheses
    //    Given a string s of '(' , ')' and lowercase English characters.
//
//Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
//
//Formally, a parentheses string is valid if and only if:
//
//It is the empty string, contains only lowercase characters, or
//It can be written as AB (A concatenated with B), where A and B are valid strings, or
//It can be written as (A), where A is a valid string.
    public String minRemoveToMakeValid(String s) {
        StringBuilder res = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < res.length(); ++i) {
            if (res.charAt(i) == '(') st.add(i + 1);
            else if (res.charAt(i) == ')') {
                if (!st.empty() && st.peek() > 0) st.pop();
                else st.add(-(i + 1));
            }
        }
        while (!st.empty()) res.deleteCharAt(Math.abs(st.pop()) - 1);
        return res.toString();
    }

    //301
    //删除括号后所有可能的情况
    public List<String> removeInvalidParentheses(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(i + 1);
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek() > 0) {
                    stack.pop();
                } else {
                    stack.add(-(i + 1));
                }
            }
        }
        //本身就已经合法的
        if (stack.isEmpty()) {
            res.add(sb.toString());
            return res;
        }
        int l = 0, r = 0;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            if (index < 0) {
                r++;
            } else {
                l++;
            }
        }
        dfsValidParentheses(sb, l, r, 0, res);
        return res;

    }

    private boolean isValidParentheses(StringBuilder sb) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.push(i + 1);
            } else if (sb.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek() > 0) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private void dfsValidParentheses(StringBuilder sb, int l, int r, int start, List<String> res) {
        if (l == 0 && r == 0) {
            if (isValidParentheses(sb)) {
                res.add(sb.toString());
            }
            return;
        }

        for (int index = start; index < sb.length(); index++) {
            if (index != start && sb.charAt(index) == sb.charAt(index - 1)) {
                continue;
            }
            if (sb.charAt(index) == ')' && r > 0) {
                StringBuilder tmp = new StringBuilder(sb);
                tmp.deleteCharAt(index);
                dfsValidParentheses(tmp, l, r - 1, index, res);
            } else if (sb.charAt(index) == '(' && l > 0) {
                StringBuilder tmp = new StringBuilder(sb);
                tmp.deleteCharAt(index);
                dfsValidParentheses(tmp, l - 1, r, index, res);
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.removeInvalidParentheses("(a)())()"));
//        s.isValid("([{}])");
    }


}
