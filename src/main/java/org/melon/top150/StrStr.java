package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/implement-strstr/">28. 找出字符串中第一个匹配项的下标</a>
 */
public class StrStr {

    public static void main(String[] args) {

        String haystack = "sadbutsad";
        String needle = "sad";
        StrStr strStr = new StrStr();
        int res = strStr.strStr(haystack, needle);
        System.out.println(res);

    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return 0;
        }
        int needleLength = needle.length();
        int begin = 0;
        int end = needleLength - 1;

        int haystackLength = haystack.length();
        while (end < haystackLength) {
            if (haystack.substring(begin, end + 1).equals(needle)) {
                return begin;
            }
            begin++;
            end++;
        }
        return -1;
    }

}
