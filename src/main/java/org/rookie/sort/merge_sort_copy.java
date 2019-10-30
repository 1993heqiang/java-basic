package org.rookie.sort;

import java.util.Arrays;

public class merge_sort_copy {
	public static void sort(int[] nums,int left,int right){
		int mid = (right+left)/2;
		if(left<right){
			sort(nums,left,mid);
			sort(nums,mid+1,right);
			merge(nums,left,mid,right);
		}
	}
	public static void merge(int[] nums,int left,int mid,int right){
		int[] temp = new int[right-left+1];
		int i = left;
		int j = mid+1;
		int k = 0;
		
		while(i<=mid&&j<=right){
			if(nums[i]<nums[j]){
				temp[k++] = nums[i++];
			}else{
				temp[k++] = nums[j++];
			}
		}
		while(i<=mid){
			temp[k++] = nums[i++];
		}
		while(j<=right){
			temp[k++] = nums[j++];
		}
		for(k=0;k<temp.length;k++){
			nums[left++] = temp[k];
		}
	}
	public static void main(String[] args) {
		int[] nums = {2,43,14,5,1,32};
		merge_sort_copy.sort(nums, 0, nums.length-1);
		System.out.println(Arrays.toString(nums));
	}

}
