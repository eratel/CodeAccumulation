package com.etoak.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

// 提供一个任务对象 - JDK 
public class DataBackup extends TimerTask{

	private static int count = 1;
	private Timer timer;
	public DataBackup(Timer timer){
		this.timer = timer;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("数据备份.");
		
		try {
			/**
			 * 环境变量 : 
			 * 	mysql_home   根目录
			 *  path   bin目录
			 */
			Process pro = Runtime.getRuntime()
				.exec("D://software//MySQL Server 5.0//bin//mysqldump -uroot -proot etoak");
			InputStream is = pro.getInputStream();
			
			String filename = "201609121526000"+".sql";
			File file = new File(filename);
			OutputStream os = new FileOutputStream(file);
			
			int len;
			byte[] data = new byte[1024];
			while((len=is.read(data))!=-1)
				os.write(data, 0, len);
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		count++;
		if(count==10){
			timer.cancel();
		}
	}

}










