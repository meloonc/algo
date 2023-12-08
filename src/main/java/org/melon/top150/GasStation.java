package org.melon.top150;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/gas-station/">134. 加油站</a>
 */
public class GasStation {

    public static void main(String[] args) {

        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        new GasStation().canCompleteCircuit3(gas, cost);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;
        int m = 2 * n;


        int[] arr = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = arr[i];
        }

        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }

        // 窗口存放的是下标
        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[i]) {
                window.pollLast();
            }
            window.addLast(i);
        }

        boolean[] res = new boolean[n];
        for (int offset = 0, i = 0, j = n; j < m; offset = arr[i++], j++) {
            if (arr[window.peekFirst()] - offset >= 0) {
                res[i] = true;
            }
            if (window.peekFirst() == i) {
                window.pollFirst();
            }
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[j]) {
                window.pollLast();
            }
            window.addLast(j);
        }
        for (int i = 0; i < gas.length; i++) {
            if (res[i]) {
                return i;
            }
        }

        return -1;
    }


    /**
     * <a href="https://leetcode.cn/problems/gas-station/solutions/488622/134-jia-you-zhan-tan-xin-jing-dian-ti-mu-xiang-jie/">
     * @param gas
     * @param cost
     * @return
     */

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {   // 当前累加rest[i]和 curSum一旦小于0
                start = i + 1;  // 起始位置更新为i+1
                curSum = 0;     // curSum从0开始
            }
        }
        if (totalSum < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }
}
