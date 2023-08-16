package org.melon.queue;

import java.util.Arrays;

public class QueueWithArray {

    private int[] arr;
    private int limit;
    private int size;
    private int begin; // pop 的位置
    private int end; // push 的位置

    public QueueWithArray(int limit) {
        this.arr = new int[limit];
        this.limit = limit;
        this.size = 0;
        this.begin = 0;
        this.end = 0;
    }

    public void push(int val) {
        if (size == limit) {
            throw new RuntimeException("full");
        }
        arr[end] = val;
        end++;
        size++;
        if (end == limit) {
            end = 0;
        }
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("empty");
        }
        int val = arr[begin];
        begin++;
        size--;
        if (begin == limit) {
            begin = 0;
        }
        return val;
    }

    public static void main(String[] args) {
        QueueWithArray queue = new QueueWithArray(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        System.out.println(Arrays.toString(queue.arr));
        int pop = queue.pop();
        System.out.println(Arrays.toString(queue.arr));
        queue.push(6);
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        System.out.println(Arrays.toString(queue.arr));
        System.out.println(queue.size);



    }
}
