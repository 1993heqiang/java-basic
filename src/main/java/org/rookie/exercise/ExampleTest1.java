package org.rookie.exercise;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


public class ExampleTest1 {
	/*
	 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长
	 * 到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
	 */

	public void Demo1(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入月份：");
		int input=sc.nextInt();
		sc.close();
		int a=1;
		int b=1;
		int temp=0;
		if(input>=1){
			System.out.println("第1个月的兔子总数为：1");
		} 
		if(input>=2){
			System.out.println("第2个月的兔子总数为：1");
		}
		if(input>=3){
			for(int i=0;i<input-2;i++){
				temp=a+b;
				a=b;
				b=temp;
				System.out.println("第"+(i+3)+"个月的兔子总数为："+temp);
			}
		}
	}
	/*
	 * 判断101-200之间有多少个素数，并输出所有素数。 
	 * 程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
	 * 则表明此数不是素数，反之是素数。
	 */
//	@Test
	public void Demo2(){
		int count=0;
		for(int i=101;i<=200;i++){
			boolean flag=true;
			for(int j=2;j<Math.sqrt(i);j++){
				if(i%j==0){
					flag=false;
					break;
				}
			}
			if(flag){
				count++;
			}
		}
		System.out.println("101~200之间质数的个数为："+count);
	}
	/*
	 * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字
	 * 立方和等于该数本身。例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的
	 * 三次方＋3的三次方。
	 */
//	@Test
	public void Demo3(){
		int a,b,c;
		for(int i=100;i<999;i++){
			a=i/100;	//百位
			b=i%10;		//个位
			c=i%100/10;	//十位
			if(i==a*a*a+b*b*b+c*c*c){
				System.out.print(i+"  ");
			}
		}
	}
	/*
	 * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 *    
	 * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：   
	 * 	(1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。   
	 * 	(2)如果n > k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的
	 * 		正整数n,重复执行第一步。   
	 * 	(3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
	 */
//	@Test
	public void Demo4(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		int n=sc.nextInt();
		sc.close();
		System.out.print(n+"=");
		int k=2;
		if(n==k){
			System.out.println(n+"=2");
		}
		while(n!=k){
			if(n>k){
				
				if(n%k==0){
					System.out.print(k+"*");
					n=n/k;
				}else{
					k=k+1;
				}
			}
		}
		System.out.print(n);
		
	}
	/*
	 * 题目：利用条件运算符的嵌套来完成此题：学习成绩> =90分的同学用A表示，
	 * 60-89分之间的用B表示，60分以下的用C表示。   
	 */
//	@Test
	public void Demo5(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入成绩：");
		int input=sc.nextInt();
		sc.close();
		System.out.println(input>=90?'A':input>=60?'B':'C');
	}
	/*		********重点*******
	 * 题目：输入两个正整数m和n，求其最大公约数和最小公倍数。   
	 * 在循环中，只要除数不等于0，用较大数除以较小的数，将小的一个数作为下一轮循
	 * 环的大数，取得的余数作为下一轮循环的较小的数，如此循环直到较小的数的值为0，
	 * 返回较大的数，此数即为最大公约数，最小公倍数为两数之积除以最大公约数。
	 */
//	@Test
	public void Demo6(){
		int  a ,b,m;
		Scanner s = new Scanner(System.in);
		System.out.print( "键入一个整数： "); 
		a = s.nextInt();
		System.out.print( "再键入一个整数： "); 
		b = s.nextInt();
		s.close();
		      Demo6 cd = new Demo6();
		      m = cd.deff(a,b);
		      int n = a * b / m;
		      System.out.println("最大公约数: " + m);
		      System.out.println("最小公倍数: " + n);	
	}
	/*
	 * 题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
	 */
//	@Test
	public void Demo7(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入字符串：");
		String input=sc.nextLine();
		sc.close();
		char[] arr=input.toUpperCase().toCharArray();
		int digital = 0;
		int letter = 0;
		int other = 0;
		int blank = 0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>='A'&&arr[i]<='Z'){
				letter++;
			}else if(arr[i]>='0'&&arr[i]<='9'){
				digital++;
			}else if(arr[i]==' '){
				blank++;
			}else{
				other++;
			}
		}
		System.out.println("字母的个数："+letter);
		System.out.println("数字的个数："+digital);
		System.out.println("空格的个数："+blank);
		System.out.println("其它："+other);
	}
	/*
	 * 题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。例如
	 * 2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
	 */
