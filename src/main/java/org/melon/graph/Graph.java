package org.melon.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 图
 * 算法并不难，难点在于如何将给出的结构（二维数组）转换成图结构
 */
public class Graph {
    // 图中的节点集合
    HashMap<Integer, Node> nodes;
    // 图中的边集合
    HashSet<Edge> edges;

    static class Edge {
        // 边的权重
        int weight;
        // 边的起始节点
        Node from;
        // 边的终止节点
        Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    static class Node {
        // 节点值
        int value;
        // 入度，有多少条边指向该节点
        int in;
        // 出度，有多少条边从该节点指出
        int out;
        // 从该节点指向的节点
        List<Node> nexts;
        // 从该节点指向的边
        List<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public void addNode(int weight, int from, int to) {
        if (!nodes.containsKey(from)) {
            Node fromNode = new Node(from);
            nodes.put(from, fromNode);
        }
        if (!nodes.containsKey(to)) {
            Node toNode = new Node(to);
            nodes.put(to, toNode);
        }
        Node fromNode = nodes.get(from);
        fromNode.out++;
        Node toNode = nodes.get(to);
        toNode.in++;
        fromNode.nexts.add(toNode);
        Edge edge = new Edge(weight, fromNode, toNode);
        edges.add(edge);
        fromNode.edges.add(edge);
    }

    /**
     * 广度优先遍历
     * 和遍历二叉树一样，只是多个 hashSet 的判断
     * 在图中，子节点可能会是重复的，如果之前遍历过这不需要遍历了
     */
    private void bfs(Node head) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(head);
        set.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 同样，深度优先遍历也需要 hashSet 来判断是否遍历过
     */
    private void dfs(Node head) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(head);
        set.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if(!set.contains(next)) {
                    // 需要将当前节点和子节点都压入栈中，这样才能保证当前节点的所有子节点都能遍历到
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    // 如果找到了一个子节点，就不遍历剩余的子节点，直接跳出循环
                    // 开始下一轮的出栈并遍历子节点
                    break;
                }
            }
        }
    }



    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode(1, 1, 2);
        graph.addNode(1, 2, 5);
        graph.addNode(1, 2, 3);
        graph.addNode(1, 3, 4);
        graph.addNode(1, 5, 6);
        graph.addNode(1, 4, 6);
        graph.bfs(graph.nodes.get(1));
        System.out.println("============");
        graph.dfs(graph.nodes.get(1));
    }
}



