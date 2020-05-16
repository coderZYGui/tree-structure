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
        Integer data[] = new Integer[] {
            7, 4, 9, 2, 5, 8, 11, 3, 12
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        // 此时这里就没有使用自定义的比较器了,使用的是Person内部实现CompareTo的比较规则
//        BinarySearchTree<Person> bst2 = new BinarySearchTree<>();
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));
//
//        // 这里使用的是匿名内部类来实现接口,并重写接口的方法
//        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new Comparator<Person>() {
//            @Override
//            public int compare(Person e1, Person e2) {
//                return e2.getAge() - e1.getAge();
//            }
//        });
//        bst3.add(new Person(12));
//        bst3.add(new Person(15));
//    }
//
//    // 创建不同规则的比较器(年龄大的,属于大的元素)
//    private static class PersonComparator1 implements Comparator<Person> {
//
//        @Override
//        public int compare(Person e1, Person e2) {
//            return e1.getAge() - e2.getAge();
//        }
    }
}
