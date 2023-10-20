package org.melon.tree;

import java.util.Arrays;

/**
 * 请把纸条竖着放在桌⼦上，然后从纸条的下边向上⽅对折，压出折痕后再展 开。此时有1条折痕，突起的⽅向指向纸条的背⾯，这条折痕叫做“下”折痕 ；
 * 突起的⽅向指向纸条正⾯的折痕叫做“上”折痕。如果每次都从下边向上⽅ 对折，对折N次。请从上到下计算出所有折痕的⽅向。
 *
 * 给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",若为上折痕则为"up".
 *
 * 测试样例：
 * 1
 * 返回：["down"]
 *
 */
public class FoldPaper {


    public static void main(String[] args) {
        int n = 3;
        foldPaper(1, n , true);
    }

    // 二叉树中序遍历
    public static void foldPaper(int level, int n, boolean down) {
        if(n < level) {
            return;
        }

        foldPaper(level+1, n , true);
        String s = down ? "凹" : "凸";


        System.out.println("折痕：" + s);
        foldPaper(level+1, n , false);
    }

//    public static String[] process()

}
