package org.melon.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">17. 电话号码的字母组合</a>
 */
public class LetterCombinations {

    public static void main(String[] args) {
        List<String> result = new LetterCombinations().letterCombinations("239");
        System.out.println(result);
    }

    private String[] arr = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        char[] chars = digits.toCharArray();
        StringBuilder sbd = new StringBuilder();
        backtracking(chars, 0, sbd, result);
        return result;

    }

    public void backtracking(char[] chars, int index, StringBuilder sbd, List<String> result) {
        if (index == chars.length && sbd.length() == chars.length) {
            String string = sbd.toString();
            result.add(string);
            return;
        } else {
            String letter = arr[chars[index] - '0' - 2];
            for (int i = 0; i < letter.length(); i++) {
                sbd.append(letter.charAt(i));
                backtracking(chars, index + 1, sbd, result);
                sbd.deleteCharAt(sbd.length() - 1);
            }
        }
    }
}
