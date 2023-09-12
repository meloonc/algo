package org.melon.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 最大线段重合问题
 * <p>
 * 给定很多线段，每条线段都有两个数组 [start, end]，表示线段的开始位置和结束位置，左右都是闭区间。规定：
 * 1.线段开始和结束位置一定都是整数值；
 * 2.线段重合区域的长度必须 >=1 （比如(1,3) 和 (3,5) 不算重合，因为只有3这个点重合）\
 * <p>
 * 返回线段最多重合区域中，包含了几条线段。
 */
public class MaxLineOverride {

    public static void main(String[] args) {
        int[][] lines = {{1, 3}, {2, 6}, {8, 10}, {15, 18}, {2, 5}, {10, 11}, {1, 9}};
        System.out.println(maxLine(lines));
    }

    public static int maxLine(int[][] lines) {
        // 先将线段按开始位置顺序排序
        List<Line> lineList = Arrays.stream(lines).map(l -> new Line(l[0], l[1])).sorted(Comparator.comparingInt(l -> l.start)).collect(Collectors.toList());
        // 然后将线段结束位置存放进小根堆，如果堆中的数字小于线段的开始位置，则从堆中删除
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lineList) {
            heap.offer(line.end);
            while (heap.size() > 0 && heap.peek() <= line.start) {
                heap.poll();
            }
            max = Math.max(max, heap.size());
        }
        return max;
    }

    static class Line {
        Integer start;
        Integer end;

        public Line(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }
}
