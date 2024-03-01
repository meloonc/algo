package org.melon.top150;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < common.length()) {
                common = common.substring(0, strs[i].length());
            }
            for (int j = 0; j < strs[i].length() && j < common.length(); j++) {
                if (strs[i].charAt(j) != common.charAt(j)) {
                    common = common.substring(0, j);
                    break;
                }
            }
        }
        return common;

    }
}
