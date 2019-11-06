package Algorithm.myStack;

import java.util.Stack;

public class MInStack {
    //    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
    //    （时间复杂度应为O（1））。
    //data stack存储原始的压栈信息
    //minStack存储每次的较小值
    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else {
            minStack.push(Math.min(minStack.peek(), node));
        }
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
