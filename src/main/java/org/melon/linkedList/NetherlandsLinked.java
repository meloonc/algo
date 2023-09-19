package org.melon.linkedList;

/**
 * 链表荷兰国旗
 */
public class NetherlandsLinked {

    public static void main(String[] args) {
        Node node7 = new Node(null, 40);
        Node node6 = new Node(node7, 30);
        Node node5 = new Node(node6, 20);
        Node node4 = new Node(node5, 700);
        Node node3 = new Node(node4, 500);
        Node node2 = new Node(node3, 1000);
        Node node1 = new Node(node2, 1);

        Node netherlands = netherlands(node1, 4);
        printNode(netherlands);
    }

    /**
     * 小于 num 的数在链表左边，等于 num 的数在中间，大于 num 的数在右边
     *
     * @param head
     * @param num
     * @return
     */
    public static Node netherlands(Node head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        Node rightHead = null;
        Node rightTail = null;
        Node middleHead = null;
        Node middleTail = null;
        Node leftHead = null;
        Node leftTail = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val > num) {
                if (rightHead == null) {
                    rightHead = head;
                } else {
                    rightTail.next = head;
                }
                rightTail = head;
            }
            if (head.val == num) {
                if (middleHead == null) {
                    middleHead = head;
                } else {
                    middleTail.next = head;
                }
                middleTail = head;
            }
            if (head.val < num) {
                if (leftHead == null) {
                    leftHead = head;
                } else {
                    leftTail.next = head;
                }
                leftTail = head;
            }
            head = next;
        }
        if (leftTail != null) {
            leftTail.next = middleHead;
            middleTail = middleTail == null ? leftTail : middleTail;
        }
        if (middleTail != null) {
            middleTail.next = rightHead;
        }
        return leftHead == null ? (middleHead == null ? rightHead : middleHead) : leftHead;
    }


    public static void printNode(Node node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
