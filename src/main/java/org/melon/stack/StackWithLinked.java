package org.melon.stack;

import org.melon.linkedList.ReverseLinkedList;

/**
 * 使用链表实现一个栈
 * 栈特性：先进后出
 */
public class StackWithLinked {
    private ReverseLinkedList.Node node;

    public StackWithLinked() {

    }

    // 从前面加数据
    public void push(int val) {
        node = new ReverseLinkedList.Node(node, val);
    }

    // 从前面获取数据，并将最后的节点移除
    public int pop() {
        if(node == null) {
            return -1;
        }
        int val = node.val;
        node = node.next;
        return val;
    }

}
