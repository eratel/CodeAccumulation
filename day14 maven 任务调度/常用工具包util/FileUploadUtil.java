package com.etoak.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	/*
	 * targetFile : 需要上传的文件对象
	 * path : 需要上传的指定目录
	 * 返回值 : 服务器保存的UUID文件名
	 */
	public static String copyFile(MultipartFile targetFile , String path){
		try{
			InputStream is = targetFile.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is,256<<10);
			String oldFileName = targetFile.getOriginalFilename();
			String filename = new UUIDGenerator().generate().toString()+
				oldFileName.substring(oldFileName.indexOf("."));
			OutputStream os = new FileOutputStream(path+"/"+filename);
			BufferedOutputStream bos = new BufferedOutputStream(os,256<<10);
			byte[] b = new byte[256<<10];
			int len;
			while((len=bis.read(b,0,b.length))!=-1){
				bos.write(b, 0, len);
			}
			bis.close();
			bos.close();
			return filename;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}









