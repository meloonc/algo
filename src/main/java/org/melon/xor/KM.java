package org.melon.xor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class KM {

    /**
     * 在一组数字中，有几种数出现了 m 次，有一种数出现了 k 次，k < m ，找到出现了 k 次的数字
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 4, 4, 4, 2, 2, 2, -1, -1};
        int search = search(arr, 2, 3);
        System.out.println(search);

        int times = 100000;
        int range = 9;

        for (int i = 0; i < times; i++) {
            int a = randomNum(range);
            int b = randomNum(range);
            int k = Math.max(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m = k + m;
            }
            int[] ints = randomArray(k, m, 4);
            int search1 = search(ints, k, m);
            int search2 = searchWithHash(ints, k, m);
            if (search1 != search2) {
                System.out.println("位运算搜索" + search1);
                System.out.println("hash 搜索" + search2);
                System.out.println("出问题啦");
            }
        }

    }

    public static int search(int[] arr, int k, int m) {
        // 创建一个长度位 32 的数组， 将数字所有位 = 1 所在 index +1
        // 即 32 的数组中的如果 ！= 0 ，就是 k 的整数倍或者 m 的整数倍，或者是 n*m +k
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                // (num>>i) & 1 如果结果 ==1 说明该位上的数字是1
                // 假设 num = 7 , 二进制为 0 1 1 1
                // 右移 0 位 & 1  0111 & 0001 = 1
                // 右移 1 位 & 1  0011 & 0001 = 1
                // 右移 2 位 & 1  0001 & 0001 = 1
                // 右移 3 位 & 1  0000 & 0001 = 0
                // 所以 =1 的位数就放进数组中了
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            // 由于 k < m , 所以 t 数组中的元素要么被 m 整除，要么不被 m 整除，如果不能被 m 整除，说明 k 对应的数字在该位上 = 1
            if (t[i] % m != 0) {
                // 通过或运算求出 k 对应的数字
                // 假设 k 对应数字在 1， 3 ，5 位上 = 1
                // 0 ｜ 1<<1 =  0 | 0010 = 0010
                // 0010 | 1<<3 = 1010
                // 1010 | 1 << 5 = 0010 1010
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int searchWithHash(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == k) {
                return integer;
            }
        }
        return -1;

    }


    public static int randomNum(int range) {
        // 2 - range 的随机数
        return (int) (Math.random() * range) + 2;
    }

    public static int[] randomArray(int k, int m, int iRange) {
        int i = randomNum(iRange);
        int randomK = randomNum(200);
        int[] arr = new int[k + i * m];
        for (int j = 0; j < k; j++) {
            arr[j] = randomK;
        }
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < i) {
            int randomM = randomNum(200);
            if (randomM != randomK) {
                set.add(randomM);
            }
        }
        for (int j = 0; j < set.size(); j++) {
            for (int l = k + (j * m); l < k + i * m; l++) {
                arr[l] = (Integer) set.toArray()[j];
            }
        }

        for (int j = 0; j < arr.length; j++) {
            int l = (int) (Math.random() * arr.length);
            int temp = arr[j];
            arr[j] = arr[l];
            arr[l] = temp;
        }
        return arr;


    }


}
