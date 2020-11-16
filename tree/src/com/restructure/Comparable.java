package com.restructure;

/**
 * Description: 比较的接口
 *
 * @author zygui
 * @date 2020/5/16 14:45
 */
public interface Comparable<E> {
    /**
     * 另外传一个E对象,返回结果是什么?
     * @param e 需要比较的对象
     * @return
     */
    int compareTo(E e);
}
