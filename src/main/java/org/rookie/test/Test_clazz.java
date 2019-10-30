package org.rookie.test;

import java.util.Arrays;

public class Test_clazz {
    public static void main(String[] args) {
        int[] arr = {1, 32, 2, 34, 3, 43, 23453, 23};
        Test_clazz sort = new Test_clazz();
        sort.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr, int left, int right) {
        int mid = (left + right) >> 1;
        if (left < right) {
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, right, mid);
        }
    }

    public void merge(int[] arr, int left, int right, int mid) {
        int[] newArr = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                newArr[index++] = arr[l++];
            } else {
                newArr[index++] = arr[r++];
            }
        }
        while (l <= mid) {
            newArr[index++] = arr[l++];
        }
        while (r <= right) {
            newArr[index++] = arr[r++];
        }
        System.arraycopy(newArr, 0, arr, left, newArr.length);
    }
}

