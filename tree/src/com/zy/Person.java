package com.zy;

/**
 * Description: Person类
 *
 * @author zygui
 * @date 2020/5/16 14:41
 */
public class Person implements Comparable<Person>{
    private int age;

    public Person(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person e) {
//        if (this.age > e.age) return 1;
//        if (age < e.age) return -1;
//        return 0;
        return age - e.age; // 相当于上面的三行代码
    }
}