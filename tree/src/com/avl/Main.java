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
                85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(avl);
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        }

        // BinaryTrees.println(avl);
    }
}
