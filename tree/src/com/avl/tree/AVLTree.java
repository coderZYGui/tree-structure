package com.avl.tree;

import com.avl.Interface.Comparator;

/**
 * Description: AVLTree
 *
 * @author guizy
 * @date 2020/11/18 23:31
 */
public class AVLTree<E> extends BST<E> {

    /**
     * 平衡因子的绝对值不能超过1
     */
    public static final int ONE = 1;

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 当添加新的节点的时候, 要判断添加的节点是否会导致树失衡
     *
     * @param node 添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        /*
            从添加节点的父节点开始寻找失衡节点, 找到高度最低的祖父节点就是失衡节点,
            将失衡节点旋转后, 让平衡因子 不超过 1 即可, 则失衡节点修复后, 其父节点,祖父节点都平衡了
         */
        while ((node = node.parent) != null) {
            // 判断node是否平衡
            if (isBalanced(node)) {

            } else {

            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= ONE;
    }

    /**
     * 因为在普通二叉树, 不需要height属性来判断节点的平衡因子, 所以
     * 定义一个AVLNode的AVLTree节点, height只用于AVLTree
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {

        /**
         * 通过节点的高度, 来判断该节点是否平衡
         */
        int height;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 节点的平衡因子
         *
         * @return 左子树高度 - 右子树高度
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }
    }
}
