package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/22.
 */
public class InsertSort {
    public static int[] sort(int arr[]){
        int len = arr.length;
        int temp;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[i]){
                    temp = arr[i];
                    for(int k=i;k>j;k--){
                        arr[k] = arr[k-1];
                    }
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
    public static void main(String args[]){
        int arr[] = {5,4,3,2,1};
        System.out.println(Arrays.toString(sort(arr)));

    }
}
