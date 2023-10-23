package org.melon.tree;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LeetCode 333题的题目是"最大 BST 子树"，英文标题是"最大 BST Subtree"。这是一道关于二叉树的问题，你需要在给定的二叉树中找到具有最大BST结构的子树，并返回该子树的节点数。
 * <p>
 * 题目描述：
 * 给定一个二叉树，找到其中最大的BST子树。BST子树意味着子树的所有节点必须满足以下条件：
 * <p>
 * 子树的根节点的值大于左子树中的所有节点的值。
 * 子树的根节点的值小于右子树中的所有节点的值。
 * 左子树和右子树也必须是BST。
 * 你需要返回最大BST子树的节点数量。
 * <p>
 * 这个问题通常需要使用递归算法来解决。你可以按照以下步骤来解决它：
 * <p>
 * 从根节点开始，递归地检查每个子树是否是BST。
 * 对于每个子树，记录其节点数量，并检查是否是BST。
 * 如果子树是BST，比较其节点数量与当前已知的最大BST子树的节点数量，更新最大值。
 * 递归地继续检查左子树和右子树。
 */
public class MaxBSTSubTree {

    public static void main(String[] args) {
        System.out.println();
    }

    public int maxBSTSubTree(Node root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxSubSize;
    }

    @Data
    static class Info {
        // 所有节点数量
        private int size;
        // 最大子树节点数量
        private int maxSubSize;
        // 节点最大值
        private int max;
        // 节点最小值
        private int min;

        public Info(int size, int maxSubSize, int max, int min) {
            this.size = size;
            this.maxSubSize = maxSubSize;
            this.max = max;
            this.min = min;
        }

        public boolean isBST() {
            return this.size == this.maxSubSize;
        }
    }

    public Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int max = node.val;
        int min = node.val;
        int size = 1;
        boolean isBST = true;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            size += leftInfo.size;
            if (!leftInfo.isBST() || leftInfo.max >= node.val) {
                isBST = false;
            }
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            size += rightInfo.size;
            if (!rightInfo.isBST() || rightInfo.min <= node.val) {
                isBST = false;
            }
        }


        // 如果当前节点不是二叉搜索树，最大搜索子树节点大小需要获取子树的最大节点数
        int maxSubSize = 0;
        if (!isBST) {
            if (leftInfo != null) {
                maxSubSize = leftInfo.maxSubSize;
            }
            if (rightInfo != null) {
                maxSubSize = Math.max(maxSubSize, rightInfo.maxSubSize);
            }
        } else {
            // 如果当前节点是二叉搜索数，最大搜索子树节点大小就是子树节点数量之和+1
            maxSubSize = size;
        }
        return new Info(size, maxSubSize, max, min);
    }

    @Test
    public void testMaxBSTSubTree() {
        // Testing an empty tree
        Node root1 = null;
        assertEquals(0, maxBSTSubTree(root1));

        // Testing a tree with a single node
        Node root2 = new Node(10);
        assertEquals(1, maxBSTSubTree(root2));

        // Testing a tree with only left children
        Node root3 = new Node(10);
        root3.left = new Node(5);
        root3.left.left = new Node(3);
        root3.left.right = new Node(7);
        assertEquals(4, maxBSTSubTree(root3));

        // Testing a tree with only right children
        Node root4 = new Node(10);
        root4.right = new Node(15);
        root4.right.right = new Node(12);
        root4.right.left = new Node(20);
        assertEquals(1, maxBSTSubTree(root4));

        // Testing a valid BST tree
        Node root5 = new Node(10);
        root5.left = new Node(5);
        root5.right = new Node(15);
        root5.left.left = new Node(3);
        root5.left.right = new Node(7);
        root5.right.left = new Node(12);
        root5.right.right = new Node(20);
        assertEquals(7, maxBSTSubTree(root5));

        // Testing a tree with multiple BST subtrees
        Node root6 = new Node(10);
        root6.left = new Node(5);
        root6.right = new Node(15);
        root6.left.left = new Node(3);
        root6.left.right = new Node(7);
        root6.right.left = new Node(12);
        root6.right.right = new Node(20);
        root6.right.left.left = new Node(11);
        root6.right.right.left = new Node(18);
        assertEquals(9, maxBSTSubTree(root6));
    }


}
