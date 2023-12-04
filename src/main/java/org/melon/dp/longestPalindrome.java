package org.melon.dp;

/**
 * <a href="https://leetcode-cn.com/problems/longest-palindromic-substring/">5. 最长回文子串</a>
 */
public class longestPalindrome {

    public String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return "";
        }
//        char[] chars = s.toCharArray();
        // 0 - n 位置上的回文子串
        return process(s, 0, s.length() - 1);
    }

    public String process(String s, int l, int r) {
        if (l > r) {
            return "";
        } else if (l == r) {
            return s.substring(l, r);
        } else if (l + 1 == r && s.charAt(l) == s.charAt(r)) {
            return s.substring(l, r);
        }

        String p1 = process(s, l + 1, r);
        String p2 = process(s, l, r - 1);
        String p3 = process(s, l + 1, r - 1);
        if(s.charAt(l) == s.charAt(r)) {
            p3 = s.charAt(l) + p3 + s.charAt(r);
        }
        return "";
    }
}
