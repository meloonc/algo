package org.melon.linkedList;

public class Node {
    public Node next;
    public int val;

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }

    public Node(int val) {
        this.val = val;
        this.next = null;
    }


}
