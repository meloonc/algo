package org.melon.tree;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 二叉树
 * <p>
 * 5
 * /  \
 * 3    7
 * / \  / \
 * 2   4 6   8
 * </p>
 * 先序遍历：5 3 2 4 7 6 8 （头 左 右）
 * <p>
 * 中序遍历：2 3 4 5 6 7 8 （左 头 右）
 * <p>
 * 后序遍历：2 4 3 6 8 7 5 （左 右 头）
 * <p>
 * <p>
 * 递归序
 */
public class BinaryTree {

    public static void main(String[] args) {
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node5.left = node3;
        node5.right = node7;
        node3.left = node2;
        node3.right = node4;
        node7.left = node6;
        node7.right = node8;
//        recursion(node5);
        System.out.println("================");
//        pre(node5);
//        post(node5);
//        in(node5);
//        Queue<String> queue = preSerialized(node5);
//        System.out.println(queue);
//
//        Node node = preDeserialized(queue);
//        pre(node);


        Node node11 = new Node(1);
        Node node12 = new Node(2);
        Node node13 = new Node(3);
        Node node14 = new Node(4);
        node11.left = node12;
        node12.right = node13;
        node13.left = node14;
        level(node5);
//        Queue<String> levelQueue = levelSerialized(node11);
//        System.out.println(levelQueue);

//        level(levelDeserialized(levelQueue));
    }

    /**
     * 递归
     *
     * @param node
     */
    public static void recursion(Node node) {
        if (node == null) {
            return;
        }
        // 先序
//        System.out.println(node.val);
        recursion(node.left);
        // 中序
//        System.out.println(node.val);
        recursion(node.right);
        // 后序
        System.out.println(node.val);
    }

    /**
     * 非递归先序
     *
     * @param node
     */
    public static void pre(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
    }

