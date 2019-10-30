package org.rookie.sort;

import java.util.Arrays;

public class QuickSortCopy {
	public static void sort(int[] nums,int left,int right){
		int pos;
		if(left<right){
			pos = partition(nums,left,right);
			sort(nums,left,pos-1);
			sort(nums,pos+1,right);
		}
	}
	public static int partition(int[] nums,int left,int right){
		int base = nums[left];
		int leftPoint = left;
		int rightPoint = right;
		while(leftPoint<rightPoint){
			while(leftPoint<rightPoint&&nums[rightPoint]>=base)
				rightPoint--;
			if(leftPoint<rightPoint){
				nums[leftPoint++] = nums[rightPoint];
			}
			while(leftPoint<rightPoint&&nums[leftPoint]<=base){
				leftPoint++;
			}
			if(leftPoint<rightPoint){
				nums[rightPoint--] = nums[leftPoint];
			}
		}
		nums[leftPoint] = base;
		return leftPoint;
	}
	public static void main(String[] args) {
		int arr[] = {1,5,3,32,233,4,1,45,98,2,45};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

	}

}
