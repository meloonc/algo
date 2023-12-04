package org.melon.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/n-queens/">N-Queens</a>
 */
public class NQueen {

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        int i = nQueen.totalNQueens(4);
        System.out.println(i);
    }


    public int totalNQueens(int n) {
        return process(n, 0, new int[n]);
    }

    public int process(int n, int row, int[] record) {
        // 如果能填完所有的行
        if (row == n) {
            return 1;
        }
        int res = 0;
        // 填列
        for (int i = 0; i < n; i++) {
            // record 的 index 代表棋子的行，value 代表列
            if (isValid(record, row, i)) {
                record[row] = i;
                res += process(n, row + 1, record);
            }
        }

        return res;


    }

    private boolean isValid(int[] record, int row, int col) {
        // 和之前的棋子进行比较，看看列是否相等是否在一条斜线
        for (int i = 0; i < row; i++) {
            // 棋子的列不能相等
            // 并且不在一条斜线上
            if (record[i] == col || Math.abs(row - i) == Math.abs(record[i] - col)) {
                return false;
            }
        }
        return true;
    }


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] record = new int[n];
        process2(n, 0, record, result);
        return result;
    }


    public int process2(int n, int row, int[] record, List<List<String>> result) {
        // 如果能填完所有的行
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (record[i] == j) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }

            result.add(list);
            return 1;
        }
        int res = 0;
        // 填列
        for (int i = 0; i < n; i++) {
            // record 的 index 代表棋子的行，value 代表列
            record[row] = i;
            if (isValid(record, row, i)) {
                res += process2(n, row + 1, record, result);
            }
        }
        return res;
    }
}
