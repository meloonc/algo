package org.melon.top150;

/**
 * <a href = https://leetcode.cn/problems/zigzag-conversion/>6. N 字形变换</a>
 */
public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        char[] chars = s.toCharArray();
        int numRow = 0;
        int flag = -1;
        for (char aChar : chars) {
            // numRow = 0 时， numRow 递增
            // numRow = numRows - 1 时， numRow 递减
            // 0 -> 1 -> 2 -> 1 -> 0
            if (numRow == 0 || numRow == numRows - 1) {
                flag = (-flag);
            }
            if (rows[numRow] == null) {
                rows[numRow] = new StringBuilder();
            }
            rows[numRow].append(aChar);
            numRow += flag;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            if (row != null) {
                result.append(row);
            }
        }
        return result.toString();
    }
}
