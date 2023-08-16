package org.melon.stack;

/**
 * 实现一个栈，提供 push pop getMin 方法，时间复杂度为 O(1)
 * 实现思路
 * 1,提供两个栈，数据栈正常 push 数据
 * 2，最小栈 push 时会和最小栈的栈顶比较大小，如果栈中的数据小，push 栈里面的数据
 */
public class MinInStack {

    private int limit;
    private StackWithArray dataStack;
    private StackWithArray minStack;

    public MinInStack(int limit) {
        this.limit = limit;
        dataStack = new StackWithArray(limit);
        minStack = new StackWithArray(limit);
    }

    public void push(int val) {
        dataStack.push(val);
        int min = Math.min(minStack.peek(), val);
        minStack.push(min);

    }

    public int pop() {
        minStack.pop();
        return dataStack.pop();
    }

}
