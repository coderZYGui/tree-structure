package com.bst_bak;

import com.bst_bak.printer.BinaryTrees;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
public class Main {
    public static void main(String[] args) {

        test6();
    }

    public static void test6() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3 ,12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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

    /**
     * 测试用例5: 判断二叉树是否是完全二叉树
     */
    public static void test5() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5
        };

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }

        // MJ的二叉树排序树打印工具
        BinaryTrees.println(bst1);

        System.out.println(bst1.isCompleteTree2()); // 推荐使用这种方式, 更清晰
        System.out.println(bst1.isCompleteTree());
    }

    /**
     * 测试用例4: 计算二叉树的高度
     */
    public static void test4() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8
        };

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }

        // MJ的二叉树排序树打印工具
        BinaryTrees.println(bst1);

        // 测试计算二叉树高度
         System.out.println(bst1.height()); // 递归的方式
//         System.out.println(bst1.height2()); // 使用迭代的方式
    }

    /**
     * 测试用例3: 遍历测试
     */
    public static void test3() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8
        };

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }

        // MJ的二叉树排序树打印工具
        BinaryTrees.println(bst1);

        // --------------------------------------------

        // 前序遍历
        // bst1.preorderTraversal();

        // 中序遍历
        // bst1.inorderTraversal();

        // 后序遍历
        // bst1.postorderTraversal();

        // 层序遍历
        // bst1.levelOrderTraversal();

        // --------------------------------------------

        // 使用的是外界调用遍历的接口, 获取到元素后, 可以对元素进行自定义操作
        // 层序遍历
//         bst1.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//             @Override
//             public void visit(Integer element) {
//                 System.out.print("_" + element + "_");
//             }
//         });

        // 中序遍历
//        bst1.inorderTraversal(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_");
//            }
//        });

        // --------------------------------------------

        // 使用我们自定义的打印器(打印的是 前序遍历 结果)
        // System.out.println(bst1);

        // --------------------------------------------
    }

    /**
     * 测试用例2: 二叉树传入Person类型
     */
    public static void test2() {
        // 此时这里就没有使用自定义的比较器了,使用的是Person内部实现CompareTo的比较规则
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst2 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }

        BinaryTrees.println(bst2);

        System.out.println("---------------------------------------------");

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst3.add(new Person(data[i]));
        }

        BinaryTrees.println(bst3);
    }

    /**
     * 测试用例1: 二叉树传入Integer类型; 扩展: 内置的一些类,已经默认实现Comparable<T>接口,并实现了compareTo方法
     */
    public static void test1() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }

        BinaryTrees.println(bst1);
    }
}
