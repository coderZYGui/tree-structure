package com.guizy;

import com.guizy.heap.BinaryHeap;
import com.guizy.printer.BinaryTrees;
import org.junit.Test;

import java.util.Comparator;

/**
 * Description:
 *
 * @author guizy1
 * @date 2020/12/25 17:29
 */
public class Main {

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) {  // 将前k个数添加到小顶堆
                heap.add(data[i]);  // O(logk)
            } else {    // 如果第k+1个数, 并且大于堆顶元素
                if (data[i] > heap.get()) {
                    heap.replace(data[i]);  // O(logK)
                }
            }
        }
        BinaryTrees.println(heap);
    }

    @Test
    public void test2() {
        // 实现小顶堆
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, (o1, o2) -> o2 - o1);
        BinaryTrees.println(heap);
    }

    @Test
    public void test1() {
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