    /**
     * 中序
     *
     * @param node
     */
    public static void in(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.add(node);
                node = node.left;
            } else {
                Node pop = stack.pop();
                System.out.println(pop.val);
                node = pop.right;
            }
        }

    }

    /**
     * 后序
     *
     * @param node
     */
    public static void post(Node node) {
        if (node == null) {
            return;
        }
        // stack1 顺序为 头右左
        // 翻转 stack1 顺序 左右头 变成后序
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.add(node);
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.add(node);
            if (node.left != null) {
                stack1.add(node.left);
            }
            if (node.right != null) {
                stack1.add(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }

    // 按层遍历 5 3 7 2 4 6 8
    public static void level(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        Node curLevelEnd = node;
        Node nextLevelEnd = null;
        int maxLength = 0;
        int curLevelLength = 0;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            curLevelLength++;
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLevelEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelEnd = node.right;
            }
            if (node == curLevelEnd) {
                curLevelEnd = nextLevelEnd;
                maxLength = Math.max(maxLength, curLevelLength);
                curLevelLength = 0;
            }
        }
        System.out.println("binary tree max length : " + maxLength);
    }
    /**
     * @javadoc
     * 中序无序列化，会有歧义，不同的数序列化结果可能是一样的
     */

    /**
     * 先序序列化
     *
     * @return
     */
    public static Queue<String> preSerialized(Node node) {
        Queue<String> list = new LinkedList<>();
        doPreSerialized(node, list);
        return list;
    }

    private static void doPreSerialized(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(node.val));
            doPreSerialized(node.left, queue);
            doPreSerialized(node.right, queue);
        }
    }

    public static Node preDeserialized(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return doPreDeserialized(queue);
    }

    public static Node doPreDeserialized(Queue<String> queue) {
        String poll = queue.poll();
        if (poll == null) {
            return null;
        }

        Node node = new Node(Integer.parseInt(poll));
        node.left = doPreDeserialized(queue);
        node.right = doPreDeserialized(queue);
        return node;
    }

    /**
     * 层序列化
     *
     * @param node
     * @return
     */
    public static Queue<String> levelSerialized(Node node) {
        Queue<String> queue = new LinkedList<>();
        if (node == null) {
            queue.add(null);
            return queue;
        }
        Queue<Node> levelQueue = new LinkedList<>();
        levelQueue.add(node);
        while (!levelQueue.isEmpty()) {
            Node cur = levelQueue.poll();
            if (cur == null) {
                queue.add(null);
            } else {
                queue.add(String.valueOf(cur.val));
                levelQueue.add(cur.left);
                levelQueue.add(cur.right);
            }
        }
        return queue;
    }

    /**
     * 层反序列化
     *
     * @param queue
     * @return
     */
    public static Node levelDeserialized(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Node head = generateNode(queue.poll());
        Queue<Node> nodes = new LinkedList<>();
        if (head != null) {
            nodes.add(head);
        }
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = nodes.poll();
            cur.left = generateNode(queue.poll());
            cur.right = generateNode(queue.poll());
            if (cur.left != null) {
                nodes.add(cur.left);
            }
            if (cur.right != null) {
                nodes.add(cur.right);
            }
        }
        return head;
    }

    private static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }

    /**
     * 是否为完全二叉树
     * <p>
     * 1.完全二叉树，如果有右孩子没有左孩子，直接返回false
     * 2.完全二叉树，如果有左孩子没有右孩子，那么后面的节点都必须是叶子节点
     *
     * @param node
     * @return
     */
    private static Boolean isCompleteTree(Node node) {
        if (node == null) {
            return true;
        }
        boolean leaf = false;
        Node l = null;
        Node r = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            l = poll.left;
            r = poll.right;
            // 1. 如果有右子节点但是没有左子节点则不是完全二叉树
            // 2. 如果遇到过叶子节点，但是后面还有非叶子节点，则不是完全二叉树
            if (l == null && r != null || leaf && l != null) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            // 找到叶子节点，后面的节点都必须是叶子节点
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;

    }

    /**
     * 使用递归判断是否为完全二叉树
     * 1. 左右子树都是满的，高度一致
     * 2. 左子树是完全二叉树，右子树是满二叉树，左子树高度比右子树高度大1
     * 3. 左右子树都是满的，左子树高度比右子树高度大1
     * 4. 左子树是满二叉树，右子树是完全二叉树，左子树高度和右子树高度一致
     *
     * @param node
     * @return
     */
    private static Boolean isCompleteTree2(Node node) {
        if (node == null) {
            return true;
        }
        return doIsCompleteTree2(node).isComplete;
    }

    static class CompleteInfo {
        boolean isComplete;
        boolean isFull;
        int height;

        public CompleteInfo(boolean isComplete, boolean isFull, int height) {
            this.isComplete = isComplete;
            this.isFull = isFull;
            this.height = height;
        }
    }

    private static CompleteInfo doIsCompleteTree2(Node node) {
        if (node == null) {
            return new CompleteInfo(true, true, 0);
        }


        CompleteInfo leftInfo = doIsCompleteTree2(node.left);
        CompleteInfo rightInfo = doIsCompleteTree2(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isComplete = false;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isComplete = true;
        } else if (leftInfo.isComplete && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
            isComplete = true;
        } else if (leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height) {
            isComplete = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
            isComplete = true;
        }

        return new CompleteInfo(isComplete, isFull, height);
    }


    @Test
    public void testIsCompleteTree2() {
        // Testing an empty tree
        assertTrue(isCompleteTree2(null));

        // Testing a tree with only one node
        Node root = new Node(1);
        assertTrue(isCompleteTree2(root));

        // Testing a complete binary tree
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        assertTrue(isCompleteTree2(root));

        // Testing a tree where left subtree is full and right subtree is complete
        //        1
        //       / \
        //      2   3
        //     / \ /
        //    4  5 6
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        assertTrue(isCompleteTree2(root));

        // Testing a tree where left subtree is complete and right subtree is full
        //        1
        //       / \
        //      2   3
        //     / \
        //    4  5
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        assertTrue(isCompleteTree2(root));

        // Testing a tree where left subtree height is greater than right subtree height
        //        1
        //       /
        //      2
        //     / \
        //    3   4
        root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        assertFalse(isCompleteTree2(root));

        // Testing a tree where left subtree height is equal to right subtree height
        //        1
        //       / \
        //      2   3
        //     /   / \
        //    4   5   6
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        assertFalse(isCompleteTree2(root));
    }

    /**
     * 是否为满二叉树
     * 满二叉树的节点数为2^h - 1
     *
     * @param node
     * @return
     */
    private static boolean isFullTree(Node node) {
        if (node == null) {
            return true;
        }
        FullInfo fullInfo = doIsFullTree(node);

        return (2 ^ fullInfo.height - 1) == fullInfo.nodes;
    }

    static class FullInfo {
        // 高度
        int height;
        // 节点数量
        int nodes;

        public FullInfo(int h, int n) {
            this.height = h;
            this.nodes = n;
        }
    }

    private static FullInfo doIsFullTree(Node node) {
        if (node == null) {
            return new FullInfo(0, 0);
        }
        FullInfo leftInfo = doIsFullTree(node.left);
        FullInfo rightInfo = doIsFullTree(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new FullInfo(height, nodes);
    }


    // 二叉树的递归套路
    //
    // 树形DP 递归套路
    // 1.假设以X节点为头，假设可以向X左树和X右树要任何信息
    // 2.在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
    //

    /**
     * 是否为平衡二叉树
     * 每一个节点的左子树和右子树的高度差不超过1
     *
     * @param node
     * @return
     */
    private static boolean isBalanceTree(Node node) {
        if (node == null) {
            return true;
        }
        return doIsBalanceTree(node).isBalance;
    }

    /**
     * 子节点是否为平衡二叉树所需的信息
     */
    @Data
    static class BalanceInfo {
        // 子数是否为平衡二叉树
        boolean isBalance;
        // 子树的高度
        int height;

        public BalanceInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    private static BalanceInfo doIsBalanceTree(Node node) {
        if (node == null) {
            return new BalanceInfo(true, 0);
        }
        BalanceInfo leftInfo = doIsBalanceTree(node.left);
        BalanceInfo rightInfo = doIsBalanceTree(node.right);
        // 通过子树的信息来判断当前节点是否为平衡二叉树
        boolean isBalance = true;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if (!leftInfo.isBalance || !rightInfo.isBalance) {
            isBalance = false;
        }
        // 判断子树高度差
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalance = false;
        }
        return new BalanceInfo(isBalance, height);
    }


    /**
     * 是否为搜索二叉树
     * 左子树的值都小于当前节点的值，右子树的值都大于当前节点的值
     *
     * @param node
     * @return
     */
    private static boolean isSearchTree(Node node) {
        if (node == null) {
            return true;
        }
        return doIsSearchTree(node).isSearch;
    }

    /**
     * 是否为搜索树的信息
     */
    static class SearchInfo {
        // 子树是否为搜索树
        boolean isSearch;
        // 子树中最大的值
        int max;
        // 子树中最小的值
        int min;

        public SearchInfo(boolean isSearch, int max, int min) {
            this.isSearch = isSearch;
            this.max = max;
            this.min = min;
        }
    }

    private static SearchInfo doIsSearchTree(Node node) {
        if (node == null) {
            return null;
        }
        SearchInfo leftInfo = doIsSearchTree(node.left);
        SearchInfo rightInfo = doIsSearchTree(node.right);
        // 通过子树的信息判断当前节点是否为搜索树
        boolean isSearch = true;
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            if (!leftInfo.isSearch || leftInfo.max >= node.val) {
                isSearch = false;
            }
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            if (!rightInfo.isSearch || rightInfo.min <= node.val) {
                isSearch = false;
            }
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        return new SearchInfo(isSearch, max, min);
    }

    /**
     * 获取二叉树的最大距离
     * <p>
     * 1. 最大距离经过当前节点，最大距离就是左右子树最大高度+1
     * 2. 最大距离不经过当前节点，最大距离就是左右子树中最大的距离
     *
     * @param node
     * @return
     */
    private static int getMaxDistance(Node node) {
        return doGetMaxDistance(node).height;
    }

    static class MaxDistanceInfo {
        int maxDistance;
        int height;

        public MaxDistanceInfo(int m, int h) {
            this.maxDistance = m;
            this.height = h;
        }
    }

    private static MaxDistanceInfo doGetMaxDistance(Node node) {
        if (node == null) {
            return new MaxDistanceInfo(0, 0);
        }

        MaxDistanceInfo leftMax = doGetMaxDistance(node.left);
        MaxDistanceInfo rightMax = doGetMaxDistance(node.right);
        int height = Math.max(leftMax.height, rightMax.height) + 1;
        int maxDistance = Math.max(height, Math.max(leftMax.maxDistance, rightMax.maxDistance));
        return new MaxDistanceInfo(height, maxDistance);
    }

    @Test
    public void testGetMaxDistance() {
        // Test case 1: Empty tree
        Node node1 = null;
        int result1 = getMaxDistance(node1);
        assertEquals(0, result1);

        // Test case 2: Tree with only one node
        Node node2 = new Node(5);
        int result2 = getMaxDistance(node2);
        assertEquals(1, result2);

        // Test case 3: Tree with two nodes
        Node node3 = new Node(5);
        node3.left = new Node(3);
        int result3 = getMaxDistance(node3);
        assertEquals(2, result3);

        // Test case 4: Tree with multiple nodes
        Node node4 = new Node(5);
        node4.left = new Node(3);
        node4.right = new Node(8);
        node4.right.left = new Node(7);
        node4.right.right = new Node(11);
        int result4 = getMaxDistance(node4);
        assertEquals(3, result4);
    }


}
