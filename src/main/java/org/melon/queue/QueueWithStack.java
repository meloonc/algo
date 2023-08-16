package org.melon.queue;

import java.util.Stack;

/**
 * 使用队列实现栈
 * 使用两个栈，一个栈用来 push 数据，一个栈用来 pop 数据
 * 每次操作都需要将 push 栈的数据转存到 pop 栈
 * 转存时机：push 栈不为空，pop 栈为空，如果 pop 栈还有数据时 push ，会导致数据被覆盖
 */
public class QueueWithStack {

    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public void push(Integer i) {
        pushStack.push(i);
        pushToPop();
    }

    public Integer pop() {
        pushToPop();
        return popStack.pop();
    }

    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }
}
