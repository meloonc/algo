package org.melon.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子序列
 * 获取字符串中所有的子序列
 */
public class SubString {

    //
    public static void main(String[] args) {
        String str = "abcccc";
        ArrayList<String> ans = new ArrayList<>();
        allSubString(str.toCharArray(), 0, "", ans);
        ans.forEach(System.out::println);
        System.out.println("===================");
        ArrayList<Character> characters = new ArrayList<>();
        for (char c : str.toCharArray()) {
            characters.add(c);
        }
        ans = new ArrayList<>();

        allPermutation(characters, "", ans);
        ans.forEach(System.out::println);
        System.out.println("===================");

        ans = new ArrayList<>();
        allPermutation2(str.toCharArray(), 0 , ans);
        ans.forEach(System.out::println);

    }


    /**
     * 这段代码是一个递归函数，用于处理字符数组并生成所有可能的字符串组合。函数的输入参数包括字符数组chars、当前处理的字符索引index、当前已生成的字符串path，以及一个存储结果的列表ans。
     * <p>
     * 代码首先判断当前处理的字符索引是否已经达到字符数组的长度，如果是，则将当前已生成的字符串添加到结果列表中，并返回。
     * <p>
     * 如果当前处理的字符索引还没有达到字符数组的长度，代码会调用两次自身的递归函数。第一次递归时，字符索引加1，字符串不变，即继续处理下一个字符。第二次递归时，字符索引加1，字符串为当前已生成的字符串加上当前字符。这样就可以生成所有可能的字符串组合。
     * <p>
     * 代码的执行过程可以通过以下步骤解释：
     * 1. 如果当前处理的字符索引等于字符数组的长度，说明已经处理完所有字符，将当前已生成的字符串添加到结果列表中，然后返回。
     * 2. 调用递归函数，将字符索引加1，字符串不变，继续处理下一个字符。
     * 3. 调用递归函数，将字符索引加1，字符串为当前已生成的字符串加上当前字符，继续处理下一个字符。
     * <p>
     * 递归函数的执行过程会不断地生成新的字符串组合，直到处理完所有字符。最终，结果列表中会包含所有可能的字符串组合。
     */
    public static void allSubString(char[] chars, int index, String path, List<String> ans) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        allSubString(chars, index + 1, path, ans);
        allSubString(chars, index + 1, path + chars[index], ans);
    }


    // 字符串全部排列
    public static void allPermutation(List<Character> chars, String path, List<String> ans) {
        if (chars.isEmpty()) {
            ans.add(path);
            return;
        }

        for (int i = 0; i < chars.size(); i++) {
            List<Character> newChars = new ArrayList<>(chars);
            newChars.remove(i);
            allPermutation(newChars, path + chars.get(i), ans);
            // 恢复现场
            newChars.add(i, chars.get(i));
        }
    }

    public static void allPermutation2(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        boolean[] booleans = new boolean[256];

        for (int i = index; i < chars.length; i++) {
            // 如果当前字符曾经交换过位置，就不用交换了，进行去重
            if(booleans[chars[i]]) {
                continue;
            }
            booleans[chars[i]] = true;
            swap(chars, i, index);
            allPermutation2(chars, index + 1, ans);
            // 恢复现场
            swap(chars, i, index);
        }

    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }




}
