package org.melon.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序
 * 有向无环图
 */
public class TopologySort {

    /**
     * 使用入度实现拓扑排序
     * 入度为 0 排在前面，排好序的节点需要删除出度
     */
    public List<Graph.Node> topology(Graph graph) {
        // 记录所有节点当前的入度
        HashMap<Graph.Node, Integer> inMap = new HashMap<>();
        // 入度为 0 的节点存进队列
        LinkedList<Graph.Node> inQueue = new LinkedList<>();
        // 遍历所有节点
        for (Graph.Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            // 节点入度为 0 进入队列
            if (node.in == 0) {
                inQueue.add(node);
            }
        }
        List<Graph.Node> result = new ArrayList<>();
        while (!inQueue.isEmpty()) {
            // 从队列取出节点加进结果集
            Graph.Node node = inQueue.poll();
            result.add(node);
            // 遍历子节点，将子节点的入度 - 1
            for (Graph.Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                // 如果当前子节点的入度为0 放进队列
                if (inMap.get(next) == 0) {
                    inQueue.add(next);
                }
            }
        }
        return result;
    }
}
