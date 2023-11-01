package org.melon.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 迪杰特斯拉算法
 * 1. 从起点开始，找到最近的节点，标记为已访问
 * 2. 从起点到该节点的距离，作为该节点的最短距离
 * 3. 从该节点出发，更新所有相邻节点的最短距离
 * 4. 重复1-3，直到所有节点都被访问过
 */
public class Dijkstra {

    public Map<Graph.Node, Integer> dijkstra(Graph.Node from) {
        HashSet<Graph.Node> visited = new HashSet<>();
        HashMap<Graph.Node, Integer> result = new HashMap<>();
        result.put(from, 0);
        Graph.Node minNode = getMinNode(visited, result);
        while (minNode != null) {
            int distance = result.get(minNode);
            for (Graph.Edge edge : minNode.edges) {
                Graph.Node to = edge.to;
                if (!result.containsKey(to)) {
                    result.put(to, distance + edge.weight);
                } else {
                    result.put(to, Math.min(result.get(to), distance + edge.weight));
                }
            }
            visited.add(minNode);
            minNode = getMinNode(visited, result);
        }
        return result;
    }

    private Graph.Node getMinNode(HashSet<Graph.Node> visited, HashMap<Graph.Node, Integer> nodes) {
        Graph.Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Graph.Node, Integer> entry : nodes.entrySet()) {
            Graph.Node node = entry.getKey();
            int distance = entry.getValue();
            if (!visited.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    // 加强堆实现迪杰特斯拉算法

}
