package com.avl;

import com.avl.printer.BinaryTrees;
import com.avl.tree.AVLTree;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Integer[] data = new Integer[]{
                40, 35, 12, 54, 3, 29, 98, 100, 2, 76
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
            // System.out.println("[" + data[i] + "]");
            // BinaryTrees.println(avl);
            // System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        }

        for (int i = 0; i < data.length; i++) {
            avl.remove(data[i]);
             System.out.println("[" + data[i] + "]");
             BinaryTrees.println(avl);
             System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        }


        // avl.remove(99);
        // avl.remove(85);
        // avl.remove(95);
        // BinaryTrees.println(avl);
    }
}
