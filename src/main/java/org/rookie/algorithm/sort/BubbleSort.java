package org.rookie.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/22.
 */
public class BubbleSort {
    public static int[] sort(int arr[]){
        int temp = 0;
        int len = arr.length;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-1-i;j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    public static void main(String args[]){
        int arr[] = {1,5,3,32};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
