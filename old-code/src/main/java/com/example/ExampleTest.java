package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ExampleTest {
	//求  a+aa+aaa+.......+aaaaaaaaa=?其中a为1至9之中的一个数，项数也要可以指定。
	public void  Demo01(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入1~9之间的任意一个数：");
		int num=sc.nextInt();
		System.out.println("请输入要生成的项数：");
		int times=sc.nextInt();
		sc.close();
		int sum=0;
		int temp=0;
		for(int i=0;i<times;i++){
			temp=temp*10+num;
			sum=sum+temp;
		}
		System.out.println("结果为："+sum);
	}
	//求 2/1+3/2+5/3+8/5+13/8.....前20项之和？

	public void Demo02(){
		double sum=0;
		double fz=2;
		double fm=1;
		double temp;
		for(int i=0;i<20;i++){
			sum+=fz/fm;
			temp=fz;
			fz=fz+fm;
			fm=temp;
			
		}
		System.out.println("结果为："+sum);
	}
	//重点
 
	public void Demo03(){
		for (int i=1; i<=13; i+=2){
            for(int j=1; j<=i && i+j<= 14; j++){
            	System.out.print("* ");
            	}
            System.out.println();  // 换行
        }
	}
	//计算圆周率
	//  PI＝4－4/3+4/5-4/7.......
	//打印出第一个大于 3.1415小于 3.1416的值

	//@Test
	public void Demo04(){
		double PI = 0;
		int fm=1;
		int temp=1;
		while(!(PI>3.1415&&PI<3.1416)){
			PI+=4.0/fm*temp;
			fm+=2;
			temp*=-1;
		}
		System.out.println("PI="+PI+"   fm="+fm);
	}
	/*
	 * 13、求1-1/3+1/5-1/7+1/9......的值。
           a,求出前50项和值。
           b,求出最后一项绝对值小于1e-5的和值。

	 */
	//@Test
	public void Demo05a(){
		double sum=0;
		int fm=1;
		int temp=1;
		for(int i=0;i<50;i++){
			sum+=1.0/fm*temp;
			fm+=2;
			temp*=-1;
		}
		System.out.println(sum);
	}

	public void Demo05b(){
		double sum=0;
		int fm=1;
		int temp=1;
		while(Math.abs(1.0/fm*temp)>(1e-5)){
			sum+=1.0/fm*temp;
			fm+=2;
			temp*=-1;
		}
		System.out.println(sum+"\t最后一项的绝对值为："+Math.abs(1.0/fm*temp));
	}
	//打印金字塔图案
	//@Test
	public void Demo06(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入要打印的行数：");
		int col=sc.nextInt();
		sc.close();
		for(int i=1;i<=col;i++){
			for(int j=0;j<(col-i);j++){
				System.out.print(" ");
			}
			for(int k=0;k<2*i-1;k++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	/*
	 * 歌德巴赫猜想,任何一个大于六的偶数可以拆分成两个质数的和
             打印出所有的可能
	 */

	public void Demo07(){
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
	 * 定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，
	 * 然后求出所有元素的最大值，最小值，平均值，和值，并输出出来
	 */
	//@Test
	public void Demo08(){
		int sum=0;
		double average;
		int[] arr=new int[10];
		for(int i=0;i<10;i++){
			arr[i]=(int)(Math.random()*99);
			sum+=arr[i];
		}
		average=sum/10.0;
		System.out.println("生成的数组为："+Arrays.toString(arr));
		System.out.println("数组中元素的和为："+sum);
		System.out.println("数组中元素的平均值为："+average);
		int max=arr[0];
		for(int i=1;i<arr.length;i++){
			if(max<arr[i]){
				max=arr[i];
			}
		}
		int min=arr[0];
		for(int i=1;i<arr.length;i++){
			if(min>arr[i]){
				min=arr[i];
			}
		}
		System.out.println("数组中最大的元素为："+max);
		System.out.println("数组中最小的元素为："+min);

		}
	/*
	 * 定义一个int型的一维数组，包含10个元素，分别赋值为1~10，
	 * 然后将数组中的元素都向前移一个位置，
             即，a[0]=a[1],a[1]=a[2],…最后一个元素的值是原来第一个元
             素的值，然后输出这个数组。
	 */

	public void Demo09(){
		int[] arr=new int[10];
		for(int i=0;i<10;i++){
			arr[i]=(int)(Math.random()*10+1);
		}
		System.out.println(Arrays.toString(arr));
		int temp=arr[0];
		for(int i=0;i<arr.length;i++){
			if(i==arr.length-1){
				arr[arr.length-1]=temp;
			}else{
				arr[i]=arr[i+1];
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	/*
	 * 定义一个int型的一维数组，包含40个元素，用来存储每个学员的
	 * 成绩，循环产生40个0~100之间的随机整数，
	     将它们存储到一维数组中，然后统计成绩低于平均分的学员的人数，
	     并输出出来。
	 */

	public void Demo10(){
		int[] scores=new int[40];
		int sum=0;
		double average;
		for(int i=0;i<40;i++){
			scores[i]=(int)(Math.random()*101);
			sum+=scores[i];
		}
		System.out.println("成绩为："+Arrays.toString(scores));
		average=sum/40.0;
		int count=0;
		for(int i=0;i<40;i++){
			if(scores[i]<average){
				count++;
			}
		}
		System.out.println("平均分为："+average);
		System.out.println("成绩低于平均分的有"+count+"人");
	}
	//承上题，将这40个成绩按照从高到低的顺序输出出来。
	//@Test
	public void Demo11(){
		int[] scores=new int[40];
		for(int i=0;i<40;i++){
			scores[i]=(int)(Math.random()*101);
		}
		System.out.println("成绩为："+Arrays.toString(scores));
		int temp;
		for(int i=0;i<40;i++){
			for(int j=0;j<40-i-1;j++){
				if(scores[j]<scores[j+1]){
					temp=scores[j+1];
					scores[j+1]=scores[j];
					scores[j]=temp;
				}
			}
		}
		System.out.println("从高到低的排序为："+Arrays.toString(scores));
	}
	/*
	 * 编写程序，将一个数组中的元素倒排过来。例如原数组为1，2，3，
	 * 4，5；则倒排后数组中的值为5，4，3，2，1。
	 */
	//@Test
	public void Demo12(){
		int[] arr=new int[19];
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*10);
		}
		System.out.println(Arrays.toString(arr));
		int temp;
		for(int i=0;i<arr.length/2;i++){
			temp=arr[arr.length-i-1];
			arr[arr.length-i-1]=arr[i];
			arr[i]=temp;
		}
		System.out.println(Arrays.toString(arr));
	}
	/*
	 * 要求定义一个int型数组a,包含100个元素,保存100个随机的4位数。
	 * 再定义一个int型数组b，包含10个元素。统计a数组中的元素对10
	 * 求余等于0的个数，保存 到b[0]中；对10求余等于1的个数，保存
	 * 到b[1]中，……依此类推。
	 */

	public void Demo13(){
		int[] a=new int[100];
		int[] b=new int[10];
		for(int i=0;i<100;i++){
			a[i]=(int)(Math.random()*9000+1000);
		}
		for(int i=0;i<10;i++){
			for(int j=0;j<100;j++){
				if(a[j]%10==i){
					b[i]++;
				}
			}
		}
		System.out.println(Arrays.toString(b));
	}
	/*
	 * 定义一个20*5的二维数组，用来存储某班级20位学员的5门课的成绩；这5门课
   		按存储顺序依次为：core C++，coreJava，Servlet，JSP和EJB。
   		（1）循环给二维数组的每一个元素赋0~100之间的随机整数。
   		（2）按照列表的方式输出这些学员的每门课程的成绩。
   		（3）要求编写程序求每个学员的总分，将其保留在另外一个一维数组中。
   		（4）要求编写程序求所有学员的某门课程的平均分。

	 */

	public void Demo14(){
		int[][] studentsScore=new int[20][5];
		for(int i=0;i<20;i++){
			for(int j=0;j<5;j++){
				studentsScore[i][j]=(int)(Math.random()*101);
			}
		}
		for(int i=0;i<studentsScore.length;i++){
			for(int j=0;j<studentsScore[i].length;j++){
				System.out.print(studentsScore[i][j]+"\t");
			}
			System.out.println();
		}
		int[] totalScore=new int[20];
		for(int i=0;i<studentsScore.length;i++){
			for(int j=0;j<studentsScore[i].length;j++){
				totalScore[i]+=studentsScore[i][j];
			}
		}
		System.out.println(Arrays.toString(totalScore));
		int[] Score=new int[5];
		for(int i=0;i<studentsScore.length;i++){
			for(int j=0;j<studentsScore[i].length;j++){
				Score[j]+=studentsScore[i][j];
			}
		}
		double[] averageScore=new double[5];
		for(int i=0;i<5;i++){
			averageScore[i]=Score[i]/20.0;
		}
		System.out.println(Arrays.toString(averageScore));
	}
	/*重点。。。。。。
	 * 
	 * 完成九宫格程序
    	在井字形的格局中(只能是奇数格局)，放入数字(数字由)，使每
    	行每列以及斜角线的和都相等
    	
    	经验规则：从 1 开始按顺序逐个填写； 1  放在第一行的中间位置；
    			  下一个数往右上角45度处填写；
        		  如果单边越界则按头尾相接地填；如果有填写冲突，则
        		  填到刚才位置的底下一格；
        		  如果有两边越界，则填到刚才位置的底下一格。

	 */

	public void Demo15(){
		// 定义 N 为九宫格的行列数，需要输入
        System.out.println("请输入九宫格的行列规模(只能是奇数的):");
        Scanner n = new Scanner(System.in);

        int N;

        //判断格局是否奇数 （可判断出偶数、负数 及小数）
        double d;
        while (true){
            d = n.nextDouble();
           
            N = (int)d;
            if ((d-N)>1.0E-4||N%2==0||N<0)
                {
            	System.out.println("输入出错,格局只能是正奇数。请重新输入");}
            else{  n.close();
            	break;}
        }
        
        //老师的九宫格填写方法
        int[][] result = new int[N][N];   //定义保存九宫格的数组
        int row = 0; //行 初始位置
        int col = N/2; //列 初始位置,因为列由0开始，故N/2是中间位置
        for (int i=1;  i<=N*N; i++){
            result [row][col] = i;
            row--;
            col++;
            if (row<0&&col>=N){col--;row+=2;} //行列都越界
            else if (row<0){ row = N-1;}   //行越界
            else if (col>=N){col = 0;}  //列越界
            else if (result[row][col] != 0){col--;row+=2;}  //有冲突
        }

        //打印出九宫格
        for (int i=0;  i<N;  i++){
            for(int j=0;  j<N; j++){System.out.print(result[i][j]+"\t");}
            System.out.println();
        }

	}
	/*
	 * 求一个3*3矩阵对角线元素之和  
	 */

	public void Demo16(){
		int[][] arr=new int[3][3];
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				arr[i][j]=(int)(Math.random()*10+1);
			}
		}
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
		int sum1=0;
		for(int i=0;i<arr.length;i++){
			sum1+=arr[i][i];
		}
		int sum2=0;
		for(int i=0;i<arr.length;i++){
			sum2+=arr[i][arr.length-1-i];
		}
		System.out.println("对角线(左上到右下)元素之和:"+sum1);
		System.out.println("对角线(右上到左下)元素之和:"+sum2);
		
	}
	/*********************重点*********************/
	//打印杨辉三角

	public void Demo17(){
		Scanner suin=new Scanner(System.in);
		System.out.println("请输入一个大于3的数：");
		int n=suin.nextInt()+1;//这里如果不+1，那么输出的结果少了一行，+1则全部输出。
		suin.close();
		int yh[][]=new int[n][n];
		if(n<3) System.out.println("输入错误");
		for(int i=1;i<n;i++){
			yh[i][0]=1;//始终输出第一行和最后一行的1.
			for(int j=1;j<i;j++){
				yh[i][j]=yh[i-1][j-1]+yh[i-1][j];//计算其它行的数字，并存入数组。
			}
		}
		for(int k=0;k<n;k++){
			for(int t=0;t<k;t++){
				System.out.print(yh[k][t]+",");
			}
			System.out.println();
		}
	}

	public void Demo17_01(){
		int[][] pas = new int[6][];
		for(int i = 0; i < pas.length; i++){
				pas[i] = new int[i + 1];
				pas[i][0] = 1;
				pas[i][i] = 1;
			for(int j = 0; j < pas[i].length - 1; j++){
				if(j >= 1 && i > 1){
					pas[i][j] = pas[i - 1][j - 1] + pas[i - 1][j];
					}

				}
			}
		for(int i = 0; i < pas.length; i++){
				for(int j = 0; j < pas.length - pas[i].length; j++){
					System.out.print(" ");
					}
				for(int j = 0; j < pas[i].length; j++){
					System.out.print(pas[i][j]);
					System.out.print(" ");
					}
				System.out.println();

			}
	}
	/**************************重点***********************/
	//约梭芬杀人法
	//  把犯人围成一圈，每次从固定位置开始算起，杀掉第7个人，直到剩下最后一个。
//   @Test
   public void Demo18_01(){
	   Scanner sc=new Scanner(System.in);
	   System.out.println("请输入要杀的人数：");
   		int num=sc.nextInt();
   		sc.close();
		int[] peoples=new int[num];
		int[] dead=new int[num];
		for(int i=1;i<=peoples.length;i++){
			peoples[i-1]=i;
		}
		System.out.println("被杀人的原始顺序："+Arrays.toString(peoples));
		int count=1;
		int t=0;
		int i=0;
		while(true){
			if(count%7==0){//计数器到第七个人时，执行杀人程序
				dead[t]=peoples[i];//把要杀的人按顺序放入死亡数列中
				System.out.print(dead[t]+",");
				if(t==(num-1)){//判断是否所有人都被杀
					break;
				}
				peoples[i]=-1;//用-1对已经杀的人做标记
				t++;
			}
			while(peoples[(i+1)%num]==-1){//判断下一个人是否已经被杀，如果被杀则检查下一个人
				i=(i+1)%num;	
			}
			count++;
			i=(i+1)%num;
		}
   }
    /*
     * 判断随机整数是否是素数
	     产生100个0-999之间的随机整数，然后判断这100个随机整数哪些
	     是素数，哪些不是？
     */
//    @Test
    public void Demo19(){
    	int isPrime=0;
    	int isNotPrime=0;
    	List<Integer> primeArr = new ArrayList<Integer>();
    	List<Integer> notPrimeArr = new ArrayList<Integer>();
    	for(int i=0;i<100;i++){
    		int a=(int)(Math.random()*1000);
    		if(isPrime(a)){
    			isPrime++;
    			primeArr.add(a);
    		}else{
    			isNotPrime++;
    			notPrimeArr.add(a);
    		}
    	}
    	
    	System.out.println("素数共"+isPrime+"个，非素数共"+isNotPrime+"个");
    	System.out.print("素数为：");
    	for(Integer i:primeArr){
    		System.out.print(i.intValue()+",");
    	}
    	System.out.println();
    	System.out.print("非素数为：");
    	for(Integer i:notPrimeArr){
    		System.out.print(i.intValue()+",");
    	}
    	
    }
    public static boolean isPrime(int num){
    	boolean flag=true;
    	for(int i=2;i<Math.sqrt(num);i++){
    		if(num%i==0){
    			flag=false;
    			break;
    		}
    	}
    	return flag;
    }
    /*
     * 某公司的雇员分为以下若干类：
	   Employee：这是所有员工总的父类，属性：员工的姓名和生日月份。
	     方法：getSalary(int month) 根据参数月份来确定工资，如果该
	     月员工过生日，则公司会额外奖励100元。
	   SalariedEmployee：Employee的子类，拿固定工资的员工。
	     属性：月薪
	   HourlyEmployee：Employee的子类，按小时拿工资的员工，每月
	     工作超出160小时的部分按照1.5倍工资发放
	     属性：每小时的工资、每月工作的小时数
	   SalesEmployee：Employee的子类，销售人员，工资由月销售额和
	     提成率决定
	     属性：月销售额、提成率
	   BasePlusSalesEmployee：SalesEmployee的子类，有固定底薪的
	     销售人员，工资由底薪加上销售提成部分    
	      属性：底薪。
     */
  //以下全是类，要验证的话自己新建一个class去调用实现
    public class Employee{
    	String name;
    	private int birth;
    	
		public Employee() {
			super();
		}
	
		public Employee(String name, int birth) {
			super();
			this.name = name;
			this.birth = birth;
		}
		
		public String getName(String name) {
			return name;
		}
		
		public double getSalary(int month){
			double birthReward = 0;
			if(birth==month){
				birthReward=100;
			}
			return birthReward;
		}	
    }
   public class SalariedEmployee extends Employee{
	   
	   private double mouthlySalary;

		public SalariedEmployee(String name, int birth, int mouthlySalary) {
		super(name, birth);
		this.mouthlySalary = mouthlySalary;
	     }

		public double getSalary(int month,double extraPay) {
		
			return mouthlySalary+super.getSalary(month)+extraPay;
		}
   }
   public class HourlyEmployee extends Employee{
	  private int hours;
	  private int hourlySalary;
	

	public HourlyEmployee(String name, int birth, int hours, int hourlySalary) {
		super(name, birth);
		this.hours = hours;
		this.hourlySalary = hourlySalary;
	}
	public double gerSalary(int month){
		double salary;
		if(hours>160){
			salary=hourlySalary*160+(hours-160)*1.5*hourlySalary+super.getSalary(month);
		}else{
			salary=hourlySalary*hours+super.getSalary(month);
		}
		return salary;	
	}
   }
   public class SalesEmployee extends Employee{
	   private double mouthlySale;
	   private double royaltyRate;
	   
	
	public SalesEmployee(String name, int birth,double mouthlySale, double royaltyRate) {
		super(name,birth);
		this.mouthlySale = mouthlySale;
		this.royaltyRate = royaltyRate;
	}


	public double gerSalary(int month){
		double salary;
		salary=mouthlySale*royaltyRate+super.getSalary(month);
		return salary;
		
	} 
   }
   public class BasePlusSalesEmployee extends SalesEmployee{
	   double basicSalary;


	   public BasePlusSalesEmployee(String name, int birth, double mouthlySale, double royaltyRate, double basicSalary) {
		super(name, birth, mouthlySale, royaltyRate);
		this.basicSalary = basicSalary;
	}


	public double getSalary(int month,double extraPay){
		   return basicSalary+super.gerSalary(month)+extraPay;
		}
	   }
   /*
    *  某学校为学生分配宿舍，每6个人一间房（不考虑性别差异），问需
    *  要多少房？
    */
//   @Test
   public void Demo20(){
	   Scanner sc=new Scanner(System.in);
	   System.out.println("请输入学生的人数：");
	   int students=sc.nextInt();
	   sc.close();
	   System.out.println("总共需要"+(students+5)/6+"间房间");
   }
   /*
    *  让数值在 0～9 之间循环
    */
 // @Test
   public void Demo21(){
	   int i=0;
	   while(true){
		   System.out.println(i%10);
		   i++;
	   }
   }
 // @Test
  public void Demo21_01(){
	  int i=0;
      while(true){
          i = (i+1)%10;
          System.out.println(i);
      }
  }
  /*
   *  写一个数组类（放对象）：
   *  功能包括：添加(添加不限制多少项)、修改、插入、删除、查询
   */
 // @Test
  public void Demo22(){
	  MyArray ma = new MyArray();
      ma.add("aaa");
      ma.add("bbb");
      ma.add("ccc");
      ma.set(2, "123");
      ma.insert(1, "yy");
      ma.remove(1);
      ma.add("bbb");
      ma.remove("bbb");
      Object[] ma1=ma.getOs();
      for(Object o:ma1){
          System.out.println(o);
      }

  }
  /*
   * 1. 随机产生 20 个整数(10以内的)，放入一个ArrayList中，
   * 	 用迭代器遍历这个ArrayList
   * 2. 并删除其中为 5 的数
   * 3. 再产生 3 个整数，插入到位置 4 处
   * 4. 把所有值为 1 的数都变成 10
   */
//  @Test
  public void Demo23(){
	  List<Integer> list=new ArrayList<Integer>();
	  for(int i=0;i<20;i++){
		  list.add((int)(Math.random()*10+1));
	  }
	  Iterator<Integer> it=list.iterator();
	  while(it.hasNext()){
		  System.out.print(it.next()+" ");
	  }
	  System.out.println();
	  System.out.println("原始集合为："+list);
	  for(int i=0;i<list.size();i++){
		  if(list.get(i)==5){
			  list.remove(i);
		  }
	  }
	  System.out.println("删除5之后的集合："+list);
	  List<Integer> list1=new ArrayList<Integer>();
	  for(int j=0;j<3;j++){
		  int temp=(int)(Math.random()*10+1);
		  list1.add(temp);
		  list.add(4, temp);
	  }
	  System.out.println("新产生的三个元素为："+list1);
	  System.out.println("插入到原数组中后："+list);
	  for(int i=0;i<list.size();i++){
		  if(list.get(i)==1){
			  list.set(i, 10);
		  }
	  }
	  System.out.println("将1改为10之后："+list);
  }
  /*
   * 1. 产生 3000 个 10 以内的数，放入 hashSet
   * 2. 遍历它，打印每一个值
   */
// @Test
  public void Demo24(){
//	  HashSet<Integer> hashSet=new HashSet<Integer>();
//	  for(int i=0;i<3000;i++){
//		  hashSet.add((int)(Math.random()*10));
//	  }
//	  System.out.println(hashSet);
	  Random r = new Random();
      HashSet<Integer> hs1 = new HashSet<Integer>();
      for(int i=0; i<3000; i++){
          hs1.add(r.nextInt(10));
      }
      Iterator<Integer> it1 = hs1.iterator();
      while(it1.hasNext()){
          System.out.print(it1.next()+" ");
      }

  }
  /*
   * 测试TreeSet 的比较器，
   * 在有自己的比较器的情况下，如何实现Comparable接口
   */
//  @Test
  public void Demo25(){
	  Set<Student1> set = new TreeSet<Student1>(new Comparator<Student1>(){

			public int compare(Student1 o1, Student1 o2) {
				return -(o1.getAge()-o2.getAge());
			}
		  });
	  set.add(new Student1("张三","123",18));
	  set.add(new Student1("李四","456",21));
	  set.add(new Student1("王五","789",20));
	  
	  System.out.println(set);
  }
  /*
   * 在Map中，以name作Key，以Student类 作Velue，写一个HashMap
   */
//  @Test
  public void Demo26(){
	  Map<String,Student1> map=new HashMap<String,Student1>();
	  map.put("张三", new Student1("张三","123",18));
	  map.put("李四", new Student1("李四","456",19));
	  map.put("王五", new Student1("王五","789",20));
	  Set<String> keySet=map.keySet();
	  for(String  s:keySet){
		  System.out.println(s);
	  }
	  System.out.println("========================================");
	  Set<Entry<String,Student1>> entrySet=map.entrySet();
	  for(Entry<String,Student1> e:entrySet){
		  System.out.println(e.getKey()+":"+e.getValue());
	  }
  }
  /*
   * 自己写一个栈:     ( 先进后出 )
   * 建议底层用LinkedList实现
   * 参照 java.util.Stack
   * 方法:  boolean empty()  测试堆栈是否为空。
   * E  peek()        查看栈顶对象而不移除它。
   * E  pop()         移除栈顶对象并作为此函数的值返回该对象。
   * E  push(E item)  把项压入栈顶。
   */
//  @Test
  public void Demo27(){
	  Deque<String> stack=new LinkedList<String>();
	
	  stack.push("a");
	  stack.push("b");
	  stack.push("c");
	  System.out.println(stack);
	  String str=stack.peek();
	  System.out.println(str);
	  String pop=stack.pop();
	  System.out.println(pop);
	  System.out.println(stack);
	  stack.push("d");
	  System.out.println(stack);
  }
}


class MyArray{
	 private Object[] os = {};
	 
	public Object[] getOs() {
		return os;
	}

	public void setOs(Object[] os) {
		this.os = os;
	}

	public void add(Object o) {
    	 Object[] newArray=Arrays.copyOf(os, os.length+1);
    	 newArray[newArray.length-1]=o;
    	 os=newArray;

	}
    
	public void set(int index, Object o) {
    	 os[index]=o;
	}
     public void insert(int index, Object  o) {
    	 Object[] newArray=Arrays.copyOf(os, os.length+1);
    	 for(int i=newArray.length-1;i>index;i--){
    		 newArray[i]=newArray[i-1];
    	 }
    	 newArray[index]=o;
    	 os=newArray;
	}
     public void remove(int index) {
    	 Object[] newArray=os;
    	 for(int i=index;i<newArray.length-1;i++){
    		 newArray[i]=newArray[i+1];
    	 }
    	 newArray=Arrays.copyOf(newArray, newArray.length-1);
    	 os=newArray;
	}
     public void remove(Object o) {
    	 Object[] newArray=os;
    	 for(int i=0;i<newArray.length;i++){
    		
    		 if(newArray[i]==o){
    			 for(int j=i;j<newArray.length-1;j++){
    	    		 newArray[j]=newArray[j+1];
    	    	 }
    			 newArray=Arrays.copyOf(newArray, newArray.length-1);
    		 }
    	 }
    	 os=newArray;
	}
     public Object get(int index) {
    	 
		return os[index];
	}
}

class Student1{
	private String name;
	private String Id;
	private int age;
	public Student1(String name, String id, int age) {
		super();
		this.name = name;
		Id = id;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", Id=" + Id + ", age=" + age + "]";
	}
	
	
}