//	@Test	
	public void Demo8(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入1~9之间的任意一个数：");
		int num=sc.nextInt();
		System.out.println("请输入要生成的项数：");
		int times=sc.nextInt();
		sc.close();
		int sum=0;
		int temp=num;
		String str="";
		for(int i=0;i<times-1;i++){
			str+=temp+"+";
			temp=temp*10+num;
			sum=sum+temp;
		}
		str+=temp;
		System.out.println(sum+"="+str);
	}
	/*
	 * 题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.
	 * 编程     找出1000以内的所有完数。 
	 */
//	@Test
	public void Demo9(){
		System.out.println("1000以内的完数有：");
		for(int i=1;i<1000;i++){
			int t=0;
			for(int j=1;j<=i/2;j++){
				if(i%j==0){
					t+=j;
				}
			}
			if(t==i){
				System.out.print(i+" ");
			}
		}
	}
	/*
	 * 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求
	 * 它在第10次落地时，共经过多少米？第10次反弹多高？
	 */
//	@Test
	public void Demo10(){
		double h=100;
		double s=0;
		for(int i=0;i<10;i++){
			s+=1.5*h;
			h*=0.5;
		}
		System.out.println("经过路程："+s);
		System.out.println("反弹高度："+h);
	}
	/*
	 * 题目：有1、2、3、4四个数字，能组成多少个互不相同且无重复数字的三位数？
	 * 都是多少？
	 */
//	@Test
	public void Demo11(){
		
//		 int count = 0;
//	     for(int x=1; x<5; x++) {
//	      for(int y=1; y<5; y++) {
//	       for(int z=1; z<5; z++) {
//	        if(x != y && y != z && x != z) {
//	         count ++;
//	         System.out.println(x*100 + y*10 + z );
//	        }
//	       }
//	      }
//	     }
//	     System.out.println("共有" + count + "个三位数");

		List<Integer> list=new ArrayList<Integer>();
		for(int i=1;i<5;i++){
			list.add(i);
		}
		int a,b,c,count=0;
		for(int i=0;i<4;i++){
			a=list.remove(i);
			for(int j=0;j<3;j++){
				b=list.remove(j);
				for(int k=0;k<2;k++){
					c=list.remove(k);
					count++;
					System.out.println(a*100+b*10+c);
					list.add(k, c);
				}
				list.add(j,b);
			}
			list.add(i, a);
		}
		System.out.println("共有"+count+"个三位数");
	}
	/*
	 * 题目：企业发放的奖金根据利润提成。利润(I)低于或等于10万元时，奖金可提10%；
	 * 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部
	 * 分，可可提成7.5%；20万到40万之间时，高于20万元的部分，可提成5%；40万到
	 * 60万之间时高于40万元的部分，可提成3%；60万到100万之间时，高于60万元的
	 * 部分，可提成1.5%，高于100万元时，超过100万元的部分按1%提成，从键盘输入
	 * 当月利润，求应发放奖金总数？ 
	 */
