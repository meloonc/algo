package org.melon.dp;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">
 * 121. 买卖股票的最佳时机</a>
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }


    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int price : prices) {
            if(price > minPrice) {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }else {
                minPrice = price;
            }
        }
        return maxProfit;
    }


}
