package org.melon.stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用栈实现队列
 * 使用两个队列
 * 第一个队列 push 数据，需要 pop 数据时，将第一个队列的数据 pop 到 第二个队列,直到第一个队列中只剩 1 个数据，然后将这个数据 pop 出来返回
 * 两个队列交替使用
 */
public class StackWithQueue {

    private Queue<Integer> queue;
    private Queue<Integer> help;

    public StackWithQueue(int size){
        queue = new ArrayBlockingQueue<>(size);
        help = new ArrayBlockingQueue<>(size);
    }

    private void push(int i) {
        queue.add(i);
    }

    private Integer pop(){
        // queue 中的数据导入 help，只留一个数据需要返回
        while (queue.size() > 1) {
            help.add(queue.poll());
        }
        // 需要返回的数据
        Integer val = queue.poll();
        // 交换队列位置
        Queue<Integer> temp = queue;
        queue = help;
        help = temp;
        return val;
    }
}
