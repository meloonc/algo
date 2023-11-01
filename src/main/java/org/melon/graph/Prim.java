package org.melon.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树算法之Prim算法
 */
public class Prim {

    public Set<Graph.Edge> prim(Graph graph) {

        // 确保不成环
        HashSet<Graph.Node> visited = new HashSet<>();
        // 返回结果
        HashSet<Graph.Edge> result = new HashSet<>();
        // 随机选一个节点
        Graph.Node node = new ArrayList<>(graph.nodes.values()).get(0);
        // 加入到已访问节点中
        visited.add(node);
        // 小根堆
        PriorityQueue<Graph.Edge> queue = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
        queue.addAll(node.edges);
        while (!queue.isEmpty()) {
            // 弹出一个权重最小的边
            Graph.Edge edge = queue.poll();
            // 如果这个边的to节点没有被访问过，加入到已访问节点中，加入到返回结果中，将这个节点的所有边加入到小根堆中
            Graph.Node toNode = edge.to;
            if (!visited.contains(toNode)) {
                visited.add(toNode);
                result.add(edge);
                queue.addAll(toNode.edges);
            }
        }
        return result;


    }
}
