package org.melon.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/restore-ip-addresses/">93. 复原 IP 地址</a>
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {
        List<String> result = new RestoreIpAddresses().restoreIpAddresses("25525511135");
        System.out.println(result);
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(s, 0, path, result);
        return result;

    }

    public void backtracking(String s, int startIndex, List<String> path, List<String> result) {
        if (startIndex == s.length()) {
            if (path.size() == 4) {
                result.add(String.join(".", path));
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (checkIP(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
                backtracking(s, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean checkIP(String s, int i, int j) {
        if (j - i > 3) {
            return false;
        }
        String substring = s.substring(i, j + 1);
        if(substring.startsWith("0") && substring.length() > 1) {
            return false;
        }
        return Integer.parseInt(substring) <= 255;
    }
}
