package BasicCalculator;


import java.util.*;

import java.util.Stack;

public class BasicCalculator {

    /**
     * 运算表达式
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        // 操作数栈
        Stack<Integer> stack = new Stack<>();
        // 正负号
        int sign = 1;
        // 累计结果
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                // 字符转换
                int num = s.charAt(i) - '0';
                // 处理多位整数
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += num * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BasicCalculator solution = new BasicCalculator();
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(solution.calculate("1 + 1 - 3 + 4 - (8 + 2) - 4 + 3 - 1 - 4 + 6 - 9 + 1"));
//        System.out.println(solution.calculate("1-(5)"));
//        System.out.println(solution.calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
