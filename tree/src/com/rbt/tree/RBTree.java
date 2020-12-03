package com.rbt.tree;

import com.rbt.Interface.Comparator;

/**
 * Description: 红黑树
 *
 * @author guizy
 * @date 2020/12/3 22:49
 */
public class RBTree<E> extends BST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点(染成黑色)
        if (parent == null) {
            black(node);
            return;
        }

        // ------------- 一共 12 种情况--------------
        // 不需要处理的4种情况:  如果父节点是黑色, 直接返回
        if (isBlack(parent)) return;

        // 根据uncle节点的颜色来判断其他的各4种情况
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = parent.parent;

        // 需要处理的4种情况: 叔父节点是红色
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 把祖父节点染成红色, 当做新添加的结点处理(递归调用afterAdd)
            afterAdd(red(grand));
            return;
        }

        // 需要处理的4种情况: 叔父节点不是红色(叔父节点为空)
    }

    /**
     * 将node染成color色
     *
     * @param node  需要染色的节点
     * @param color 染成的颜色
     * @return 返回染色的节点
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 传进来的节点染成黑色
     *
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 传进来的节点染成红色
     *
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 返回当前节点的颜色
     *
     * @param node
     * @return 如果传入的节点为空, 默认为黑色
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    /**
     * 节点是否为黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 节点是否为红色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E> {

        boolean color;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
