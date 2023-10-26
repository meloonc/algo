package org.melon.union;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 岛屿问题，动态添加岛屿 leetcode 305
 */
public class Island2 {

    @Test
    void testNumIsland() {
        Island2 numIsland = new Island2();

        // Testing when there are no positions
        int[][] positions1 = {{0,0},{0,1},{1,2},{2,1}};
        int[] expected1 = {1, 1, 2, 3};
        assertArrayEquals(expected1, numIsland.numIsland(3, 3, positions1));
        assertArrayEquals(expected1, numIsland.numIsland2(3, 3, positions1));

        int[][] positions2 = {{0,0},{0,1},{1,2},{2,1},{1,1},{2,2},{3,3}};
        int[] expected2 = {1, 1, 2, 3, 1, 1, 2};
        assertArrayEquals(expected2, numIsland.numIsland(4, 4, positions2));
        assertArrayEquals(expected2, numIsland.numIsland2(4, 4, positions2));
//
//        // Testing when there is one position
//        int[][] positions2 = {{0, 0}};
//        int[] expected2 = {1};
//        assertArrayEquals(expected2, numIsland.numIsland(1, 1, positions2));
//        assertArrayEquals(expected2, numIsland.numIsland2(1, 1, positions2));
//
//        // Testing when there are multiple positions
//        int[][] positions3 = {{0, 0}, {1, 1}, {2, 2}};
//        int[] expected3 = {1, 2, 3};
//        assertArrayEquals(expected3, numIsland.numIsland(3, 3, positions3));
//        assertArrayEquals(expected3, numIsland.numIsland2(3, 3, positions3));
    }


    public int[] numIsland(int m, int n, int[][] positions) {
        int[] res = new int[positions.length];
        UnionFind1 unionFind1 = new UnionFind1(m, n);
        for (int i = 0; i < positions.length; i++) {
            res[i] = unionFind1.add(positions[i][0], positions[i][1]);
        }
        return res;
    }

    /**
     * 该方法有缺陷，如果 m n 特别大，但是 positions 并没有很多，占用空间很大，因为需要提前初始 parent size help
     * 最好的方法是取掉 parent size help 的初始化， 使用 map 来替代。 将 position 的坐标转换成字符串去 map 进行存储
     */
    static class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int sets;
        private int colLength;
        private int rowLength;
        private int[] help;

        // 由于是动态添加，目前只需要初始长度就好，不需要在数组中添加元素
        public UnionFind1(int r, int c) {
            int total = r * c;
            this.parent = new int[total];
            this.size = new int[total];
            this.help = new int[total];
            this.sets = 0;
            this.colLength = c;
            this.rowLength = r;
        }


        private int index(int c, int r) {
            return c * colLength + r;
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

        // 添加岛屿
        public int add(int c, int r) {
            // 如果越界了就不添加了
            if (c < 0 || c >= colLength || r < 0 || r >= rowLength) {
                return sets;
            }
            int i = index(c, r);
            // 如果当前岛屿的数量为 0 说明是第一次被添加
            if (size[i] == 0) {
                sets++;
                parent[i] = i;
                size[i] = 1;
                // 添加完岛屿后与周围进行合并
                if (c > 0 && size[index(c - 1, r)] > 0) {
                    union(i, index(c - 1, r));
                }
                if (c + 1 < colLength && size[index(c + 1, r)] > 0) {
                    union(i, index(c + 1, r));
                }
                if (r > 0 && size[index(c, r - 1)] > 0) {
                    union(i, index(c, r - 1));
                }
                if (r + 1 < rowLength && size[index(c, r + 1)] > 0) {
                    union(i, index(c, r + 1));
                }

            }
            return sets;
        }

    }


    static class UnionFind2 {
        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private Integer sets;
        private int rowLength;
        private int colLength;

        public UnionFind2(int m, int n) {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
            this.sets = 0;
            this.rowLength = m;
            this.colLength = n;
        }


        public String find(String i) {
            List<String> list = new ArrayList<>();
            while (!parent.get(i).equals(i)) {
                i = parent.get(i);
                list.add(i);
            }
            for (String s : list) {
                parent.put(s, i);
            }
            return i;
        }

        public int union(String i, String j) {
            String parentI = find(i);
            String parentJ = find(j);
            if (!Objects.equals(parentI, parentJ)) {
                Integer sizeI = size.get(parentI);
                Integer sizeJ = size.get(parentJ);
                if (sizeI > sizeJ) {
                    parent.put(parentJ, parentI);
                    size.put(parentI, sizeI + sizeJ);
                } else {
                    parent.put(parentI, parentJ);
                    size.put(parentJ, sizeI + sizeJ);
                }
                sets--;
            }
            return sets;
        }

        public int add(int c, int r) {
            if (c > colLength || r > rowLength) {
                return sets;
            }
            String key = c + "_" + r;
            if (size.getOrDefault(key, 0) == 0) {
                sets++;
                size.put(key, 1);
                parent.put(key, key);
                String leftKey = (c - 1) + "_" + r;

                String rightKey = (c + 1) + "_" + r;
                String topKey = c + "_" + (r - 1);
                String downKey = c + "_" + (r + 1);
                if (c > 0 && size.getOrDefault(leftKey, 0) > 0) {
                    union(key, leftKey);
                }
                if (c + 1 < colLength && size.getOrDefault(rightKey, 0) > 0) {
                    union(key, rightKey);
                }
                if (r > 0 && size.getOrDefault(topKey, 0) > 0) {
                    union(key, topKey);
                }
                if (r + 1 < rowLength && size.getOrDefault(downKey, 0) > 0) {
                    union(key, downKey);
                }
            }
            return sets;
        }
    }

    public int[] numIsland2(int m, int n, int[][] positions) {
        int[] res = new int[positions.length];
        UnionFind2 unionFind2 = new UnionFind2(m , n);
        for (int i = 0; i < positions.length; i++) {
            res[i] = unionFind2.add(positions[i][0], positions[i][1]);
        }
        return res;
    }


}
