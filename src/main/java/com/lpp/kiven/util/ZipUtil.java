package com.lpp.kiven.util;


import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang.StringUtils;

import java.io.*;

public class ZipUtil{

/**
* 
* @Title: zipFilesAndEncrypt
* @Description: 将指定路径下的文件压缩至指定zip文件，并以指定密码加密 若密码为空，则不进行加密保护
* @param srcFileName 待压缩文件路径
* @param zipFileName zip文件名
* @param password 加密密码 
* @return
* @throws Exception 
*/
public static void zipFilesAndEncrypt(String srcFileName,String zipFileName,String password)
		throws Exception{
	ZipOutputStream outputStream = null;
	InputStream inputStream = null;
	
	if(StringUtils.isEmpty(srcFileName) || StringUtils.isEmpty(zipFileName)){
		
		System.out.println("待压缩文件或者压缩文件名");
	}
	
	try {
		File srcFile = new File(srcFileName);   
		File[] files = new File[0];
		if (srcFile.isDirectory()) {
		files = srcFile.listFiles();
		} else {
		files = new File[1];
		files[0] = srcFile;
		}
		outputStream = new ZipOutputStream(new FileOutputStream(new File(zipFileName)));
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		if(!StringUtils.isEmpty(password)){
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		parameters.setPassword(password);
		}	

		int fileNums = files.length;
		for (int i = 0; i < fileNums; i++) {
		File file = (File)files[i];
	
		outputStream.putNextEntry(file,parameters);
		
		if (file.isDirectory()) {
		outputStream.closeEntry();
		continue;
		}
		
		inputStream = new FileInputStream(file);
		byte[] readBuff = new byte[4096];
		int readLen = -1;
		//Read the file content and write it to the OutputStream
		while ((readLen = inputStream.read(readBuff)) != -1) {
		outputStream.write(readBuff, 0, readLen);
		}
		//Once the content of the file is copied, this entry to the zip file
		//needs to be closed. ZipOutputStream updates necessary header information
		//for this file in this step
		outputStream.closeEntry();
		inputStream.close();
		}
		//ZipOutputStream now writes zip header information to the zip file
		outputStream.finish();
	} catch (Exception e) {
		System.out.println("文件压缩出错");
		throw e;
	} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println("压缩文件输出错误");
					throw e;
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						System.out.println("压缩文件错误");
						throw e;
					}
				}
			
			}
	}
}

public static void main(String[] args) {
	String filename="";
	try {
		ZipUtil.zipFilesAndEncrypt("d:/molog.txt", "d:/molog.zip", "123456");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
	
