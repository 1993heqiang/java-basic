package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/22.
 */
public class QuickSort {
    static void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }

    static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
    public static void main(String args[]){
        int arr[] = {1,5,3,32,233,4,1,45,98,2,45};
        quicksort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