//	@Test
	public void Demo12(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入利润：(单位：万)");
		double x=sc.nextDouble();
		sc.close();
		double y=0;
		 if(x > 0 && x <= 10) {
		     y = x * 0.1;
		  } else if(x > 10 && x <= 20) {
		      y = 10 * 0.1 + (x - 10) * 0.075;
		  } else if(x > 20 && x <= 40) {
		      y = 10 * 0.1 + 10 * 0.075 + (x - 20) * 0.05;
		  } else if(x > 40 && x <= 60) {
		      y = 10 * 0.1 + 10 * 0.075 + 20 * 0.05 + (x - 40) * 0.03;
		  } else if(x > 60 && x <= 100) {
		      y = 10 * 0.175 + 20 * 0.05 + 20 * 0.03 + (x - 60) * 0.015; 
		  } else if(x > 100) {
		      y = 10 * 0.175 + 20 * 0.08 + 40 * 0.015 + (x - 100) * 0.01;
		  }
		    System.out.println("应发奖金数为:"+y*10000);
	
	}
	/*
	 * 题目：一个整数（整数包括正整数、负整数、零），它加上100后是一个完全平方数，再加上168又是一个完全平方数，
	 * 请问该数是多少？  
	 */
//	@Test
	public void Demo13(){
		for(int i=-100;i<10000;i++){
			if(Math.sqrt(i+100)%1==0){
				if(Math.sqrt(i+100+168)%1==0){
					System.out.println(i + "加100是一个完全平方数，再加168又是一个完全平方数");
				}
			}
		}
	}
	/*
	 * 题目：输入某年某月某日，判断这一天是这一年的第几天？
	 */
//	@Test
	public void Demo14(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入日期：(格式：1900年10月1日)");
		String input=sc.nextLine();
		sc.close();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date date=sdf.parse(input);
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			System.out.println(cal.get(Calendar.DAY_OF_YEAR));
		} catch (ParseException e) {
			System.out.println("日期格式不正确");
		}
		
	}
	/*
	 * 题目：输入三个整数x,y,z，请把这三个数由小到大输出。 
	 */
//	@Test
	public void Demo15(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个数：");
		int a=sc.nextInt();
		System.out.println("请输入第二个数：");
		int b=sc.nextInt();
		System.out.println("请输入第三个数：");
		int c=sc.nextInt();
		sc.close();
		int t;
		if(a>b){
			t=a;
			a=b;
			b=t;
		}
		if(b>c){
			t=b;
			b=c;
			c=t;
		}
		if(a>b){
			t=a;
			a=b;
			b=t;
		}
		System.out.println("排序结果为："+a+","+b+","+c);
	}
	/*
	 * 题目：输出9*9口诀。  
	 */
//	@Test
	public void Demo16(){
		for(int i=1;i<=9;i++){
			for(int j=1;j<i+1;j++){
				System.out.print(j+"*"+i+"="+i*j+"  ");
			}
			System.out.println();
		}
	}
	/*
	 * 题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃
	 * 了一个;第二天早上又将剩下的桃子吃掉一半，又多吃了一个;以后每天早上都吃了前
	 * 一天剩下 的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。求第一天
	 * 共摘了多少。  
	 */
//	@Test
	public void Demo17(){
		int num=1;
		for(int i=0;i<9;i++){
			num=(num+1)*2;
		}
		System.out.println("猴子第一天共摘了"+num+"个桃");
	}
	/*		**********重点***********
	 * 题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
	 * 已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,
	 * z比，请编程序找出三队赛手的名单。
	 */
//	@Test
	public void Demo18(){
		char[] m={'a','b','c'};
		char[] n={'x','y','z'};
		
		 for (int i = 0; i < m.length; i++) {
			    for (int j = 0; j < n.length; j++) {
			     if (m[i] == 'a' && n[j] == 'x') {
			      continue;
			} else if (m[i] == 'a' && n[j] == 'y') {
			      continue;
			     } else if ((m[i] == 'c' && n[j] == 'x')
			       || (m[i] == 'c' && n[j] == 'z')) {
			      continue;
			     } else if ((m[i] == 'b' && n[j] == 'z')
			       || (m[i] == 'b' && n[j] == 'y')) {
			      continue;
			     } else
			      System.out.println(m[i] + " vs " + n[j]);
			    }
			   }
	}
	/*
	 * 题目：打印出如下图案（菱形）  
	  
     *   
    ***   
   *****   
  *******   
   *****   
    ***   
     *   
     
	 */
