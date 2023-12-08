package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">
 * 122. 买卖股票的最佳时机</a>
 */
public class MaxProfit2 {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if(profit > 0) {
                maxProfit += profit;
            }
        }
        return maxProfit;
    }



}
