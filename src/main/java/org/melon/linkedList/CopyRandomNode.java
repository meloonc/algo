package org.melon.linkedList;

/**
 * 深拷贝链表，带随机指针
 */
public class CopyRandomNode {

    public static void main(String[] args) {
        RandomNode node7 = new RandomNode(null, null,40);
        RandomNode node6 = new RandomNode(node7, node7,30);
        RandomNode node5 = new RandomNode(node6, node7,20);
        RandomNode node4 = new RandomNode(node5, node7,700);
        RandomNode node3 = new RandomNode(node4, node6,500);
        RandomNode node2 = new RandomNode(node3, node3,1000);
        RandomNode node1 = new RandomNode(node2, node2,1);

        RandomNode copy = copy(node1);
        printNode(copy);
        System.out.println("=============");
        printNode(node1);
    }

    public static RandomNode copy(RandomNode head) {
        RandomNode next = null;
        RandomNode cur = head;
        // 插入拷贝节点
        while (cur != null) {
            next = cur.next;
            cur.next = new RandomNode(next, null, cur.val);
            cur = next;
        }
        // 设置拷贝节点的 random 指针
        cur = head;
        while (cur != null && cur.next != null) {
            if(cur.random == null) {
                cur.next.random = null;
            } else {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 分离出拷贝链表
        RandomNode nextCopy = null;
        RandomNode copyNode = null;
        RandomNode copyHead = null;
        while(head != null) {
            nextCopy = head.next;
            next = nextCopy.next;
            nextCopy.next = null;
            if(copyNode != null) {
                copyNode.next = nextCopy;
            }else {
                copyHead = nextCopy;
            }
            copyNode = nextCopy;
            head.next = next;
            head = next;
        }
        return copyHead;
    }

    public static void printNode(RandomNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


}

class RandomNode {
    public RandomNode next;
    public RandomNode random;
    public int val;

    public RandomNode(RandomNode next, RandomNode random, int val) {
        this.next = next;
        this.random = random;
        this.val = val;
    }
}

