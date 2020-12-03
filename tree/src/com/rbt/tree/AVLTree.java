package com.rbt.tree;

import com.rbt.Interface.Comparator;

/**
 * Description: AVLTree
 *
 * @author guizy
 * @date 2020/11/18 23:31
 */
@SuppressWarnings("all")
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
            将失衡节点旋转后, 让平衡因子 不超过 1 即可, 则失衡节点修复后, 整棵树就平衡了
         */
        while ((node = node.parent) != null) {
            // 判断node是否平衡
            if (isBalanced(node)) {
                /*
                    因为即使添加完一个结点, 整棵树也是平衡的, 但是node的父节点高度
                    也发生了变化, 所以我们要更新它们的高度; 新添加的结点默认高度为1(height默认)了
                */
                updateHeight(node);
            } else {
                // 能到这里说明, node肯定不平衡, 且node是高度最小的节点
                // 恢复平衡
                rebalance(node);
                // 整棵树都平衡
                break;
            }
        }
    }

    /**
     * @param node 被删除的结点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断node是否平衡
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 恢复平衡-分LL、LR、RL、RR来旋转
     *
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡-统一旋转
     *
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else { // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    /**
     * 统一旋转
     */
    private void rotate(
            Node<E> r, // 子树的根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {
        // 让d成为这颗子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        if (a != null) a.parent = b;
        b.right = c;
        if (c != null) c.parent = b;
        // 更新b的高度, 因为b的左右子树都变了, 所以要更新高度
        updateHeight(b);

        // e-f-g
        f.left = e;
        if (e != null) e.parent = f;
        f.right = g;
        if (g != null) g.parent = f;
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    /**
     * 对node进行左旋转
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 对node进行右旋转
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 旋转之后, 更新它们的parent; 并且更新旋转后的高度
     *
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent为子树的根节点
        parent.parent = grand.parent;
        // 如果grand是其父节点的left, 则将grand.parent.left = parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
            // grand是根节点
        } else {
            root = parent;
        }

        // 更新child的parent
        if (child != null)
            child.parent = grand;

        // 更新grand的parent
        grand.parent = parent;

        // 更新高度(因为grand、parent的左右子树都变了, 先更新grand(矮的))
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 更新节点的高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 判断节点是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= ONE;
    }

    /**
     * AVLNode
     * <p>
     * 因为在普通二叉树, 不需要height属性来判断节点的平衡因子, 所以
     * 定义一个AVLNode的AVLTree节点, height只用于AVLTree
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {

        /**
         * 节点的高度 = 节点到叶子节点路径上节点的总数量
         * 通过节点的高度, 来判断该节点是否平衡, 因为添加的肯定是叶子节点,
         * 所以设置叶子节点的默认高度为1
         */
        int height = ONE;

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

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = ONE + Math.max(leftHeight, rightHeight);
        }

        /**
         * 返回节点中左右子树中高度最高的子树
         * 若左右高度相同, 则根据节点属于其父节点的左/右, 返回节点的左/右
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
