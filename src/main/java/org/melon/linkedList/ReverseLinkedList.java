package org.melon.linkedList;

public class ReverseLinkedList {

    public static class Node {
        public Node next;
        public int val;

        public Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    static class DoubleNode {
        DoubleNode previous;
        DoubleNode next;
        int val;

        public DoubleNode(int val) {
            this.val = val;
        }
    }

    // 反转单链表
    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            // 保存下一个节点
            next = head.next;
            // 将当前节点的 next 指向 pre， pre 是上一个节点
            head.next = pre;
            // 将当前节点的 pre 指向 next
            pre = head;
            // head 指向下一个节点，遍历
            head = next;
        }
        // 遍历结束后， pre 会来到翻转之前的最后一个节点，也就是翻转后的头节点
        return pre;
    }

    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            // 多交换一个前节点，指向当前节点的next
            head.previous = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        Node node4 = new Node(null, 4);
        Node node3 = new Node(node4, 3);
        Node node2 = new Node(node3, 2);
        Node node1 = new Node(node2, 1);
        printNode(node1);
        Node node = reverse(node1);
        printNode(node);


        DoubleNode doubleNode4 = new DoubleNode(4);
        DoubleNode doubleNode3 = new DoubleNode(3);
        DoubleNode doubleNode2 = new DoubleNode(2);
        DoubleNode doubleNode1 = new DoubleNode(1);
        doubleNode1.previous = null;
        doubleNode1.next = doubleNode2;
        doubleNode2.previous = doubleNode1;
        doubleNode2.next = doubleNode3;
        doubleNode3.previous = doubleNode2;
        doubleNode3.next = doubleNode4;
        doubleNode4.previous = doubleNode3;
        doubleNode4.next = null;
        printDoubleNode(doubleNode1);
        DoubleNode doubleNode = reverseDoubleNode(doubleNode1);
        printDoubleNode(doubleNode);
    }

    public static void printNode(Node node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void printDoubleNode(DoubleNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}

