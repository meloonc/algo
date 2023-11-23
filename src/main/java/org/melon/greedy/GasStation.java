package org.melon.greedy;

/**
 * <a href="https://leetcode-cn.com/problems/gas-station/">134. 加油站</a>
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        int left = 0;
        while (i < gas.length) {
            left = gas[i]-cost[i];
            if(left < 0 ) {
                left = 0;
                i++;
            }else {
                int j = i + 1;
                while (j != i) {
                    if (j == gas.length) {
                        j = 0;
                    }
                    left += gas[j] - cost[j];
                    if (left < 0) {
                        break;
                    }
                    j++;
                }
                if (j == i) {
                    return i;
                }
                i = j + 1;
            }



        }

        return i;
    }
}
