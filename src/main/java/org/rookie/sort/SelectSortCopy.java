package org.rookie.sort;

import java.util.Arrays;

public class SelectSortCopy {
	public static void sort(int[] nums){
		int temp,minIndex;
		for(int i=0;i<nums.length-1;i++){
			minIndex = i;
			for(int j=i+1;j<nums.length;j++){
				if(nums[j]<nums[minIndex]){
					minIndex = j;
				}
			}
			temp = nums[i];
			nums[i] = nums[minIndex];
			nums[minIndex] = temp;
		}
	}
	public static void main(String[] args) {
		int arr[] = {1,5,3,32,2,4,1,45,5,2,45};
		SelectSortCopy.sort(arr);
        System.out.println(Arrays.toString(arr));

	}

}
