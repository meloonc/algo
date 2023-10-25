package org.melon.greedy;

import java.util.PriorityQueue;

/**
 * 分割金条的最小代价
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
 * 一群人想整分整块金条，怎么分最省铜板？
 * 例如，给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60.
 * 金条要分成10,20,30三个部分。
 * 如果，先把长度60的金条分成10和50，花费60，再把长度50的金条分成20和30，花费50，一共花费110铜板。
 * 但是如果，先把长度60的金条分成30和30，花费60，再把长度30的金条分成10和20，花费30，一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class SpitGolden {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(getMinCost(arr));
        System.out.println(getMinCostWithHeap(arr));
    }

    public static int getMinCost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int[] next = copyAndMergeTwo(arr, i);
            min = Math.min(min, process(next, pre + arr[i]));
        }
        return min;
    }

    public static int[] copyAndMergeTwo(int[] arr, int index) {
        int[] next = new int[arr.length - 1];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                next[count++] = arr[i];
            }
        }
        next[count] = arr[index] + arr[index + 1];
        return next;
    }


    /**
     * 哈夫曼队列
     *
     * @param arr
     * @return
     */
    public static int getMinCostWithHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Integer integer : arr) {
            heap.add(integer);
        }
        int cost = 0;
        int sum = 0;
        while (heap.size() > 1) {
            Integer i1 = heap.poll();
            Integer i2 = heap.poll();
            sum = i1 + i2;
            cost += sum;
            heap.add(sum);

        }
        return sum;
    }


}