//	@Test
	public void Demo19(){
		for(int i=1;i<=7;i+=2){
			for(int j=0;j<(7-i)/2;j++){
				System.out.print(" ");
			}
			for(int j=0;j<i;j++){
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i=5;i>=1;i-=2){
			for(int j=0;j<(7-i)/2;j++){
				System.out.print(" ");
			}
			for(int j=0;j<i;j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	/*
	 * 题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...
	 * 求出这个数列的前20项之和。 
	 */
//	@Test
	public void Demo20(){
		int fz=2,fm=1,temp;
		double sum=0;
		for(int i=0;i<20;i++){
			sum+=(double)fz/fm;
			temp=fz;
			fz=fz+fm;
			fm=temp;
		}
		System.out.println("前20项之和为："+sum);
	}
	/*
	 * 题目：求1+2!+3!+...+20!的和   
	 */
//	@Test
	public void Demo21(){
		long sum=0,k;
		for(int i=1;i<=20;i++){
			k=1;
			for(int j=1;j<=i;j++){
				k*=j;
			}
			sum+=k;
		}
		System.out.println(sum);
	}
	/*		**********重点**********
	 * 题目：利用递归方法求5!。
	 */
//	@Test
	public void Demo22(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入n值：");
		int n=sc.nextInt();
		sc.close();
		System.out.println(n+"!="+Demo22_1(n));
	}
	public static long Demo22_1(int n){
		long value = 0 ;
	    if(n ==1 ) {
	     value = 1;
	    } else {
	     value = n * Demo22_1(n-1);
	    }
	    return value;
	}
	/*
	 * 题目：有5个人坐在一起，问第五个人多少岁？他说比第4个人大2岁。问第4个人岁
	 * 数，他说比第3个人大2岁。问第三个人，又说比第2人大两岁。问第2个人，说比第
	 * 一个人大两岁。最后问第一个人，他说是10岁。请问第五个人多大？
	 */
//	@Test
	public void Demo23(){
		System.out.println("第五个人的年龄为："+Demo23_1(5)+"岁");
	}
	public static int Demo23_1(int n){
		int value = 0 ;
	    if(n ==1 ) {
	     value = 10;
	    } else {
	     value = 2+Demo23_1(n-1);
	    }
	    return value;
	}
	/*
	 * 题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位
	 * 数字。
	 */
//	@Test
	public void Demo24(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		int input=sc.nextInt();
		sc.close();
		char[] arr=new char[(input+"").length()];
		System.out.println(input+"是"+arr.length+"位数");
		for(int i=arr.length;i>=1;i--){
			System.out.print((input+"").charAt(i-1)+",");
		}
	}
	/*
	 * 题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，
	 * 十位与千位相同。
	 */
//	@Test
	public void Demo25(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个5位数：");
		int input=sc.nextInt();
		sc.close();
		String str=input+"";
		StringBuilder str1=new StringBuilder(str);
		String str2=str1.reverse().toString();
		System.out.println(input+((str2.equals(str))?"是回文":"不是回文"));
	}
	/*
	 * 题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则
	 * 继续判断第二个字母。
	 */
//	@Test
	public void Demo26(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个字母：");
		String letter1=sc.nextLine();
		
		letter1=letter1.trim().toUpperCase();
	
		if(letter1.equals("M")){
			System.out.println("星期一：Monday");
		}else if(letter1.equals("T")){
			System.out.println("请输入第二个字母：");
			String letter2=sc.nextLine();
			letter2=letter2.trim().toUpperCase();
			if(letter2.equals("u")){
				System.out.println("星期二：Tuesday");
			}else{
				System.out.println("星期四：Thursday");
			}
		}else if(letter1.equals("W")){
			System.out.println("星期三：Wednesday");
		}else if(letter1.equals("F")){
			System.out.println("星期五：Friday");
		}else if(letter1.equals("S")){
			System.out.println("请输入第二个字母：");
			String letter2=sc.nextLine();
			letter2=letter2.trim().toUpperCase();
			if(letter2.equals("u")){
				System.out.println("星期天：Sunday");
			}else{
				System.out.println("星期六：Saturday");
			}
		}else{
			System.out.println("您输入的字母有误！");
		}
		sc.close();
	}
	/*
	 * 题目：求100之内的素数 
	 */
//	@Test
	public void Demo27(){
		System.out.print("100之内的素数有：2，3，");
		for(int i=4;i<=100;i++){
			boolean flag=false;
			for(int j=2;j<=Math.sqrt(i);j++){
				if(i%j==0){
					flag=true;
					break;
				}
			}
			if(!flag){
				System.out.print(i+"，");
			}
		}
	}
	/*
	 * 题目：对10个数进行排序 
	 */
//	@Test
	public void Demo28(){
		int[] arr=new int[10];
		
		for(int i=0;i<10;i++){
			arr[i]=(int)(Math.random()*100+1);
		}
		System.out.println("原数组为："+Arrays.toString(arr));
		int temp;
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-i-1;j++){
				if(arr[j]>arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
	
		System.out.println("排序后的数组为："+Arrays.toString(arr));
	
	}
	/*
	 * 题目：求一个3*3矩阵对角线元素之和
	 */
//	@Test
	public void Demo29(){
		Scanner sc=new Scanner(System.in);
		double sum=0;
		for(int i=1;i<=3;i++){
			System.out.println("请输入第"+i+"行的元素:(元素之间以空格隔开)");
			String input=sc.nextLine();
			String[] row=input.trim().split("\\s+");
			for(int j=0;j<3;j++){
				if(j==(i-1)){
					Double a=Double.parseDouble(row[j]);
					sum+=a;
				}
			}
		}
		sc.close();
		System.out.println("3*3矩阵对角线元素之和:"+sum);
	}
	/*
	 * 题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组
	 * 中。
	 */
//	@Test
	public void Demo30(){
		int[] arr=new int[10];
		
		for(int i=0;i<10;i++){
			arr[i]=(int)(Math.random()*100+1);
		}
		System.out.println("原数组为："+Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("排序后的数组为："+Arrays.toString(arr));
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入要插入的数字：");
		int input=sc.nextInt();
		sc.close();
		for(int i=0;i<arr.length-1;i++){
			if(input>=arr[i]&&input<=arr[i+1]){
				int[] newArray=Arrays.copyOf(arr, arr.length+1);
				for(int j=newArray.length-1;j>(i+1);j--){
					newArray[j]=newArray[j-1];
				}
				newArray[i+1]=input;
				System.out.print("插入元素后的数组为："+Arrays.toString(newArray));
				
				break;
			}
		}
		
	}
	/*
	 * 题目：将一个数组逆序输出。 
	 */
//	@Test
	public void Demo31(){
		int[] arr=new int[10];
		
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*100+1);
		}
		System.out.println("原数组为："+Arrays.toString(arr));
		int temp;
		for(int i=0;i<arr.length/2;i++){
			temp=arr[arr.length-1-i];
			arr[arr.length-1-i]=arr[i];
			arr[i]=temp;
		}
		System.out.println("变换后的数组为："+Arrays.toString(arr));
	}
	/*
	 * 题目：取一个整数a从右端开始的4～7位。
	 */
//	@Test
	public void Demo32(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个位数大于7的整数：");
		long input=sc.nextLong();
		sc.close();
		String str=Long.toString(input);
		str=str.substring(str.length()-7,str.length()-3 );
		System.out.println("截取从右端开始的4～7位是："+str);
	}
	/*		*******重点******
	 * 题目：打印出杨辉三角形（要求打印出10行如下图） 
	 */
//	@Test
	public void Demo33(){
		 int[][] a = new int[10][10];
		   for(int i=0; i<10; i++) {
		    a[i][i] = 1;
		    a[i][0] = 1;
		   }
		   for(int i=2; i<10; i++) {
		    for(int j=1; j<i; j++) {
		     a[i][j] = a[i-1][j-1] + a[i-1][j];
		    }
		   }
		    for(int i=0; i<10; i++) {
		    	for(int k=0; k<2*(10-i)-1; k++) {
		    		System.out.print(" ");
		    	}
		    	for(int j=0; j<=i; j++) {
		    		System.out.print(a[i][j] + "    ");
		    	}
		    	System.out.println();
		   }

	}
	/*
	 * 题目：输入3个数a,b,c，按大小顺序输出。
	 */
//	@Test
	public void Demo34(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个数：");
		int a=sc.nextInt();
		System.out.println("请输入第二个数：");
		int b=sc.nextInt();
		System.out.println("请输入第三个数：");
		int c=sc.nextInt();
		sc.close();
		int t;
		if(a>b){
			t=a;
			a=b;
			b=t;
		}
		if(b>c){
			t=b;
			b=c;
			c=t;
		}
		if(a>b){
			t=a;
			a=b;
			b=t;
		}
		System.out.println("排序结果为："+a+","+b+","+c);
	}
	/*
	 * 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，
	 * 输出数组。 
	 */
//	@Test
	public void Demo35(){
		int[] arr=new int[10];
		
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*100+1);
		}
		System.out.println("原数组为："+Arrays.toString(arr));
		int max=arr[0],min=arr[0],maxi=0,mini=0;
		for(int i=1;i<arr.length;i++){
			if(arr[i]>max){
				max=arr[i];
				maxi=i;
			}
			if(arr[i]<min){
				min=arr[i];
				mini=i;
			}
		}
		System.out.println("数组中最大的元素为："+max);
		System.out.println("数组中最小的元素为："+min);
		int temp=arr[maxi];
		arr[maxi]=arr[0];
		arr[0]=temp;
		temp=arr[mini];
		arr[mini]=arr[arr.length-1];
		arr[arr.length-1]=temp;
		System.out.println("变换后的数组为："+Arrays.toString(arr));
	}
	/*
	 * 题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的
	 * m个数
	 */
//	@Test
	public void Demo36(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入n：");
		int n=sc.nextInt();
		System.out.println("请输入m");
		int m=sc.nextInt();
		sc.close();
		int[] arr=new int[n];
		
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*100+1);
		}
		
		System.out.println("原数组为："+Arrays.toString(arr));
		int[] sub1=Arrays.copyOfRange(arr,arr.length-m, arr.length);
 		int[] sub2=Arrays.copyOfRange(arr, 0, arr.length-m);
 	
 		System.arraycopy(sub1, 0, arr, 0, sub1.length);
 		System.arraycopy(sub2, 0, arr, sub1.length, sub2.length);
 		System.out.println("变换后的数组为："+Arrays.toString(arr));
	}
	/*
	 * 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报
	 * 到3的人退出圈子，问最后留下的是原来第几号的那位。
	 */
