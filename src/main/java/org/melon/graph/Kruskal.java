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

        graph.addNode(4, 1, 2);
        graph.addNode(1, 1, 3);
        graph.addNode(3, 2, 3);
        graph.addNode(2, 2, 4);
        graph.addNode(5, 3, 4);

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
