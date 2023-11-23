package org.melon.dp;

import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    // todo

//    public static void main(String[] args) {
//        String s = "cbca";
//        String[] arr = {"bc", "ca"};
//        List<String> wordDict = Arrays.asList(arr);
//        WordBreak wordBreak = new WordBreak();
//        boolean b = wordBreak.wordBreak(s, wordDict);
//        System.out.println(b);
//    }
//
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> wordDictSet = new HashSet(wordDict);
//
//        return process(s, wordDictSet, s.length() - 1);
//    }
//
////    public boolean process(String s, List<String> wordDict, Integer index) {
////        if (s.isEmpty()) {
////            return true;
////        }
////        if (index > wordDict.size() - 1 && !s.isEmpty()) {
////            return false;
////        }
////
////        // 使用当前单词
////        String temp = s;
////        String word = wordDict.get(index);
////        temp = temp.replaceAll(word, "");
////        index++;
////        boolean p1 = process(temp, wordDict, index);
////        // 不使用当前单词
////        boolean p2 = process(s, wordDict, index);
////        return p1 || p2;
////    }
//
//
//    public boolean process(String s, HashSet<String> wordDictSet, Integer index) {
//        if (index == 0) {
//            return true;
//        }
//        String substring = s.substring(0, index);
//        return wordDictSet.contains(substring) || process(s, wordDictSet, index --);
//
//    }
}