//	@Test
	public void Demo37(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入人数n：");
		int n=sc.nextInt();
		System.out.println("请输入第m个人退出：");
		int m=sc.nextInt();
		sc.close();
		int[] arr=new int[n];
		for(int i=1;i<=n;i++){
			arr[i-1]=i;
		}
		System.out.println("原数组为："+Arrays.toString(arr));
		
		int i=0,num=0,remove=0;

		while(remove!=n-1){
			while(true){
				if(arr[i%n]!=0){	
					num++;
					if(num%m==0){
						arr[i%n]=0;
						remove++;
						System.out.println(Arrays.toString(arr));
						break;
					}	
				}
				i++;
			}
			
		}
	}
	/*
	 * 题目：写一个函数，求一个字符串的长度，在main函数中输入字符串，并输出其长度。
	 */
//	@Test
	public void Demo38(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String input=sc.nextLine();
		sc.close();
		System.out.println("字符串的长度："+Strlenght(input));
	}
	public static int Strlenght(String str){
		return str.length();
	}
	/*
	 * 题目：编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,当输入
	 * n为奇数时，调用函数1/1+1/3+...+1/n(利用指针函数) 
	 */
//	@Test
	public void Demo39(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数n：");
		int n=sc.nextInt();
		sc.close();
		System.out.println("和为："+sum(n));
	}
	public static double sum(int n){
		double sum = 0;
		if(n%2==0){
			for(int i=2;i<=n;i+=2){
				sum+=1.0/i;
			}
		}else{
			for(int i=1;i<=n;i+=2){
				sum+=1.0/i;
			}
		}
		return sum;
	}
	/*		*******重点：关于比较方法的重写*****
	 * 题目：字符串排序。 
	 */
