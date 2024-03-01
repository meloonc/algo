package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/is-subsequence/">392. 判断子序列</a>
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;

        }
        return i == s.length();

    }


}
