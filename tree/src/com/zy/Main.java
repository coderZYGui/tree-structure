package com.zy;

import com.zy.printer.BinaryTrees;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
public class Main {
    public static void main(String[] args) {

        test2();
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
