package com.etoak.util;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerListener implements ServletContextListener {

	private Timer timer;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("tomcat关闭了.");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("tomcat启动了.");
		
		timer = new Timer();
		System.out.println("计时器准备就绪,随时可以调度任务执行.");
		// 通过计时器设置在什么时候触发任务内容
		DataBackup backup = new DataBackup(timer);
		/**
		 * 	schedule(TimerTask , new Date())
		 * 	schedule(TimerTask , long time)
		 * 		在计时器调度任务之后,延迟time毫秒触发任务内容
		 * 	schedule(TimerTask , Date , long time)
		 * 		在Date指定的时间开始执行,每隔time毫秒重复周期执行
		 * 	schedule(TimerTask , long t1 , long t2)
		 * 		在计时器调度任务之后,延迟t1毫秒开始执行,每隔t2毫秒重复周期执行
		 */
		// timer.schedule(backup, new Date(116,8,12,11,25,0));
		// timer.schedule(backup, 3000);
		// timer.schedule(backup, new Date(116,8,12,11,33,0) , 3000);
		// timer.schedule(backup, 3000 , 5000);
		
		timer.schedule(backup, new Date(116,9,1,0,0,0), 31*24*60*60*1000);
	}
	/**
	 * 1 每个周六的凌晨4点 执行数据备份任务
	 * 2 每个周六的凌晨4点 执行数据备份任务;
	 * 		当任务执行10次之后，终止执行
	 * 3 每个月的一号 执行数据备份任务
	 * 		Timer无法实现该需求
	 * 		只能对周期固定的任务进行调度
	 */
}









