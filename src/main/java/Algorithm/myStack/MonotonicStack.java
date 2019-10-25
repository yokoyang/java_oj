package Algorithm.myStack;

import java.util.LinkedList;
import java.util.Stack;

public class MonotonicStack {
    public static void main(String[] args) {
        int a=1;
        int b=2;
        a^=b;
        b^=a;
        a^=b;
        System.out.println(a);
        System.out.println(b);
    }
    //    单调栈
    //    单调栈主要回答这样的几种问题
    //
    //比当前元素更大的下一个元素
    //比当前元素更大的前一个元素
    //比当前元素更小的下一个元素
    //比当前元素更小的前一个元素


    //    leetcode 42
//    Runtime: 59 ms, faster than 5.29%
//  朴素思路，每个点盛水的数目=Max(Min(左边最高,右边最高) - Height[i]),0)
    public int trap1(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMost = 0;
            int rightMost = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMost = Math.max(leftMost, height[j]);
            }
            for (int j = i + 1; j < height.length; j++) {
                rightMost = Math.max(rightMost, height[j]);
            }
            int minWall = Math.min(rightMost, leftMost);
            if (height[i] < minWall) {
                ans += (minWall - height[i]);
            }
        }
        return ans;
    }

    //开辟2个数组，记录每个位置的左最高和右最高
    public int trap2(int[] height) {
        int size = height.length;
        int ans = 0;
        if (size <= 2) {
            return ans;
        }
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                leftMax[i] = 0;
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (i == size - 1) {
                rightMax[i] = 0;
            } else {
                rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
            }
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.max(0, Math.min(rightMax[i], leftMax[i]) - height[i]);
        }
        return ans;
    }

    //Solution 3：
    //这里再介绍一种优化方法，双指针法，在数组首尾分别创建一个指针，两指针相见时结束循环。
    public int trap3(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    ans += (leftMax - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    ans += (rightMax - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    //    用非递增栈来实现，栈里面存储的是坐标
    public int trap4(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peekLast()] < height[i]) {
                int top = stack.peekLast();
                stack.pollLast();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peekLast() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peekLast()]) - height[top];
                ans += distance * boundedHeight;
            }
            stack.offerLast(i);
        }
        return ans;
    }
}
