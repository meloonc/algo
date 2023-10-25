package org.melon.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href=https://leetcode.cn/problems/ipo/>leetcode 502 IPO</a>
 */
public class IPO {

    // 解决思路
    // 根据项目花费构建小根堆
    // 从小根堆中取出花费小于等于当前资本的项目，并存放到大根堆中，大根堆按照项目例如排序
    // 从大根堆中取出利润最大的项目，将利润加到资本上
    // 上述过程重复 k 次

    public static void main(String[] args) {
//        int k = 2;
//        int w = 0;
//        int[] profits = {1, 2, 3};
//        int[] capital = {0, 1, 1};
//        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }

    /**
     * @param k       购买次数
     * @param w       初始资金
     * @param profits 项目利润
     * @param capital 项目花费
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 小根堆，项目花费最少的在最上面
        PriorityQueue<int[]> minCostHeap = new PriorityQueue<>(Comparator.comparingInt(i -> i[0]));
        // 大根堆，项目利润最多的在最上面
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < capital.length; i++) {
            int[] ints = new int[]{capital[i], profits[i]};
            minCostHeap.add(ints);
        }

        for (int i = 0; i < k; i++) {
            // 贪心算法，优先购买利润最多，花费最少的项目
            while (!minCostHeap.isEmpty() && minCostHeap.peek()[0] <= w) {
                maxProfitHeap.add(minCostHeap.poll()[1]);
            }
            // 如果 maxProfitHeap 为空，说明现在的资金不能购买任何项目，直接跳出循环
            if(maxProfitHeap.isEmpty()) {
                break;
            }
            w += maxProfitHeap.poll();
        }
        return w;
    }
}
