package com.etoak.util;

import java.util.TimerTask;

import javax.servlet.ServletContext;

public class AnnleeTimerTask extends TimerTask {
	private ServletContext context; 
    private static boolean isRunning = false;
    private static boolean flag = true;
    private static final int C_SCHEDULE_HOUR = 0;
   
    public AnnleeTimerTask(ServletContext context){
     this.context = context; 
    }

    public void run() {
        if (!isRunning) {
        	try{
             DatabaseQuartz.backup();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        } 
      }

}
