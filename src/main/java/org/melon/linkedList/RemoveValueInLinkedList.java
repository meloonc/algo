package org.melon.linkedList;

import static org.melon.linkedList.ReverseLinkedList.printNode;

/**
 * 移除链表中指定的值
 */
public class RemoveValueInLinkedList {

    public static void main(String[] args) {
        ReverseLinkedList.Node node4 = new ReverseLinkedList.Node(null, 4);
        ReverseLinkedList.Node node3 = new ReverseLinkedList.Node(node4, 3);
        ReverseLinkedList.Node node2 = new ReverseLinkedList.Node(node3, 2);
        ReverseLinkedList.Node node1 = new ReverseLinkedList.Node(node2, 1);
        printNode(node1);
        ReverseLinkedList.Node node = remove(node1, 2);
        printNode(node);
    }

    public static ReverseLinkedList.Node remove(ReverseLinkedList.Node head, int val) {
        // 找到非指定值的节点
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        //
        ReverseLinkedList.Node pre = head;
        ReverseLinkedList.Node cur = head;
        while (cur != null) {
            // 如果找到了指定值的节点，跳过 cur 节点，即将 pre 的 next 节点指向 cur 的 next
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                // 如果不是指定值的节点，pre 的位置就是 cur 的位置
                pre = cur;
            }
            // cur 来到下一个位置
            cur = cur.next;
        }
        // 返回头节点
        return head;
    }
}
