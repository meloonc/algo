package org.melon.stack;

public class StackWithArray {

    private int[] arr;
    private int index;
    private int limit;

    public StackWithArray(int limit) {
        arr = new int[limit];
        this.limit = limit;
        index = 0;
    }

    public void push(int val) {
        arr[index] = val;
        index++;
    }

    public int pop() {
        int val = arr[index - 1];
        index--;
        return val;
    }

    public int peek(){
        return arr[index -1];
    }
}