//	@Test
	public void Demo40(){
		String[] s=new String[5];
		s[0] = "matter";
		s[1] = "state";
		s[2] = "solid";
		s[3] = "liquid";
		s[4] = "gas";
		Arrays.sort(s);
		System.out.println(Arrays.toString(s));
		
	}
	/*		*******重点*******
	 * 题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子平均分为五份，
	 * 多了一个，这只猴子把多的一个扔入海中，拿走了一份。第二只猴子把剩下的桃子
	 * 又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，第三、第
	 * 四、第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？
	 */
//	@Test
	public void Demo41(){
		int i,m,j=0,k,count; 
		for(i=4;i<10000;i+=4) 
		   { count=0; 
		     m=i; 
		     for(k=0;k<5;k++) 
		        { 
		         j=i/4*5+1; 
		         i=j; 
		         if(j%4==0) count++;
		            else break;   
		       } 
		    i=m; 
		if(count==4) 
		{System.out.println("原有桃子 "+j+" 个"); 
		break;} 
		} 

	}
	/*
	 * 题目：809*??=800*??+9*??  其中??代表的两位数,8*??的结果为两位数，
	 * 9*??的结果为3位数。求??代表的两位数，及809*??后的结果。
	 */
//	@Test
	public void Demo42(){
		for(int i=10;i<100;i++){
			if(8*i<100&&9*i>=100&&809*i==800*i+9*i){
				System.out.println("??:"+i);
				System.out.println("809*??:"+i*809);
			}
		}
	}
	/*
	 * 题目：求0—7所能组成的奇数个数。   
	 * //组成1位数是4个。
	 * //组成2位数是7*4个。
	 * //组成3位数是7*8*4个。
	 * //组成4位数是7*8*8*4个。
	 * //......
	 */
