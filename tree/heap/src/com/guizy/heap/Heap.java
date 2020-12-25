package com.guizy.heap;

/**
 * Description: Heap接口
 *
 * @author guizy1
 * @date 2020/12/25 17:31
 */
public interface Heap<E> {

    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    /**
     * 获取堆顶元素
     *
     * @return
     */
    E get();

    /**
     * 删除堆顶元素
     *
     * @return
     */
    E remove();

    /**
     * 删除堆顶元素的同时插入一个新元素
     *
     * @param element
     * @return
     */
    E replace(E element);
}
