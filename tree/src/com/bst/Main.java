package com.bst;

import com.bst.printer.BinaryTrees;
import org.junit.Test;

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

    @Test
    public void test2() {
        // 创建BST
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        // 树状打印
        BinaryTrees.println(bst);
        // 遍历器
        StringBuilder sb = new StringBuilder();
        BinaryTree.Visitor<Integer> visitor = new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append(element).append(" ");
                return false;
            }
        };
        // 遍历
        sb.delete(0, sb.length());
        bst.preorderTraversal(visitor);
        System.out.println(sb);
        Asserts.test(sb.toString().equals("7 4 2 5 9 8 11 "));

        sb.delete(0, sb.length());
        bst.inorderTraversal(visitor);
        Asserts.test(sb.toString().equals("2 4 5 7 8 9 11 "));

        sb.delete(0, sb.length());
        bst.postorderTraversal(visitor);
        Asserts.test(sb.toString().equals("2 5 4 8 11 9 7 "));
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
