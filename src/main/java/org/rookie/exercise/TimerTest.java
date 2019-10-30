package org.rookie.exercise;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest{
	 
	   public static void main(String[] args){
	    	Timer timer = new Timer();
	    	//匿名内部类
	    	timer.schedule(new TimerTask(){
	    	 
	    	public void run() {
	    			 
	    		System.out.println("MyTask...");
	        
	    	 }
	    		
	    	}, 1000, 500);
	    	timer.cancel(); 
	    }
	} 
