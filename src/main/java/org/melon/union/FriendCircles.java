package org.melon.union;

/**
 * 朋友圈数量
 */
public class FriendCircles {

    public int friendCircles(int[][] circles) {
        UnionFind unionFind = new UnionFind(circles.length);

        // 只需要遍历上半部分，因为下半部分是对称的
        for (int i = 0; i < circles.length; i++) {
            for (int j = i + 1; j < circles.length; j++) {
                // 同一朋友圈
                if (circles[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count;
    }

    static class UnionFind {
        // 父节点关系 i 的 父节点是 parent[i]
        private int[] parent;

        // 朋友圈大小， i 的朋友圈大小，只有当 i 是朋友圈代表节点时，circleSize[i] 才有意义
        private int[] circleSize;

        // 朋友圈数量
        private int count;

        // 辅助数组，用于路径优化
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            circleSize = new int[n];
            count = 0;
            help = new int[n];
            for (int i = 0; i < n; i++) {
                // 初始化，每个节点的父节点都是自己
                parent[i] = i;
                // 初始化，每个节点的朋友圈大小都是 1
                circleSize[i] = 1;
                count++;
            }
        }

        // 找到 i 的代表节点，需要进行路径优化
        public int find(int i) {
            int n = 0;
            // 找到代表节点
            while (i != parent[i]) {
                help[n++] = i;
                i = parent[i];
            }
            // 路径优化
            for (n--; n >= 0; n--) {
                parent[help[n]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int i1 = find(i);
            int i2 = find(j);
            // 如果朋友圈不一样
            if (i1 != i2) {
                // 数量少的集合合并到数量多的集合
                if (circleSize[i1] > circleSize[i2]) {
                    parent[i2] = i1;
                    circleSize[i1] += circleSize[i2];
                } else {
                    parent[i1] = i2;
                    circleSize[i2] += circleSize[i1];
                }
                count--;
            }
        }
    }
}
