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

    public static int [] quickSort1 (int [] ins, int start, int end) {
        if(start>=end){
            return ins;//这个返回值并没有影响，因为这个返回值没有使用到。
        }
        int mid = ins[start];
        int low = start;
        int hight = end;
        while(low < hight){
            while(low < hight && ins[hight]>=mid){//
                hight -=1;
            }
            ins[low] = ins[hight];

            while(low < hight && ins[low] < mid){
                low +=1;
            }
            ins[hight] = ins[low];
        }
        ins[low] = mid;
        quickSort1(ins, start, low-1);
        quickSort1(ins, low+1, end);

        return ins;
    }

}
