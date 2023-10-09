package org.melon.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 * <p>
 *        5
 *      /  \
 *     3    7
 *    / \  / \
 *   2   4 6   8
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
        Queue<String> queue = preSerialized(node5);
        System.out.println(queue);

        Node node = preDeserialized(queue);
        pre(node);

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
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

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

    public static Node preDeserialized(Queue<String> queue){
        if(queue == null || queue.isEmpty()) {
            return null;
        }
        return doPreDeserialized(queue);
    }

    public static Node doPreDeserialized(Queue<String> queue){
        String poll = queue.poll();
        if(poll == null) {
            return null;
        }

        Node node = new Node(Integer.parseInt(poll));
        node.left = doPreDeserialized(queue);
        node.right = doPreDeserialized(queue);
        return node;
    }




}
