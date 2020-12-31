package com.bst;

import com.bst.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description: 二叉树
 *
 * @author guizy
 * @date 2020/11/16 22:34
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    // 定义根节点
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 前序遍历(根左右)
     */
    public void preorderTraversal(Visitor<E> visitor) {
        // 递归实现
        // this.preorderTraversal(root, visitor); // 从根节点开始遍历

        // 非递归实现
        if (visitor == null || root == null) return;
        Node<E> node = root;
        Stack<Node<E>> stack = new Stack<>(); // 用来存放右子节点
        while (true) {
            if (node != null) {
                // 拼接node节点
                if (visitor.visit(node.element)) return;
                // 将右子节点入栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 一直向左找
                node = node.left;
            } else if (stack.isEmpty()) {
                // 表示遍历完了, 或者二叉树没有右子节点
                return;
            } else {
                node = stack.pop();
            }
        }
    }

    /**
     * 前序遍历(根左右)
     */
    public void preorderTraversal2(Visitor<E> visitor) {
        // 递归实现
        // this.preorderTraversal(root, visitor); // 从根节点开始遍历

        // 非递归实现
        if (visitor == null || root == null) return;
        Stack<Node<E>> stack = new Stack<>(); // 用来存放右子节点
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            // 访问node节点
            if (visitor.visit(node.element)) return;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    /**
     * 递归实现前序遍历
     *
     * @param node
     * @param visitor
     */
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
        // 递归实现
        // this.inorderTraversal(root, visitor); // 从根节点开始遍历

        // 非递归实现
        if (visitor == null || root == null) return;
        Node<E> node = root;
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return;
            } else {
                node = stack.pop();
                // 访问node节点
                if (visitor.visit(node.element)) return;
                // 让右节点进行中序遍历
                node = node.right;
            }
        }
    }

    /**
     * 递归实现中序遍历
     */
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
        // 递归实现
        // this.postorderTraversal(root, visitor); // 从根节点开始遍历

        // 非递归实现
        if (visitor == null || root == null) return;
        // 记录上一次弹出访问的节点
        Node<E> prev = null;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 偷看栈顶是哪个元素
            Node<E> top = stack.peek();
            if (top.isLeaf() || (prev != null && prev.parent == top)) {
                prev = stack.pop();
                // 访问节点
                if (visitor.visit(prev.element)) return;
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
    }

    /**
     * 递归实现后序遍历
     *
     * @param node
     * @param visitor
     */
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
        boolean visit(E element);
    }

    // 计算二叉树排序树的高度
    // 方式一: 递归的方式
    public int height() {
        return height(root);
    }

    public int height(Node<E> node) {
        if (node == null) return 0;
        // 左子节点或右子节点中高度最高的值 + 1
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 方式二: 迭代的方式

    /**
     * 使用层序遍历的方式, 计算树的高度
     */
    /*public int height2() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        // 当前队列中节点个数
        int elementCount = 1;
        // 树的高度
        int height = 0;
        while (!queue.isEmpty()) {
            // 取出节点
            Node<E> node = queue.poll();
            // 队列中节点个数-1
            elementCount--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 当 elementCount = 0, 说明当前层的节点遍历完成
            if (elementCount == 0) {
                // 记录下一层节点个数
                elementCount = queue.size();
                // 高度+1
                height++;
            }
        }
        return height;
    }*/

    // 判断一棵二叉树是否为完全二叉树(推荐方式二)
    // 方式二
    public boolean isCompleteTree() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root); // 将根节点先入队

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 取出队头节点

            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {    // 如果该节点左节点不为空,则将该左节点入队
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {   // 如果该节点右节点不为空,则将该右节点入队
                queue.offer(node.right);
            } else {
                // 能来到这里, 说明后面的节点都是叶子节点
                // node.left == null && node.right == null
                // node.left != null && node.right == null
                leaf = true;
            }
        }
        return true;
    }

    // 方式一
    /*public boolean isCompleteTree2() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root); // 将根节点先入队

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 取出队头节点

            // node不是叶子节点的情况
            if (leaf && !node.isLeaf()) return false;

            if (node.hasTwoChildren()) {    // 左右节点都不为空
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {   // 不符合完全二叉树的要求
                return false;
            } else {    // 这里的节点都是叶子节点
                leaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }*/

    /**
     * 根据传入的节点, 返回该节点的前驱节点 (中序遍历)
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return node;

        // (中序遍历)前驱节点在左子树当中(node.left.right.right.right...)
        Node<E> p = node.left;
        // 左子树存在
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 程序走到这里说明左子树不存在; 从父节点、祖父节点中寻找前驱节点
        /*
         * node的父节点不为空 && node是其父节点的左子树时. 就一直往上寻找它的父节点
         *  因为node==node.parent.right, 说明你在你父节点的右边, 那么node.parent就是其node的前驱节点
         */
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // 能来到这里表示: 两种情况如下
        // node.parent == null 表示没有父节点(根节点),返回空 ==> return node.parent;
        // node==node.parent.right 说明你在你父节点的右边, 那么node.parent就是其node的前驱节点 ==> return node.parent;
        return node.parent;
    }

    /**
     * 根据传入的节点, 返回该节点的后驱节点 (中序遍历)
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return node;

        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // node.right为空
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    protected static class Node<E> {
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

        /**
         * 判断是否为叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否为左右节点都不为空的节点
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
//        return myNode.element;
    }
}
