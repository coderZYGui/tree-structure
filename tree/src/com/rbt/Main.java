package com.rbt;

import com.rbt.printer.BinaryTrees;
import com.rbt.tree.RBTree;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
public class Main {
    public static void main(String[] args) {
        test3();
    }

    public static void test3() {
        Integer[] data = new Integer[]{
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);

        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("-------------------------------------");
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(rb);
        }
    }

    // 测试RBTree的添加操作 (打印最终的结果)
    public static void test() {
        Integer[] data = new Integer[]{
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
    }

    // 测试RBTree的添加操作 (打印添加结点的过程)
    public static void test2() {
        Integer[] data = new Integer[]{
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(rb);
            System.out.println("-------------------------------------");
        }
    }
}
