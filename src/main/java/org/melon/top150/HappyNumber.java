package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/happy-number/">202. 快乐数</a>
 */
public class HappyNumber {

    public static void main(String[] args) {
        int n = 91;
        System.out.println(new HappyNumber().isHappy(n));
    }

    /**
     * 如果不是快乐数，会死循环，如果是快乐数，在计算 1 时同样会死循环
     * <p>
     * 问题简化成链表成环节点是否等于1，如果成环节点==1 说明是快乐数
     *
     * @param n
     * @return
     */

    public boolean isHappy(int n) {
        int slow = next(n);
        int fast = next(next(n));
        while (slow != fast) {
            slow = next(slow);
            System.out.println("slow:" + slow);
            fast = next(next(fast));
        }
        System.out.println(slow);
        System.out.println(fast);
        return slow == 1;

    }

    // 计算快乐数
    public int next(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
//        System.out.println(sum);
        return sum;
    }


}
