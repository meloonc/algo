package org.melon.greedy;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮，‘.’表示居民点，可以放灯，需要点亮。
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮。
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯。
 */
public class Light {
    public static void main(String[] args) {
        String str = "X..XX.......X.";
        System.out.println(light(str));
    }

    public static int light(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int light = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                light++;
                // 如果当前位置是最后一个位置，那么直接退出循环
                // 以下逻辑为贪心策略，如果当前位置的下一个位置是X，那么当前位置的下一个位置一定不会放灯
                if(index + 1 == chars.length) {
                    break;
                }
                if (chars[index + 1] == 'X') {
                    index = index + 2;
                } else {
                    index = index + 3;
                }
            }
        }
        return light;
    }
}
