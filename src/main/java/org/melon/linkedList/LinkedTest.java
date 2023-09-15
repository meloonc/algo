package org.melon.linkedList;


/**
 * 链表测试
 * 1. 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2. 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class LinkedTest {

    public static void main(String[] args) {
        ReverseLinkedList.Node node8 = new ReverseLinkedList.Node(null, 8);
        ReverseLinkedList.Node node7 = new ReverseLinkedList.Node(null, 7);
        ReverseLinkedList.Node node6 = new ReverseLinkedList.Node(node7, 6);
        ReverseLinkedList.Node node5 = new ReverseLinkedList.Node(node6, 5);
        ReverseLinkedList.Node node4 = new ReverseLinkedList.Node(node5, 4);
        ReverseLinkedList.Node node3 = new ReverseLinkedList.Node(node4, 3);
        ReverseLinkedList.Node node2 = new ReverseLinkedList.Node(node3, 2);
        ReverseLinkedList.Node node1 = new ReverseLinkedList.Node(node2, 1);
        System.out.println("test1 值为：" + test1(node1).val);
        ;
        System.out.println("test2 值为：" + test2(node1).val);
        ;
        System.out.println("test3 值为：" + test3(node1).val);
        ;
        System.out.println("test4 值为：" + test4(node1).val);
        ;
    }

    public static ReverseLinkedList.Node test1(ReverseLinkedList.Node head) {
        ReverseLinkedList.Node quick = head;
        ReverseLinkedList.Node slow = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ReverseLinkedList.Node test2(ReverseLinkedList.Node head) {
        ReverseLinkedList.Node quick = head;
        ReverseLinkedList.Node slow = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        // 偶数个
        if (quick.next != null) {
            slow = slow.next;
        }
        return slow;
    }

    public static ReverseLinkedList.Node test3(ReverseLinkedList.Node head) {
        ReverseLinkedList.Node quick = head;
        ReverseLinkedList.Node slow = head;
        ReverseLinkedList.Node pre = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            pre = slow;
            slow = slow.next;
        }
        return pre;
    }

    public static ReverseLinkedList.Node test4(ReverseLinkedList.Node head) {
        ReverseLinkedList.Node quick = head;
        ReverseLinkedList.Node slow = head;
        ReverseLinkedList.Node pre = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            pre = slow;
            slow = slow.next;
        }
        // 偶数个
        if (quick.next != null) {
            return slow;
        }
        return pre;
    }
}
