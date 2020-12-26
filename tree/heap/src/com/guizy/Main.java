package com.guizy;

import com.guizy.heap.BinaryHeap;
import com.guizy.printer.BinaryTrees;

/**
 * Description:
 *
 * @author guizy1
 * @date 2020/12/25 17:29
 */
public class Main {
    public static void main(String[] args) {

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
        heap.remove();
        BinaryTrees.print(heap);
    }
}
