package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/reverse-words-in-a-string/">151. 反转字符串中的单词</a>
 */
public class ReverseWords {

    public static void main(String[] args) {
        String str = "  hello world  ";
        ReverseWords reverseWords = new ReverseWords();
        String s = reverseWords.reverseWords(str);
        System.out.println(s);
    }

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].equals(" ") || strings[i].equals("")) {
                continue;
            }
            sb.append(strings[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
