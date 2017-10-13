package com.etoak.util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerListener implements ServletContextListener {

	private Timer timer;
	private AnnleeTimerTask task;

	public void contextDestroyed(ServletContextEvent arg0) {
	   timer.cancel();
	  // System.out.println("定时器已销毁");
	}

	public void contextInitialized(ServletContextEvent event) {

	   timer = new java.util.Timer(true);
	   task = new AnnleeTimerTask(event.getServletContext());
	  // System.out.println("定时器已启动");
	   timer.schedule(task, 0, 1000*60*60*6);
	  // System.out.println("已经添加任务调度表");
	}

	}