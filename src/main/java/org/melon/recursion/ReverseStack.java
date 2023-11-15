package org.melon.recursion;

import java.util.Stack;

/**
 * 不是用额外数据结构反转一个栈
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        r(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    // 反转栈
    public static void r(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int p = p(stack);
        r(stack);
        stack.push(p);
    }

    // 弹出栈底元素
    public static int p(Stack<Integer> stack) {
        Integer pop = stack.pop();
        // 如果栈空了，返回栈底元素
        if (stack.isEmpty()) {
            return pop;
        }
        // 继续调用 p 方法，
        int p = p(stack);
        stack.push(pop);
        return p;
    }
}
