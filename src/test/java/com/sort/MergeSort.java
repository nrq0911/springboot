package com.sort;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] a, int low, int mid, int high) {
        int[] mergeArr = new int[high - low + 1];//申请一个新空间来保存排序后数组
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                mergeArr[k] = a[i];
                k++;
                i++;
            } else {
                mergeArr[k] = a[j];
                k++;
                j++;
            }
        }
        while (i <= mid) {
            mergeArr[k] = a[i];
            k++;
            i++;
        }//把左边剩余的元素导入
        while (j <= high) {
            mergeArr[k] = a[j];
            j++;
            k++;
        }//把右边剩余的元素导入
        for (int m = 0; m < mergeArr.length; m++) {
            a[m + low] = mergeArr[m];
        }//将新排好序的数组放入元素相应的位置中
    }

    public static void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);//左
            mergeSort(a, mid + 1, high);//右
            merge(a, low, mid, high);//左右合并
        }
    }
}
