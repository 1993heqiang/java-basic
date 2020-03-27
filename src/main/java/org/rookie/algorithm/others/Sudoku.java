package org.rookie.algorithm.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 未完成
 * @author Administrator
 *
 */
public class Sudoku {
	static long kinds=1;
	static int total_time = 0;
	public static void main(String[] args) {
		String[] arr = {"8........","..36.....",".7..9.2..",".5...7...","....457..","...1...3.","..1....68","..85...1.",".9....4.."};
		char[][] Arr = new char[9][9]; 
		for(int i=0;i<Arr.length;i++){
			Arr[i] = arr[i].toCharArray();
		}
		int count = 0;
		for(int i=0;i<Arr.length;i++){
			for(int j=0;j<Arr[i].length;j++){
				if(Arr[i][j]!='.'){
					 count++;
					 if(!valid(Arr,i,j)) System.out.println("数独无效");
				}
			}
		}
		if(count==Arr.length*Arr.length){
			System.out.println("数独正确");
		}
		//打印数独数组
		for(int i=0;i<Arr.length;i++){
			System.out.println(Arrays.toString(Arr[i]));
		}
		//完成数独的代码
		String[][] newArr = new String[9][9];
		for(int i=0;i<Arr.length;i++){
			for(int j=0;j<Arr[i].length;j++){
				if(Arr[i][j]=='.'){
				//System.out.println("("+i+","+j+")");
					//计算出每个空白格子能填的所有值
					newArr[i][j] = values(Arr,i,j);
				}
			}
		}
		System.out.println(kinds);
		System.out.println(total_time);
		
		for(int i=0;i<newArr.length;i++){
			System.out.println(Arrays.toString(newArr[i]));
		}
		
	}
	public static String values(char[][] arr,int row,int col){
		Set<Character> temp = new HashSet<Character>();
		for(int i=0;i<arr.length;i++){
			if(arr[row][i]!='.') temp.add(arr[row][i]);
			if(arr[i][col]!='.') temp.add(arr[i][col]);
		}
		//只针对9X9的数独，则每个小方格的中心点坐标为：(1,1),(1,4),(1,7),(4,1),(4,4),(4,7),(7,1),(7,4),(7,7)
		int row_center = 1,col_center = 1;
		double min = 49;
		//找出空白处所处小方格的中心点
		for(int i=1;i<=7;i+=3){
			for(int j=1;j<=7;j+=3){
				double temp1 = Math.pow(row-i, 2)+Math.pow(col-j, 2);
				if(temp1<min){
					min = temp1;
					row_center = i;
					col_center = j;
				}
			}
		}
	//	System.out.println("已知点的坐标：("+row+","+col+"),所在小方格的中心坐标：("+row_center+","+col_center+");");
		//排除小方格中的其他值
		for(int i=row_center-1;i<=row_center+1;i++){
			for(int j=col_center-1;j<=col_center+1;j++){
				if(!(i==row||j==col)&&arr[i][j]!='.')  {
					temp.add(arr[i][j]);
				}
			}
		}
		String ret = "";
		for(char i='1';i<='9';i++){
			if(!temp.contains(i)) ret+=i;
		}
		kinds*=temp.size();
		return ret;
	}
	//有效性验证
	public static boolean valid(char[][] arr,int row,int col){
		long t1 = System.nanoTime();
		for(int i=0;i<arr.length;i++){
			if(i!=col&&arr[row][i]!='.'&&arr[row][i]==arr[row][col]) return false;
			if(i!=row&&arr[i][col]!='.'&&arr[i][col]==arr[row][col]) return false;
		}
		int row_center = 1,col_center = 1;
		double min = 49;
		for(int i=1;i<=7;i+=3){
			for(int j=1;j<=7;j+=3){
				double temp1 = Math.pow(row-i, 2)+Math.pow(col-j, 2);
				if(temp1<min){
					min = temp1;
					row_center = i;
					col_center = j;
				}
			}
		}
		for(int i=row_center-1;i<=row_center+1;i++){
			for(int j=col_center-1;j<=col_center+1;j++){
				if(i!=row&&j!=col&&arr[i][j]!='.'&&arr[i][j]==arr[row][col]) return false;
			}
		}
		long t2 = System.nanoTime();
		total_time+=(t2-t1);
		return true;
	}
	
}
