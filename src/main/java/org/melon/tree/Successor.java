package org.melon.tree;

/**
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * <p>
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 */
public class Successor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    public TreeNode findSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        if(node.right != null) {
            // 找到右子树的最左节点
            return findMostLeft(node.right);
        } else {
            // 找到祖先节点
            TreeNode parent = node.parent;
            // 如果当前节点是父节点的左孩子，那么父节点就是后继节点
            // 如果当前节点是父节点的右孩子，那么继续向上找，直到找到一个节点是它父节点的左孩子，那么父节点就是后继节点
            // 如果找到根节点都没有找到，那么当前节点就是最后一个节点，没有后继节点
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;

        }
    }


    public TreeNode findMostLeft(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}


