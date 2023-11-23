package org.melon.array;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array"></a>
 */
public class RemoveDuplicatesInSortedArray {

    public static void main(String[] args) {
        int[] arr = {0,0,1,1,1,2,2,3,3,4};

        RemoveDuplicatesInSortedArray solution = new RemoveDuplicatesInSortedArray();
        int i = solution.removeDuplicates(arr);
        System.out.println(i);
    }


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int current = nums[0];
        int currentIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > current) {
                current = nums[i];
                swap(nums, i, currentIndex);
                currentIndex ++;
            }
        }
        return currentIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
