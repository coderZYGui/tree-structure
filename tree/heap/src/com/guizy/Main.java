package com.guizy;

import com.guizy.heap.BinaryHeap;
import com.guizy.printer.BinaryTrees;
import org.junit.Test;

/**
 * Description:
 *
 * @author guizy1
 * @date 2020/12/25 17:29
 */
public class Main {

    public static void main(String[] args) {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        BinaryTrees.println(heap);

        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heap);

    }

    @Test
    public void test() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);

        BinaryTrees.print(heap);
//        heap.remove();
//        BinaryTrees.print(heap);
        System.out.println(heap.replace(70));
        BinaryTrees.print(heap);
    }
}
