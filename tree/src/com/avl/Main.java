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
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        AVLTree<Integer> bst = new AVLTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }

        // 测试
        BinaryTrees.println(bst);
        // 测试删除根节点7
        bst.remove(7);
        BinaryTrees.println(bst);
    }
}
