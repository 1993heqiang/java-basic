package org.rookie.exercise;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DoubleBalls {


	public static void main(String[] args) {
		String[] red = red();//摇奖，抽出红球
		String []bule = bule();//摇奖，抽出蓝球
		//System.out.println("本次开奖结果为：红球"+Arrays.toString(red));//输出开奖结果
		//System.out.println("本次开奖结果为：篮球"+Arrays.toString(bule));//输出开奖结果
		
		int result = 0;//记录红球对的个数
		int result1 = 0;//判断篮球是否正确；
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入开奖结果：(红球)");
		String []radInput=new String[6];//记录用户购买的红球号码
		String []buleInput=new String[1];//记录用户购买的蓝球号码
		for(int i =0;i<radInput.length;i++){//用户输入购买的红球号码
			String input = scanner.next();
			radInput[i]=input;
		}
		System.out.println("请输入开奖结果：(篮球)");
		String input = scanner.next();
		
		buleInput[0]=input;//用户输入购买的蓝球号码
		
		result = check(red,radInput);//用户购买的红球号码跟摇奖结果进行对比
		result1 = check(bule,buleInput);//用户购买的蓝球号码跟摇奖结果进行对比
		if(result+result1==7){//选6+1中6+1，一等奖
			System.out.println("恭喜您中了500万！");
			
		}else if(result==6&&result1==0){//选6+1中6+0，二等奖
			System.out.println("恭喜您中了二等奖!");
			
		}else if(result==5&&result1==1){//选选6+1中5+1，三等奖
			System.out.println("恭喜您中了三等奖!奖金是3000元！");
		}else if(result+result1==5){//选6+1中5+0或中4+1，四等奖
			System.out.println("恭喜您中了四等奖!奖金是200元！");
		}else if(result+result1==4){//选6+1中4+0或中3+1,五等奖
			System.out.println("恭喜您中了五等奖!奖金是10元！");
		}else if(result<=2&&result1==1){//选6+1中2+1或中1+1或中0+1，六等奖
			System.out.println("恭喜您中了六等奖!奖金是5元！");
		}else{
			System.out.println("对不起，您没有中奖！谢谢支持");
		}
		System.out.println("本次开奖结果为：红球"+Arrays.toString(red));//公布开奖结果
		System.out.println("本次开奖结果为：篮球"+Arrays.toString(bule));//公布开奖结果
		
	}
	public static int check(String[] chs, String[] input) {
		int result =0;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < chs.length; j++) {
				if (input[i].equals(chs[j])) {// 判断用户号码跟摇奖号码是否一样
					result++;//记录对的个数
					break;
				}
			}
		}
		return result;
	}
 	public static String[] red() {
		String [] balls = {"01","02","03","04","05","06","07","08",
				"09","10","11","12","13","14","15","16",
				"17","18","19","20","21","22","23","24",
				"25","26","27","28","29","30","31","32","33"};
		boolean []flags = new boolean[balls.length];
		
		String []radBall = new String[6];
		for (int i = 0; i < radBall.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * (balls.length));//随机抽取红球
			} while (flags[index]);// 判断摇出的数是否重复
			radBall[i] = balls[index];
			flags[index] = true;
		}
					
		
		return radBall;
	}
	public static String[] bule() {
		String [] balls = {"01","02","03","04","05","06","07","08",
				"09","10","11","12","13","14","15","16",};
		Random bule = new Random();
		String []buleBall = new String[1];
		int index=bule.nextInt(balls.length);
		buleBall[0] = balls[index];
		
		return buleBall;
	}

}
