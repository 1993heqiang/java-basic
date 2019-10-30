package org.rookie.exercise;

import java.util.Scanner;

public class heqiang {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		heqiang he = new heqiang();
		for(int g=1;g<input/2;g++){
			String[] arr = he.split1(g, input).split(",");
		//	System.out.println(Arrays.toString(arr));
			for(int i=0;i<arr.length-1;i++){
				int nub = 0;
				String str = "";
				for(int k=0;k<=i;k++){
					str += arr[k]+"+";
				}
				for(int j=i+1;j<arr.length;j++){
					nub += Integer.parseInt(arr[j]);
				}
				System.out.println(input+"="+str+nub);
			}
		}
		
	}
	public String split1(int i,int j){
		
		if(i<j-i){
			return i+","+split1(i+1,j-i);
		}else{
			return j+"";
		}
		
	}
}
