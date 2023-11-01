package org.melon.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树算法之Kruskal算法
 * 利用并查集实现
 * 在图中找到权重最小的边，且不会形成环，直到所有的点都被连接起来
 */
public class Kruskal {

    @Test
    public void testKruskal() {
        Graph graph = new Graph();

        // Create nodes
        Graph.Node node1 = new Graph.Node(1);
        Graph.Node node2 = new Graph.Node(2);
        Graph.Node node3 = new Graph.Node(3);
        Graph.Node node4 = new Graph.Node(4);

        // Create edges
        Graph.Edge edge1 = new Graph.Edge(4,node1, node2);
        Graph.Edge edge2 = new Graph.Edge(1,node1, node3);
        Graph.Edge edge3 = new Graph.Edge(3,node2, node3);
        Graph.Edge edge4 = new Graph.Edge(2,node2, node4);
        Graph.Edge edge5 = new Graph.Edge(5,node3, node4);

        // Add nodes and edges to the graph
        graph.nodes.put(1, node1);
        graph.nodes.put(2, node2);
        graph.nodes.put(3, node3);
        graph.nodes.put(4, node4);

        graph.edges.add(edge1);
        graph.edges.add(edge2);
        graph.edges.add(edge3);
        graph.edges.add(edge4);
        graph.edges.add(edge5);

        Set<Graph.Edge> expected = new HashSet<>();
        expected.add(edge2);
        expected.add(edge4);
        expected.add(edge1);

        Kruskal kruskal = new Kruskal();
        Set<Graph.Edge> kruskal1 = kruskal.kruskal(graph);


        Prim prim = new Prim();
        Set<Graph.Edge> prim1 = prim.prim(graph);

        Assertions.assertEquals(kruskal1, prim1);
    }

    public Set<Graph.Edge> kruskal(Graph graph) {

        UnionFind unionFind = new UnionFind();

        PriorityQueue<Graph.Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (Graph.Node node : graph.nodes.values()) {
            unionFind.makeSets(node);
        }

        // 将所有的边放入小根堆中
        queue.addAll(graph.edges);
        Set<Graph.Edge> edges = new HashSet<>();
        while (!queue.isEmpty()) {
            Graph.Edge edge = queue.poll();
            if (unionFind.findHead(edge.from) == unionFind.findHead(edge.to)) {
                continue;
            }
            unionFind.union(edge.from, edge.to);
            edges.add(edge);
        }
        return edges;
    }


    /**
     * 并查集
     */
    static class UnionFind {

        private final HashMap<Graph.Node, Graph.Node> parent;
        private final HashMap<Graph.Node, Integer> sizeMap;

        public UnionFind() {
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Graph.Node node) {
            parent.put(node, node);
            sizeMap.put(node, 1);
        }

        public Graph.Node findHead(Graph.Node node) {
            Graph.Node cur = node;
            Graph.Node parent = this.parent.get(cur);
            List<Graph.Node> help = new ArrayList<>();
            while (parent != cur) {
                cur = parent;
                help.add(cur);
                parent = this.parent.get(cur);
            }
            for (Graph.Node node1 : help) {
                this.parent.put(node1, parent);
            }
            return parent;
        }

        public void union(Graph.Node node1, Graph.Node node2) {
            Graph.Node head1 = findHead(node1);
            Graph.Node head2 = findHead(node2);
            if (head1 != head2) {
                if (sizeMap.get(head1) > sizeMap.get(head2)) {
                    parent.put(head2, head1);
                    sizeMap.put(head1, sizeMap.get(head1) + sizeMap.get(head2));
                    sizeMap.remove(head2);
                } else {
                    parent.put(head1, head2);
                    sizeMap.put(head2, sizeMap.get(head2) + sizeMap.get(head1));
                    sizeMap.remove(head1);
                }
            }
        }

    }
}
