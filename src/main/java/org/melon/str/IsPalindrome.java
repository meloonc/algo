package org.melon.str;

import java.util.ArrayList;

public class IsPalindrome {

    public static void main(String[] args) {
        String s = "0P";
        IsPalindrome isPalindrome = new IsPalindrome();
        isPalindrome.isPalindrome(s);
    }


    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        ArrayList<Character> characters = new ArrayList<>();
        for (char aChar : chars) {
            if (isBigLetter(aChar) || isSmallLetter(aChar) || isNumber(aChar)) {
                if(isBigLetter(aChar)) {
                    aChar = (char)( aChar + 32);
                }
                characters.add(aChar);
            }
        }

        int l = 0;
        int r = characters.size() - 1;

        int m = r / 2;
        while (l <= m && m < r) {
            if (characters.get(l) != characters.get(r)) {
                return false;
            }
            l++;
            r--;

        }

        return true;

    }

    /**
     * ascill 码， 数字为 48-57
     * 大写字母为 65-90
     * 小写字母为 97-122
     *
     * @return
     */
    public boolean isBigLetter(char c) {
        return (c >= 65 && c <= 90);
    }

    public boolean isSmallLetter(char c) {
        return (c >= 97 && c <= 122);

    }

    public boolean isNumber(char c) {
        return c >= 48 && c <= 57;
    }

}
