package com.zy;

/**
 * Description: 二叉排序树(二叉搜索树)实现
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
// 表示传进来的元素 E 必须要实现 Comparable接口中的方法; 这样以来,传入的元素必须要实现这个接口中的方法,逻辑都写到了元素中,不灵活!
public class BinarySearchTree<E> {

    private int size;
    // 定义根节点
    private Node<E> root;

    private Comparator<E> comparator; // 定义一个比较器

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            // 给根节点赋值,且根节点没有父节点
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        Node<E> parent = root; // 这个是第一次比较的父节点
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);   // 两者具体比较的方法
            parent = node; // 记录其每一次比较的父节点
            if (cmp > 0) {
                // 插入的元素大于根节点的元素,插入到根节点的右边
                node = node.right;
            } else if (cmp < 0) {
                // 插入的元素小于根节点的元素,插入到根节点的左边
                node = node.left;
            } else { // 相等
                return;
            }
        }
        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * @return 返回值等于0, 代表e1=e2
     * 大于0,代表e1>e2
     * 小于0,代表e1<e2
     */
    private int compare(E e1, E e2) {
        return comparator.compare(e1, e2);
    }

    /**
     * 二叉排序树结点内容不能为空(否则怎么排序呢?)
     *
     * @param element 结点内容
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            // 手动抛出异常对象
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        // 这里不初始化左,右结点,因为不常用,比如所叶子结点,就灭有左右结点
        // 父节点,除了根节点外,都有父节点
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
