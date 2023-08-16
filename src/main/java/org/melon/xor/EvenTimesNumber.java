package org.melon.xor;

import java.util.Arrays;

/**
 * 在一组数据当中，有两个数字出现奇数次，请查找出来
 */
public class EvenTimesNumber {

    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,4,4,3};
        int[] numbersWithOddOccurrences = findNumbersWithOddOccurrences(arr);
        System.out.println(Arrays.toString(numbersWithOddOccurrences));
    }
    // 解决这个问题的一种常见方法是使用异或操作。我们可以遍历整个数组，将所有数字进行异或操作，最终得到的结果将是这两个出现奇数次的数字的异或结果。
    //
    //由于两个相同的数字进行异或操作的结果为0，而任何数字与0进行异或操作的结果仍然是该数字本身，所以最终得到的异或结果将是这两个出现奇数次的数字的异或结果。
    //
    //接下来，我们需要找到这个异或结果中的某个非零位（即1的位），以便区分出这两个数字。我们可以选择任意一个非零位，例如最右边的非零位。
    //
    //然后，我们再次遍历整个数组，将所有数字按照该非零位是否为1分为两组。这样，这两个出现奇数次的数字就被分到了不同的组中。
    //
    //最后，我们分别对这两个组中的数字进行异或操作，得到的结果就是这两个出现奇数次的数字。

    /**
     * 使用异或运算不创建额外变量即可交换数据
     * 1. 0 ^ a = a
     * 2. a ^ a = 0
     * 3. 异或运算与数据位置无关，同一组数据异或的结果一直相等
     * 4. 两个数字位于不同内存区域，否则异或运算后结果 = 0
     */
    // a & ((~a ) + 1) 数字和数据取反+1 进行与计算，可以查找到最右侧的二进制 1
    public static int[] findNumbersWithOddOccurrences(int[] arr) {
        int xorResult = 0;
        for (int num : arr) {
            xorResult ^= num;
        }

        // 找到最右边的非零位
        // 11111111111111111111111111111101
        // 00000000000000000000000000000011
        // 00000000000000000000000000000001
        int rightmostSetBit = xorResult & -xorResult;

        int[] result = new int[2];
        for (int num : arr) {
            // 为什么这里 & 计算可以区分出两种数？
            // 通过 rightmostSetBit 必然可以区分出奇数次数的两个数
            if ((num & rightmostSetBit) != 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;
    }
}