//	@Test
	public void Demo43(){
		int num=0;
		for(int i=1;i<=8;i++){
			if(i==1){
				num+=4;
				System.out.println("组成"+i+"位数是"+num+"个");
			}else{
				int temp=1;
				for(int j=0;j<(i-2);j++){
					temp*=8;
				}
				System.out.println("组成"+i+"位数是"+4*7*temp+"个");
				num+=4*7*temp;
			}
		}
	}
	/*
	 * 题目：一个偶数总能表示为两个素数之和。   
	 * 由于用除sqrt(n)的方法求出的素数不包括2和3，
	 * 因此在判断是否是素数程序中人为添加了一个3。
	 */
//	@Test
	public void Demo44(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个大于6的偶数：");
		int n=sc.nextInt();
		sc.close();
		List<Integer> list=prime(n);
		for(int i=0;i<list.size();i++){
			if(list.contains((n-list.get(i)))){
				System.out.println(n+"="+list.get(i)+"+"+(n-list.get(i)));
				break;
			}
		}
	}
	public static List<Integer> prime(int n){
		List<Integer> list=new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		for(int i=4;i<=n;i++){
			boolean flag=true;
			for(int j=2;j<=Math.sqrt(i);j++){
				if(i%j==0){
					flag=false;
					break;
				}
			}
			if(flag){
				list.add(i);
			}
		}
		return list;
	}
	/*
	 * 题目：判断一个整数能被几个9整除 
	 */
