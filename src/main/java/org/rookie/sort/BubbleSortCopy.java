package org.rookie.sort;

import java.util.Arrays;

public class BubbleSortCopy {
	public static void sort(int[] nums){
		int temp;
		int len = nums.length;
		for(int i=0;i<len-1;i++){
			for(int j=0;j<len-i-1;j++){
				if(nums[j]>nums[j+1]){
					temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
	}
	public static void main(String[] args) {
		int[] nums = {2,43,14,5,1,32};
		BubbleSortCopy.sort(nums);
		System.out.println(Arrays.toString(nums));

	}

}
