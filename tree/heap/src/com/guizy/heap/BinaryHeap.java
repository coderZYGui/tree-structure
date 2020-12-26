package com.guizy.heap;

import com.guizy.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * Description: 二叉堆(大顶堆)
 *
 * @author guizy1
 * @date 2020/12/25 17:33
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    private E[] elements;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        // 上滤,当添加一个节点, 要恢复二叉堆的性质
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }

    /**
     * 让index位置的元素上滤
     *
     * @param index
     */
    private void siftUp(int index) {
//        E e = elements[index];
//        // 必须要存在父节点, index > 0表示有父节点
//        while (index > 0) {
//            // 获取父节点的索引. 根据公式, (index-1)/2
//            int pindex = (index - 1) >> 1;
//            E p = elements[pindex];
//            // 插入的节点 <= 父节点, 此时符合二叉堆性质,退出
//            if (compare(e, p) <= 0) return;
//            // 交换index, pindex位置的内容
//            E tmp = elements[index];
//            elements[index] = elements[pindex];
//            elements[pindex] = tmp;
//            // 重新赋值(继续上滤),循环判断
//            index = pindex;
//        }

        // 对比上面, 进行优化
        E e = elements[index];
        while (index > 0) {
            int pindex = (index - 1) >> 1;
            E p = elements[pindex];
            if (compare(e, p) <= 0) break;
            // 将父元素存储在index位置
            elements[index] = p;
            // 重新赋值index
            index = pindex;
        }
        elements[index] = e;
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0)
            throw new IndexOutOfBoundsException("Heap is empty");
    }

    private void elementNotNullCheck(E element) {
        if (element == null)
            throw new IllegalArgumentException("Element must not be null");
    }

    @Override
    public Object root() {
        // 返回的根节点, 就是数组0位置的元素
        return 0;
    }

    @Override
    public Object left(Object node) {
        // 左索引 2 * index + 1
        int index = (int) node;
        index = (index << 1) + 1;
        // 因为index索引在数组中和size的关系, index从0开始, 所以肯定不能index>=size,越界
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = (int) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
