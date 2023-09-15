package org.melon.linkedList;

import static org.melon.linkedList.ReverseLinkedList.printNode;

/**
 * 是否为回文链表
 */
public class PalindromeLinked {

    public static void main(String[] args) {
        ReverseLinkedList.Node node8 = new ReverseLinkedList.Node(null, 1);
        ReverseLinkedList.Node node7 = new ReverseLinkedList.Node(node8, 2);
        ReverseLinkedList.Node node6 = new ReverseLinkedList.Node(node7, 4);
        ReverseLinkedList.Node node5 = new ReverseLinkedList.Node(node6, 3);
        ReverseLinkedList.Node node4 = new ReverseLinkedList.Node(node5, 3);
        ReverseLinkedList.Node node3 = new ReverseLinkedList.Node(node4, 3);
        ReverseLinkedList.Node node2 = new ReverseLinkedList.Node(node3, 2);
        ReverseLinkedList.Node node1 = new ReverseLinkedList.Node(node2, 1);
        printNode(node1);
        boolean palindrome = isPalindrome(node1);
        System.out.println("是否为回文链表" + palindrome);
        printNode(node1);
    }


    public static boolean isPalindrome(ReverseLinkedList.Node head) {
        boolean isPalindrome = true;
        // 如果只有一个节点
        if (head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.next.val == head.val;
        }
        // 快指针
        ReverseLinkedList.Node quick = head;
        // 慢指针
        ReverseLinkedList.Node slow = head;
        // 快指针步长为2
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        // 从中点开始翻转后面的指针
        ReverseLinkedList.Node reverse = reverse(slow);
        // 头尾开始遍历，判断节点的值是否相等
        ReverseLinkedList.Node node1 = head;
        ReverseLinkedList.Node node2 = reverse;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val) {
                isPalindrome = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        // 将之前反转的节点还原
        reverse(reverse);

        return isPalindrome;
    }

    // 反转链表
    public static ReverseLinkedList.Node reverse(ReverseLinkedList.Node head) {
        // 还原链表
        ReverseLinkedList.Node next = null;
        ReverseLinkedList.Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
