package org.melon.linkedList;

/**
 * 判断两个链表是否相交，如果相交返回第一个相交的节点
 * 链表可能有环，可能无环
 */
public class IntersectLinked {

    public static void main(String[] args) {
            /**********两个链否相交*********/
//        Node common6 = new Node(8);
//        Node common5 = new Node(common6,13);
//        Node common4 = new Node(common5,11);
//        Node common3 = new Node(common4,32);
//        Node common2 = new Node(common3,12);
//        Node common1 = new Node(common2,3);
//        Node common = new Node(common1,3);

        //
//
//        Node res = null;
//        // 如果相交，链表要么都无环要么都有环
//        if (!isLoopNode(node1) && !isLoopNode(node2)) {
//            res = noLoopNodeIntersect(node1, node2);
//        } else if (isLoopNode(node1) && isLoopNode(node2)) {
//            res = loopNodeIntersect(node1, node2);
//        }
//        if (res != null) {
//            System.out.println("链表相交：" + res.val);
//        }else {
//            System.out.println("链表不相交");
//        }
        /*****/

        /*********单链表是否相交 ******/
         // 单链表是否相交
//        Node head1 = new Node(4);
//        Node node2 = new Node(1);
//        Node node3 = new Node(8);
//        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//
//        head1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//
//
//
//        Node head2 = new Node(5);
//        Node node1 = new Node(6);
//        Node node6 = new Node(1);
//
//        head2.next = node1;
//        node1.next = node6;
//        node6.next = node3;



//        Node node1 = new Node(4);
//        Node node1 = new Node(4);
//        Node node1 = new Node(4);
//        Node node1 = new Node(4);
//        Node node1 = new Node(4);
//        Node node2 = new Node(common,2);

//        Node res = noLoopNodeIntersect(head1, head2);
//        if (res != null) {
//            System.out.println("链表相交：" + res.val);
//        }else {
//            System.out.println("链表不相交");
//        }
        /********************/

        /**************链表是否成环并返回相交节点**********/
        Node head1 = new Node(3);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(-4);

        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;


        Node fast = head1.next.next ;
        Node slow = head1.next;
        // 快慢指针找到相遇的节点
        while (fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 快节点从头开始遍历，慢指针继续遍历
        // 相遇节点就是首个相交节点
        fast = head1;
        while (fast != null) {
            if(fast == slow) {
                System.out.println("链表成环，相交节点：" + fast.val);
                break;
//                return fast;
            }
            fast = fast.next;
            slow = slow.next;
        }
        System.out.println("链表没成环");




    }


    /**
     * 有环节点相交
     * <p>
     * 思路：一个链表使用快指针，一个链表使用慢指针，当两个指针相遇，快指针回到头节点，快指针步长变成 1，然后两个指针同时开始遍历，如果节点相遇则为首个相交节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node loopNodeIntersect(Node head1, Node head2) {
        Node fast = head1 ;
        Node slow = head2;
        // 快慢指针找到相遇的节点
        while (fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 快节点从头开始遍历，慢指针继续遍历
        // 相遇节点就是首个相交节点
        fast = head1;
        while (fast != null) {
            if(fast == slow) {
                return fast;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * 无环节点相交
     * <p>
     * 思路：将两个链表遍历到同样的长度，然后接着一起遍历，如果节点是同一个，就是相交节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoopNodeIntersect(Node head1, Node head2) {
        int length1 = 0;
        int length2 = 0;
        Node cur1 = head1;
        // 计算链表长度
        while (cur1 != null) {
            length1++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        while (cur2 != null) {
            length2++;
            cur2 = cur2.next;
        }
        cur1 = head1;
        cur2 = head2;
        // 将链表遍历到相同长度
        if (length1 > length2) {
            while (length1 - length2 > 0) {
                cur1 = cur1.next;
                length1--;
            }
        } else {
            while (length2 - length1 > 0) {
                cur2 = cur2.next;
                length2--;
            }
        }
        // 相同长度的链表开始遍历，如果节点相等就是相交节点
        while (cur1 != null && cur2 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }

    /**
     * 判断是否有环
     *
     * @return
     */
    public static boolean isLoopNode(Node node) {
        if (node == null || node.next == null) {
            return false;
        }
        Node fast = node;
        Node slow = node;
        // 快慢指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
