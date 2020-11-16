package com.zy;

import com.zy.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: 二叉排序树(二叉搜索树)实现
 *
 * @author guizy
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
        root = null;
        size = 0;
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
        remove(node(element));
    }

    /**
     * 删除结点的逻辑
     *
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) return;
        // node 不为空, 必然要删除结点, 先size--;
        size--;
        // 删除node是度为2的结点
        if (node.hasTwoChildren()) {
            //1 找到后继(也可以找到前驱)
            Node<E> successor = successor(node);
            //2 用后继结点的值覆盖度为2结点的值
            node.element = successor.element;
            //3 删除后继节点
            node = successor;
        }
        // 删除node,即删除后继节点 (node节点必然是度为1或0)
        // 因为node只有一个子节点/0个子节点, 如果其left!=null, 则用node.left来替代, node.left==null, 用node.right来替代,
        // 若node为叶子节点, 说明, node.left==null, node.right也为null, 则replacement==null;
        Node<E> replacement = node.left != null ? node.left : node.right;

        // 删除node是度为1的结点
        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) {  // node是度为1且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
            // 删除node是叶子节点, 且是根节点
        } else if (node.parent == null) {
            root = null;
        } else { // node是叶子结点, 且不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {  // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    /**
     * 传入element找到对应element对应的结点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {  // 说明element对应的结点, 比node的element大, 所以去它的右子树找
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null; // 没有找到element对应的结点
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    // ==================================默认遍历的方式=======================================

    /**
     * 前序遍历(根左右)
     */
    public void preorderTraversal() {
        this.preorderTraversal(root); // 从根节点开始遍历
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.print(node.element + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历(左根右 / 右根左), 相当于升序/降序
     */
    public void inorderTraversal() {
        this.inorderTraversal(root); // 从根节点开始遍历
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.print(node.element + " ");
        inorderTraversal(node.right);
    }

    /**
     * 后序遍历(左右根/右左根)
     */
    public void postorderTraversal() {
        this.postorderTraversal(root); // 从根节点开始遍历
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.element + " ");
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root); // 将根节点先入队

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 取出队头节点
            System.out.print(node.element + " ");
            if (node.left != null) {    // 如果该节点左节点不为空,则将该左节点入队
                queue.offer(node.left);
            }
            if (node.right != null) {   // 如果该节点右节点不为空,则将该右节点入队
                queue.offer(node.right);
            }
        }
    }

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

    /**
     * 根据传入的节点, 返回该节点的前驱节点 (中序遍历)
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
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
    private Node<E> successor(Node<E> node) {
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

    public interface Visitor<E> {
        void visit(E element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toString(root, sb, ""); // 如果是根节点什么都不加
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        ;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "L--");
        toString(node.right, sb, prefix + "R--");
    }

    public boolean isCompleteTree2() {
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

    // 判断一棵二叉树是否为完全二叉树(方式一, 推荐方式二)
    public boolean isCompleteTree() {
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
    public int height2() {
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
