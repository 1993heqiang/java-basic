package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RollingBall extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=500;
	public static final int HEIGHT=500;
	public static final int R=10;//小球的半径
	public static final int SPEED=7;//速度范围
	
	private Random random=new Random();
	private Ball ball=new Ball();//小球的对象
	private Color[] colors=//所有颜色数组
		{Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,
				Color.BLUE,Color.CYAN,Color.PINK};
	private Color color=Color.WHITE;//颜色变量，初始颜色为白色
	
	private int xSpeed=-(random.nextInt(SPEED)+1);//横向速度
	private int ySpeed=(random.nextInt(SPEED)+1);//纵向速度
	
	
	
	public void action(){
		
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				if(ball.getX()<R){//碰到左边界，小球的x方向、速度和颜色改变
					xSpeed=random.nextInt(SPEED)+1;
					color=colors[random.nextInt(colors.length)];
				}
				if(ball.getY()>(HEIGHT-R)){//碰到下边界，小球的y方向、速度和颜色改变
					ySpeed=-(random.nextInt(SPEED)+1);
					color=colors[random.nextInt(colors.length)];
				}
				if(ball.getX()>(WIDTH-R)){//碰到右边界，小球的x方向、速度和颜色改变
					xSpeed=-(random.nextInt(SPEED)+1);
					color=colors[random.nextInt(colors.length)];
				}
				if(ball.getY()<R){//碰到上边界，小球的y方向、速度和颜色改变
					ySpeed=random.nextInt(SPEED)+1;
					color=colors[random.nextInt(6)];
				}
				
				ball.setX(ball.getX()+xSpeed);//将得到的横向速度设置给小球
				ball.setY(ball.getY()+ySpeed);//将得到的纵向速度设置给小球
				repaint();	
			
			}
			
		}, 10, 10);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);//设置背景颜色
	
		g.fillRect(0, 0, WIDTH, HEIGHT);//绘制背景
		
		g.setColor(color);//设置小球颜色
		
		g.fillOval(ball.getX(), ball.getY(), 2*R,2*R );//绘出小球的位置
	}
	
	public static void main(String[] args){
		
		JFrame frame=new JFrame("RollingBall");
		frame.setSize(WIDTH, HEIGHT);//设置框体的大小
	//	frame.setBackground(Color.BLACK);
		frame.setUndecorated(true);//去掉边框
	//	frame.setAlwaysOnTop(true);//框体总置于最上层
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//设置框体的默认位置
		
		RollingBall rb=new RollingBall();
	
		frame.add(rb);
		
		frame.setVisible(true);//设置窗体可见
		rb.action();
	}
	//小球的类
	class Ball{
		
		
		private int x;
		private int y;
		
		public Ball() {
			super();
			this.x = (int)(Math.random()*(WIDTH-2*R)+R);
			this.y = R;
		}


		public int getX() {
			return x;
		}


		public void setX(int x) {
			this.x = x;
		}


		public int getY() {
			return y;
		}


		public void setY(int y) {
			this.y = y;
		}
		
		
	}
}
