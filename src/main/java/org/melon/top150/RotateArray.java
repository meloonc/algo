package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/rotate-array/">189. Rotate Array</a>
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(arr, 3);
    }

    public void rotate(int[] nums, int k) {
        // 如果 k % n == 0, 数组不用移动位置
        int n = nums.length;
        int moveStep = k % n;
//        while (moveStep > 0) {
//            moveEnd2Begin(nums);
//            moveStep--;
//        }

        reverse(nums, 0, n - 1);
        reverse(nums, 0, moveStep - 1);
        reverse(nums, moveStep, n - 1);
    }

    private void moveEnd2Begin(int[] nums) {
        int end = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = end;
    }


    /**
     * 翻转数组[i...j]
     *
     * @param nums
     * @param i
     * @param j
     */
    public void reverse(int[] nums, int i, int j) {
        while (i <= j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
