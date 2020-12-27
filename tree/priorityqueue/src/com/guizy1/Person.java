package com.guizy1;

public class Person implements Comparable<Person> {
    private String name;
    private int boneBreak;

    public Person(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public int compareTo(Person person) {
        return this.boneBreak - person.boneBreak; // 表示boneBreak大的优先级就大
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
    }
}
