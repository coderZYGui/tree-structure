package com.zy;

/**
 * Description:
 *
 * @author zygui
 * @date 2020/4/15 21:08
 */
public class Main {
    public static void main(String[] args) {
//        Integer data[] = new Integer[] {
//            7, 4, 9, 2, 5, 8, 11, 3
//        };
//
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        for (int i = 0; i < data.length; i++) {
//            bst.add(data[i]);
//        }

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>();
        bst2.add(new Person(12));
        bst2.add(new Person(15));
    }
}
