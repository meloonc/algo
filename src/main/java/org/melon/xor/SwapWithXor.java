package org.melon.xor;

/**
 * 使用异或交换两个数据的位置
 * 使用异或运算不创建额外变量即可交换数据
 * 1. 0 ^ a = a
 * 2. a ^ a = 0
 * 3. 异或运算与数据位置无关，同一组数据异或的结果一直相等
 * 4. 两个数字位于不同内存区域，否则异或运算后结果 = 0
 */
public class SwapWithXor {

    public static void main(String[] args) {
//        int a = 3;
//        int b = 4;
//        System.out.println(a + "," + b);
//
//        a = a ^ b;
//        b = a ^ b; // a ^ b ^ b -> 0 ^ a = a
//        a = a ^ b; // a ^ b ^ a -> 0 ^ b = b
//        System.out.println(a + "," + b);

        int a = 7;
        System.out.println(~a);
    }


}
