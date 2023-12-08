package org.melon.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomizedSet {

    private HashMap<Integer, Integer> sizeMap;

    private List<Integer> list;

    private Random random;

    public RandomizedSet() {
        sizeMap = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (sizeMap.containsKey(val)) {
            return false;
        }
        list.add(val);
        sizeMap.put(val, list.size() - 1);
        return true;

    }

    public boolean remove(int val) {
        if (sizeMap.containsKey(val)) {
            Integer index = sizeMap.get(val);
            Integer lastNum = list.get(list.size() - 1);
            // 与最后的数字交换位置
            list.set(index, lastNum);
            // 修改索引
            sizeMap.put(lastNum, index);
            // 移除最后的数字
            list.remove(list.size()-1);
            sizeMap.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int size = list.size();
        int index = random.nextInt(size);
        return list.get(index);
    }
}
