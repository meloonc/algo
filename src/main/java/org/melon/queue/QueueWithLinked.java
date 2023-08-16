package org.melon.queue;

import org.melon.linkedList.ReverseLinkedList;

/**
 * 使用链表实现一个队列
 * 队列的特性：先进先出
 */
public class QueueWithLinked {
    private ReverseLinkedList.Node node;

    public QueueWithLinked() {
    }

    // 出 从前面获取数据，并将头节点移到下一个
    public int pop() {
        if (node == null) {
            return -1;
        }
        int val = node.val;
        node = node.next;
        return val;
    }

    // 进 从后面加数据
    public void push(int val) {
        if (this.node == null) {
            this.node = new ReverseLinkedList.Node(null, val);
        } else {
            ReverseLinkedList.Node tail = node;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new ReverseLinkedList.Node(null, val);
        }
    }


    public void print() {
        ReverseLinkedList.Node printNode = node;
        while (printNode != null) {
            System.out.println(printNode.val);
            printNode = printNode.next;
        }
    }

    public static void main(String[] args) {
        QueueWithLinked queue = new QueueWithLinked();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        int pop = queue.pop();
        queue.print();
        int pop1 = queue.pop();
        queue.print();
    }

}
