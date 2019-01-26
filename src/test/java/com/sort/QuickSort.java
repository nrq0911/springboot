package com.sort;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] a, int low, int high) {
        if (low > high) {
            return;
        }//递归的出口
        int i = low;
        int j = high;
        int key = a[low];
        while (i < j) {
            while (a[j] > key && i < j) {
                j--;
            }//找到第一个比key小的数
            while (a[i] <= key && i < j) {
                i++;
            }//找到第一个比key大的数
            if (i < j) {//如果i小于j，交换a[i],a[j]
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int p = a[i];
        a[i] = a[low];
        a[low] = p;//调整key的位置
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);
    }

}
