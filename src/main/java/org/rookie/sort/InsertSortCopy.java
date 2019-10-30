package org.rookie.sort;

import java.util.Arrays;

public class InsertSortCopy {
	public static void sort(int[] nums){
		int cur;
		for(int i=1;i<nums.length;i++){
			cur = nums[i];
			for(int j=0;j<=i-1;j++){
				if(cur<nums[j]){
					for(int k=i-1;k>=j;k--){
						nums[k+1] = nums[k];
					}
					nums[j] = cur;
					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		int[] nums = {2,43,14,5,1,32};
		InsertSortCopy.sort(nums);
		System.out.println(Arrays.toString(nums));

	}

}
