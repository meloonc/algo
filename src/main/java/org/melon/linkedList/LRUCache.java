package org.melon.linkedList;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/lru-cache/">LRU Cache</a>
 */
public class LRUCache {

    private HashMap<Integer, Integer> map;
    private Integer capacity;
    private LinkedList<Integer> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        list.remove((Integer) key);
        list.addFirst(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            list.remove((Integer) key);
        }
        map.put(key, value);
        if(map.size() > capacity) {
            int last = list.removeLast();
            map.remove(last);
        }
        list.addFirst(key);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }

}
