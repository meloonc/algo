package org.melon.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }


    // 单测
    @Test
    public void test() {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        Node p = root.left;
        Node q = root.left.right.right;
        Node ancestor = lowestCommonAncestor(root, p, q);
        System.out.println(ancestor.val);
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        return process(root, p, q).ancestor;
    }

    public Info process(Node root, Node p, Node q) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        boolean findP = root == p || leftInfo.findP || rightInfo.findP;
        boolean findQ = root == q || leftInfo.findQ || rightInfo.findQ;
        Node ancestor = null;
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        }

        if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        }

        if(findP && findQ && ancestor == null){
            ancestor = root;
        }
        return new Info(findP, findQ, ancestor);

    }


    /**
     * 信息类
     * 1. 节点是否包含p
     * 2. 节点是否包含q
     * 3. 最近公共祖先节点
     */
    class Info {
        private Boolean findP;
        private Boolean findQ;
        private Node ancestor;

        public Info(Boolean findP, Boolean findQ, Node ancestor) {
            this.findP = findP;
            this.findQ = findQ;
            this.ancestor = ancestor;
        }
    }


}
