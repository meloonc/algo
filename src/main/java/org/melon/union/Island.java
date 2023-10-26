package org.melon.union;

/**
 * <a href= https://leetcode.cn/problems/number-of-islands/> leetcode 200</a>
 */
public class Island {


    // 递归
    public int numIslands1(char[][] grid) {
        int island = 0;
        // 遍历所有岛屿
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 如果是岛就去感染周围的岛
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    island++;
                }
            }
        }
        return island;
    }

    private void infect(char[][] grid, int i, int j) {
        // 如果越界或者不是岛屿，返回0
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        // 来过的岛全部改成 2，为了退出递归
        grid[i][j] = 2;
        // 递归感染上下左右
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }


    //并查集
    public int numIslands2(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(unionFind.index(i, 0), unionFind.index(i - 1, 0));
            }
        }

        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionFind.union(unionFind.index(0, i), unionFind.index(0, i - 1));
            }
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                    unionFind.union(unionFind.index(i, j), unionFind.index(i, j - 1));
                }
                if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                    unionFind.union(unionFind.index(i, j), unionFind.index(i - 1, j));
                }
            }
        }
        return unionFind.sets;
    }

    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int sets;
        private int col;
        private int[] help;

        public UnionFind(char[][] grid) {
            col = grid[0].length;
            int rowLength = grid.length;
            parent = new int[col * rowLength];
            size = new int[col * rowLength];
            help = new int[col * rowLength];
            sets = 0;

            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        parent[index(i, j)] = index(i, j);
                        size[index(i, j)] = 1;
                        sets++;
                    }
                }
            }
        }

        // 将二维数组的 index 转为一维数组的 index
        // 行数 * 列长度 + 列数 = 一维数组的 index
        public int index(int i, int j) {
            return i * col + j;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);
            if (parentJ != parentI) {
                if (size[parentI] > size[parentJ]) {
                    parent[parentJ] = parentI;
                    size[parentI] += size[parentJ];
                } else {
                    parent[parentI] = parentJ;
                    size[parentJ] += size[parentI];
                }
                sets--;
            }
        }
    }
}
