package com.bst;

import com.bst.printer.BinaryTrees;

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
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        // 测试
        BinaryTrees.println(bst);

        // 测试删除的是叶子结点
        /*bst.remove(1);
        bst.remove(3);
        bst.remove(12);*/
        // bst.remove(5);

        // 测试删除度为1结点
        //bst.remove(11);

        // 测试删除度为2结点
        // bst.remove(9);

        // 测试删除根节点7
        bst.remove(7);
        BinaryTrees.println(bst);
    }
}
