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
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
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
        emptyCheck();

        E root = elements[0];
        // 将数组最后的节点,替代堆顶节点; 这样做的目的是为了减少移动,提高效率
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;

        // 最后一个元素放到数组索引0的位置后, 要进行下滤, 修复二叉堆性质
        siftDown(0);
        return root;
    }

    /**
     * 删除堆顶元素, 并添加一个元素
     * @param element
     * @return
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        // 方式一: 两个O(logn)级别
//        E root = remove();
//        add(element);

        // 方式二: 可以将要添加的元素直接覆盖掉堆顶元素, 然后进行一次下滤操作即可; 这样的话就一次O(logn)
        E root = null;
        if (size == 0) {    // 表示数组中为空,堆中没有节点, 所以就不删除了, 但是可以添加
            elements[0] = element;
            size++;
        } else { // 表示堆不为空, 将堆顶元素删除
            root = elements[0];
            elements[0] = element;
            siftDown(0); // 下滤
        }
        return root;
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
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;
            // 将父元素存储在index位置
            elements[index] = parent;
            // 重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }

    /**
     * 让index位置的元素进行下滤操作, 最后一个元素放到数组索引0的位置后,可能不满足二叉堆的性质,要进行下滤, 修复二叉堆性质
     *
     * @param index 需要进行下滤的节点, 肯定是非叶子节点,因为要和他的子节点比较, 得到堆顶元素
     */
    private void siftDown(int index) {
        E element = elements[index];
        // 非叶子节点的数量: 公式:floor (n / 2)
        int notLeafCount = size >> 1;
        /*
            完全二叉树, 从上至下,从左向右,遇到的第一个叶子节点, 它后面的节点必然是叶子节点, 完全二叉树性质
            也就是说, `当index小于第一个叶子节点的索引`, 那么index肯定不是叶子节点

            第一个叶子节点的索引 = 非叶子节点的数量
         */

        // 确保index位置的元素必须要有子节点, 然后和最大的子节点进行交换位置
        // 如果index位置的元素 大于 它的子节点, 则不需要交换, 符合二叉堆性质, 退出循环
        /*
            第一个叶子节点的索引 = 非叶子节点的数量
            index < 第一个非叶子节点的数量, 就表示它肯定是有子节点的
         */
        while (index < notLeafCount) {
            // 表示index索引的节点肯定是非叶子节点
            // index节点有两种情况 (基于完全二叉树的性质)
            // 1. 只有左子节点
            // 2. 同时有左右子节点
            // 默认为 左子节点 跟 交换完的堆顶节点 比较 ;左子节点的索引:公式 2i+1,右子节点的索引: 2i+2 == 左子节点索引 + 1
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            // 如果有左右节点, 选出最大的子节点
            //  // 表示算出来的右子节点的索引,是存在的, rightIndex < size 且 右子节点大于左子节点
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }

            if (compare(element, child) >= 0) break; // 说明element交换过来的堆顶节点 大于它的子节点, 此时满足二叉堆性质,退出

            // 将子节点存放到index位置; `然后要将childIndex赋值给index`, 也就是说此时的堆顶节点跑到了childIndex的位置,
            // 所以要将更换堆顶节点index的值, 就需要使用 index = childIndex; 因为下一轮还是需要index的堆顶元素再和它的子节点比较, 以此类推
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
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
