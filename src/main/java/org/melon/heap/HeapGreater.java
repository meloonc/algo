package org.melon.heap;

import java.util.*;

/**
 * 加强堆
 * 提供反向索引，方便查找
 */
public class HeapGreater<T> {
    private ArrayList<T> heap;
    private Integer heapSize;
    private Map<T, Integer> index;
    private Comparator<T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        this.heapSize = 0;
        this.index = new HashMap<>();
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    /**
     * Determines whether the given element is contained in the heap.
     *
     * @param t the element to check for containment
     * @return true if the element is contained in the heap, false otherwise
     */
    public boolean contains(T t) {
        return index.containsKey(t);
    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return heapSize;
    }

    /**
     * Retrieves the element at the top of the heap without removing it.
     *
     * @return the element at the top of the heap
     */
    public T peek() {
        return heap.get(0);
    }

    /**
     * Retrieves the element at the top of the heap with removing it.
     *
     * @return the element at the top of the heap
     */
    public T pop() {
        T top = heap.get(0);
        index.remove(top);
        swap(0, heapSize - 1);
        heap.remove(--heapSize);
        heapify(0);
        return top;
    }

    /**
     * Adds an element of type T to the collection.
     *
     * @param t the element to be added
     * @return true if the element was successfully added, false otherwise
     */
    public void add(T t) {
        heap.add(t);
        index.put(t, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * Remove the element of the heap.
     *
     * @param t
     * @return
     */
    public void remove(T t) {
        T tail = heap.get(heapSize - 1);
        Integer i = index.get(t);
        index.remove(t);
        heap.remove(--heapSize);
        if (t != tail) {
            heap.set(i, tail);
            index.put(tail, i);
            heapify(i);
            heapInsert(i);
        }

    }

    /**
     * Swaps the elements at the given indices in the heap.
     *
     * @param i1 the index of the first element to swap
     * @param i2 the index of the second element to swap
     */
    private void swap(int i1, int i2) {
        T t1 = heap.get(i1);
        T t2 = heap.get(i2);
        this.heap.set(i1, t2);
        this.heap.set(i2, t1);
        index.put(t1, i2);
        index.put(t2, i1);
    }

    /**
     * Inserts an element into the heap and maintains the heap property.
     *
     * @param i the element to be inserted
     */
    private void heapInsert(Integer i) {
        if (i == 0) {
            return;
        }
        int parent = (i - 1) / 2;
        int compare = this.comparator.compare(heap.get(i), this.heap.get(parent));
        if (compare < 0) {
            swap(i, parent);
            heapInsert(parent);
        }
    }

    /**
     * Heapify the heap starting from the given index.
     *
     * @param i the index to start heapify from
     */
    private void heapify(Integer i) {
        int left = 2 * i + 1;
        while (left < heapSize) {
            int max = left + 1 < heapSize ? (this.comparator.compare(heap.get(left), this.heap.get(left + 1)) < 0 ? left + 1 : left) : left;
            max = this.comparator.compare(heap.get(max), this.heap.get(i)) < 0 ? max : i;
            if (max == i) {
                break;
            }
            swap(i, max);
            i = max;
            left = 2 * i + 1;
            heapify(max);
        }
    }

    /**
     * Retrieves the index of the specified element in the list.
     *
     * @param t the element whose index is to be retrieved
     * @return the index of the specified element, or null if the element is not found
     */
    public Integer indexOf(T t) {
        return index.get(t);
    }

}
