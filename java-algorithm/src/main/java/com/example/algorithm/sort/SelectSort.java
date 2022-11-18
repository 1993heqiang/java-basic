package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/22.
 */
public class SelectSort {
    public static int[] sort(int arr[]){
        int len = arr.length;
        int minIndex;
        int temp = 0;
        for(int i=0;i<len-1;i++){
            minIndex = i;
            for(int j=i+1;j<len;j++){
                if(arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
    public static void main(String args[]){
        int arr[] = {1,5,3,32,2,4,1,45,5,2,45};
        System.out.println(Arrays.toString(sort(arr)));

    }
}
