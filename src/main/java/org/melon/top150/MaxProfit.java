package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">121. 买卖股票的最佳时机</a>
 */
public class MaxProfit {

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int[] prices = {7, 6, 4, 3, 1};
        int profit = maxProfit.maxProfit(prices);
        System.out.println(profit);
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;

    }


}
