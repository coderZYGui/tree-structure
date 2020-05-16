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

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator1());
        bst2.add(new Person(12));
        bst2.add(new Person(15));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
        bst3.add(new Person(12));
        bst3.add(new Person(15));
    }

    // 创建不同规则的比较器(年龄大的,属于大的元素)
    private static class PersonComparator1 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    // 创建第二种比较规则的比较器(年龄小的,属于大的元素)
    private static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }
}