//	@Test
	public void Demo45(){
		
	}
	/*
	 * 题目：两个字符串连接程序 
	 */
//	@Test
	public void Demo46(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个字符串");
		String str1=sc.nextLine();
		System.out.println("请输入第二个字符串");
		String str2=sc.nextLine();
		sc.close();
		StringBuilder sb1=new StringBuilder(str1);
		String str=sb1.append(str2).toString();
		System.out.println("拼接后的字符串："+str);
	}
	/*
	 * 题目：读取7个数（1—50）的整数值，每读取一个值，程序打印出该值个数的＊。
	 */
//	@Test
	public void Demo47(){
		int[] arr=new int[7];
		
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*50+1);
		}
		System.out.println("生成的7个数为"+Arrays.toString(arr));
		for(int i=0;i<7;i++){
			for(int j=0;j<arr[i];j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	/*
	 * 题目：某个公司采用公用电话传递数据，数据是四位的整数，在传递过程中是加密
	 * 的，加密规则如下：每位数字都加上5,然后用和除以10的余数代替该数字，再将
	 * 第一位和第四位交换，第二位和第三位交换。 
	 */
//	@Test
	public void Demo48(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个四位整数：");
		int n=sc.nextInt();
		sc.close();
		String str=Integer.toString(n);
		String newStr="";
		for(int i=0;i<str.length();i++){
			newStr+=(Integer.parseInt(str.charAt(i)+"")+5)%10+"";
		}
		StringBuilder sb=new StringBuilder(newStr);
		
		System.out.println(sb.reverse());
	}
	/*
	 *	题目：计算字符串中子符出现的次数 
	 */
//	@Test
	public void Demo49(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入字符串：");
		String input=sc.nextLine();
		sc.close();
		String[] arr=new String[input.length()];
		for(int i=0;i<arr.length;i++){
			arr[i]=input.charAt(i)+"";
		}
		LinkedHashMap<String,Integer> map=new LinkedHashMap<String,Integer>();
		for(int i=0;i<arr.length;i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i],map.get(arr[i])+1);
			}else{
				map.put(arr[i], 1);
			}
		}
		Set<Entry<String, Integer>> entrySet=map.entrySet();
		for(Entry<String, Integer> e:entrySet){
			System.out.println(e.getKey()+":"+e.getValue());
		}
		
	}
	/*
	 * 题目：有五个学生，每个学生有3门课的成绩，从键盘输入以上数据（包括学生号，
	 * 姓名，三门课成绩），计算出平均成绩，把原有的数据和计算出的平均分数存放在
	 * 磁盘文件 "stud "中。
	 */
//	@Test
	public void Demo50() throws FileNotFoundException{
		Scanner sc=new Scanner(System.in);
		
		FileOutputStream fis=new FileOutputStream("strd.txt");
		OutputStreamWriter osw=new OutputStreamWriter(fis);
		PrintWriter pw=new PrintWriter(osw);
		
		for(int i=0;i<5;i++){
			System.out.println("请依次输入学生的学号、姓名、三门课的成绩：");
			String studentNum=sc.next();
			String name=sc.next();
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			double average=(a+b+c)/3;
			pw.print("学号："+studentNum+"  ");
			pw.print("姓名："+name+"  ");
			pw.print("语文："+a+"  ");
			pw.print("数学："+b+"  ");
			pw.print("英语"+c+"  ");
			pw.println("平均成绩"+average+"  ");
			pw.flush();
			
		}
		pw.close();
		
	}
}

class Demo6{
	
	public int deff(int x, int y) {
		int t;
		if(x < y) {
			t = x;
			x = y;
			y = t;
		}  
		while(y != 0) {
			if(x == y) return x;
			else {
				int k = x % y;
				x = y;
				y = k;
			}
		}
		return x;
	}
} 
