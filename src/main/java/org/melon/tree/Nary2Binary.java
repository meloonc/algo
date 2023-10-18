package org.melon.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 431 题的题目是 "Encode N-ary Tree to Binary Tree and Decode Binary Tree to N-ary Tree"。这道题的目标是将一个 N 叉树编码为二叉树，然后再将二叉树解码为原始的 N 叉树。
 * <p>
 * 具体而言，你需要实现以下两个函数：
 * <p>
 * 1. `TreeNode encode(Node root)`：这个函数接收一个 N 叉树的根节点，然后返回一个二叉树的根节点，以特定的编码方式表示 N 叉树。
 * <p>
 * 2. `Node decode(TreeNode root)`：这个函数接收一个编码后的二叉树的根节点，然后返回原始的 N 叉树的根节点。
 * <p>
 * 编码的规则如下：
 * - N 叉树中的每个节点都包含一个值，以及它的子节点列表。
 * - 在编码时，你需要将 N 叉树的节点转化为二叉树节点，其中左孩子指向 N 叉树的子节点，右孩子指向 N 叉树的兄弟节点。
 * - 解码时，需要还原 N 叉树的结构。
 * <p>
 * 这个问题需要你设计一种巧妙的编码和解码方法，以便在转化过程中不丢失原始 N 叉树的信息。具体的解决方案可能涉及使用递归或其他数据结构和算法的技巧。
 */
public class Nary2Binary {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);
        node.children = new ArrayList<>();
        node.children.add(node1);
        node.children.add(node2);
        node.children.add(node3);
        node1.children = new ArrayList<>();
        node1.children.add(node4);
        node1.children.add(node5);
        node1.children.add(node6);
        node2.children = new ArrayList<>();
        node2.children.add(node7);
        TreeNode treeNode = encode(node);
        System.out.println(treeNode);

        Node decode = decode(treeNode);
        System.out.println(decode);
    }

    /**
     *
     * <p>
     *              a                               a
     *          /   ｜  \                         /
     *        b      c    d   --->               b
     *      / ｜ \  | ｜                      /    \
     *     e   f g  h i                     e       c
     *                                      \     / \
     *                                      f    h  d
     *                                      \    \
     *                                       g    i
     *
     *
     * 将多叉树转为二叉树
     * 多叉树的子节点全部放在二叉树的左边节点上
     * 右节点是原来的兄弟节点，左节点是子节点
     *
     * </p>
     * @param root
     * @return
     */
    public static TreeNode encode(Node root) {
        if(root == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(root.val);

        return doEncode(treeNode, root.children);
    }

    public static TreeNode doEncode(TreeNode treeNode, List<Node> children){
        if(children == null || children.size() == 0) {
            return treeNode;
        }
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = doEncode(new TreeNode(child.val), child.children);
            if(head != null) {
                cur.right = tNode;
                cur = cur.right;
            }else {
                head = tNode;
                cur = head;
            }
        }
        treeNode.left = head;
        return treeNode;
    }



    /**
     * 将二叉树转为多叉树
     *
     * @param root
     * @return
     */
    public static Node decode(TreeNode root) {
        if(root == null) {
            return null;
        }
        Node node = new Node(root.val);
        return doDecode(node, root);
    }

    public static Node doDecode(Node node, TreeNode treeNode) {
        if(treeNode == null) {
            return node;
        }
        List<Node> children = new ArrayList<>();
        TreeNode cur = treeNode.left;
        while (cur != null) {
            children.add(doDecode(new Node(cur.val), cur));
            cur = cur.right;
        }
        node.children = children;
        return node;
    }


}
