package org.melon.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/palindrome-partitioning/">131. 分割回文串</a>
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        List<List<String>> result = new PalindromePartitioning().partition("aab");
        System.out.println(result);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(s, 0, path, result);
        return result;
    }


    public void backtracking(String s, int startIndex, List<String> path, List<List<String>> result) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if(isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
                backtracking(s, i + 1, path, result);
                path.remove(path.size() - 1);
            }

        }
    }

    /**
     * (i,j] 范围上是不是回文子串
     * @param s
     * @param i
     * @param j
     * @return
     */
    public boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }


}
