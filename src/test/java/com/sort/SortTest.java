package com.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortTest {

    @Test
    public void sort() throws Exception {
        Random random = new Random();
        int[] array = new int[10];
        int[] array2 = new int[10];
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(20);
            array[i] = a;
            array2[i] = a;
        }

        System.out.println("未排序的数组：" + Arrays.toString(array));
        MergeSort.mergeSort(array, 0, array.length - 1);
        System.out.println("排序后的数组：" + Arrays.toString(array) + "\n\n\n\n");


        System.out.println("未排序的数组：" + Arrays.toString(array2));
        QuickSort.quickSort(array2, 0, array2.length - 1);
        System.out.println("排序后的数组：" + Arrays.toString(array2));


    }

}
