package org.melon.str;

public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (s.charAt(end) == ' ') {
            end--;
        }
        int length = 0;
        while (end >= 0 && s.charAt(end) != ' ') {
            end--;
            length++;
        }
        return length;
    }
}
