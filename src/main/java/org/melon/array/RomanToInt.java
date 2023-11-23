package org.melon.array;

import java.util.HashMap;

/**
 * <a href="https://leetcode-cn.com/problems/roman-to-integer/">13. 罗马数字转整数</a>
 */
public class RomanToInt {

    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("M", 1000);
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 < chars.length && map.containsKey(chars[i] + "" + chars[i + 1])) {
                res += map.get(chars[i] + "" + chars[i + 1]);
                i++;
            } else {
                res += map.get(chars[i] + "");
            }
        }
        return res;
    }
}
