package com.etoak.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseQuartz {
	
	
	public static void main(String[] args)throws Exception{
		DatabaseQuartz.backup();
	}
	
	public static Connection getConnection()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql:///erp","root","root");
	}
	
	public static void backup()throws Exception{
		//System.out.println("--备份开始执行--");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String filename = format.format(new Date())+".sql";
		File file = new File("D:/"+filename);
		PrintWriter pw = new PrintWriter(new FileOutputStream(file),true);
		StringBuffer create=null;
		StringBuffer insert=null;
		Connection con = getConnection();
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet tables = dbmd.getTables(null,null,null,null);
		while(tables.next()){
			create = new StringBuffer("CREATE TABLE ");
			insert = new StringBuffer("INSERT INTO ");

			String tName  = tables.getString(3);

			create.append(tName).append(" (");
			insert.append(tName).append("(");
			String sql="select * from "+ tName;
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet data = pst.executeQuery();
			ResultSetMetaData rsmd = data.getMetaData();
			int count = rsmd.getColumnCount();
			for(int i=1;i<=count;i++){
				//列名字
				String cName = rsmd.getColumnName(i);
				//列类型
				String cType = rsmd.getColumnTypeName(i);
				//列长度
				int size = rsmd.getColumnDisplaySize(i);
				if(i==count){
					create.append(cName).append(" "+(cType.equals("VARCHAR")?cType+"("+size+")":cType)).append(");\n");
					insert.append(cName).append(")values (");
				}else{
					create.append(cName).append(" "+(cType.equals("VARCHAR")?cType+"("+size+")":cType)).append(",");
					insert.append(cName).append(",");
				}
			}
				//写创建表的语句
			pw.println(create.toString());
			while(data.next()){
				StringBuffer str = new StringBuffer(insert.toString());
				for(int i=1;i<=count;i++){
					String v = data.getString(i);
					if(i==count ){
						if(v!=null){
							str.append("'"+v+"'").append(");\n");
						}else{
							str.append("''").append(");\n");
						}
					}else{
						if(v!=null){
							str.append("'"+v+"',");
						}else{
							str.append("'' ,");
						}
					}
				}
				pw.println(str.toString());
			}
		}

		//System.out.println("--备份执行完毕--");
	}


}
