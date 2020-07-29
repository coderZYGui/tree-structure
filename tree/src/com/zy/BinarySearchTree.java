package com.zy;

import com.zy.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: 二叉排序树(二叉搜索树)实现
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
// 为了扩展,我们可以自己定义比较器,也不要抛弃Comparable的方式,使两者兼容,选择性更多
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    // 定义根节点
    private Node<E> root;

    private Comparator<E> comparator; // 定义一个比较器

    public BinarySearchTree() {
        this(null);
    }

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
                node.element = element;
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

    // ==================================默认遍历的方式=======================================
//    /**
//     * 前序遍历(根左右)
//     */
//    public void preorderTraversal() {
//        this.preorderTraversal(root); // 从根节点开始遍历
//    }
//
//    private void preorderTraversal(Node<E> node) {
//        if (node == null) return;
//        System.out.print(node.element + " ");
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//    }
//
//    /**
//     * 中序遍历(左根右 / 右根左), 相当于升序/降序
//     */
//    public void inorderTraversal() {
//        this.inorderTraversal(root); // 从根节点开始遍历
//    }
//
//    private void inorderTraversal(Node<E> node) {
//        if (node == null) return;
//        inorderTraversal(node.left);
//        System.out.print(node.element + " ");
//        inorderTraversal(node.right);
//    }
//
//    /**
//     * 后序遍历(左右根/右左根)
//     */
//    public void postorderTraversal() {
//        this.postorderTraversal(root); // 从根节点开始遍历
//    }
//
//    private void postorderTraversal(Node<E> node) {
//        if (node == null) return;
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//        System.out.print(node.element + " ");
//    }
//
//    /**
//     * 层序遍历
//     */
//    public void levelOrderTraversal() {
//        if (root == null) return;
//
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root); // 将根节点先入队
//
//        while (!queue.isEmpty()) {
//            Node<E> node = queue.poll(); // 取出队头节点
//            System.out.print(node.element + " ");
//            if (node.left != null) {    // 如果该节点左节点不为空,则将该左节点入队
//                queue.offer(node.left);
//            }
//            if (node.right != null) {   // 如果该节点右节点不为空,则将该右节点入队
//                queue.offer(node.right);
//            }
//        }
//    }

    // ==================================外界自定义遍历的方式=======================================

    /**
     * 前序遍历(根左右)
     */
    public void preorderTraversal(Visitor<E> visitor) {
        this.preorderTraversal(root, visitor); // 从根节点开始遍历
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历(左根右 / 右根左), 相当于升序/降序
     */
    public void inorderTraversal(Visitor<E> visitor) {
        this.inorderTraversal(root, visitor); // 从根节点开始遍历
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历(左右根/右左根)
     */
    public void postorderTraversal(Visitor<E> visitor) {
        this.postorderTraversal(root, visitor); // 从根节点开始遍历
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }

    // 层序遍历
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root); // 将根节点先入队

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 取出队头节点

            visitor.visit(node.element);

            if (node.left != null) {    // 如果该节点左节点不为空,则将该左节点入队
                queue.offer(node.left);
            }
            if (node.right != null) {   // 如果该节点右节点不为空,则将该右节点入队
                queue.offer(node.right);
            }
        }
    }
    public interface Visitor<E> {
        void visit(E element);
    }

    /**
     * @return 返回值等于0, 代表e1=e2
     * 大于0,代表e1>e2
     * 小于0,代表e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) { // 这里表示传入了比较器
            // 优先使用比较器
            return comparator.compare(e1, e2);
        }
        // 这里表示没有使用比较器,此时再强制将传入的元素实现Comparable接口,并重写接口中的方法
        return ((java.lang.Comparable<E>) e1).compareTo(e2);
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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
}
