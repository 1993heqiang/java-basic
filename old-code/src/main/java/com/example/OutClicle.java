package com.example;

/*用数组实现约瑟夫出圈问题。 n个人排成一圈，从第一个人开始报数，
     * 从1开始报，报到m的人出圈，剩下的人继续开始从1报数，直到所有
     * 的人都出圈为止。对于给定的n,m，求出所有人的出圈顺序。
     * */
	import java.util.Scanner;

//和约梭芬杀人法原理一样
public class OutClicle {
    private int personsum;// 总人数
    private int outnum;// 要数的数
    private int person[];// 队列
    private int outperson[];// 出队次序

    //设置无参构造函数
    public OutClicle (){

    }
    //设置有参构造函数
    public OutClicle (int personsum,int outnum){
        this.personsum=personsum;
        this.outnum = outnum;
          person = new int[personsum];
          for (int i = 0; i < person.length; i++) {
           person[i] = i;
          }
          outperson= new int[personsum];

    }
    //出圈
     public void outcircle() {
          int count= 0;
          int i = 0;
          int j = 0;
          int out= 0;//记录已出环人数
          while (true) {

           if (person[i] != -1) {//pserson[i]的值是-1，代表出环
            // 没有出环，计数器加1
            count++;
            if ((count) % outnum == 0) {
             out++;
             outperson[j++] = i + 1;
             person[i] = -1;

            }
           }
           i = (i + 1) % personsum;

           if (out ==personsum) {	//是否所有的人都出环推出
            break;
           }
          }
          System.out.println("出环按顺序为：");
          for (int k = 0; k < outperson.length; k++) {	 // 输出出环顺序
           System.out.print(outperson[k] + " ");
          }

         }



    public static void main(String[] args){

        System.out.println("请输入约瑟夫环的总人数：");
        Scanner in=new Scanner(System.in);
        int n = in.nextInt();
        System.out.println("请输入出圈数：");
        Scanner in2=new Scanner(System.in);
        int m = in2.nextInt();
        OutClicle cl=new OutClicle(n,m);
        cl.outcircle();
        in.close();
        in2.close();
    }

}


