package com.guizy1.queue;

import com.guizy1.heap.BinaryHeap;

import java.util.Comparator;

public class PriorityQueue<E> {
    private BinaryHeap<E> heap = new BinaryHeap<>();

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        return heap.remove();
    }

    /**
     * 获取优先级最高的元素,即堆顶元素
     *
     * @return
     */
    public E front() {
        return heap.get();
    }
}
