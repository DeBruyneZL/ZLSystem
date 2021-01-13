package com.lpp.kiven.util;

import com.lpp.kiven.config.log.Log4j2Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	public static void uploadFile(byte[] file, String filePath, String fileName)
			throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath +"/"+ fileName);
		out.write(file);
		out.flush();
		out.close();
	}

    /**
     * 上传文件
     * @param file
     * @param fileFileName
     * @param path
     * @return
     */
    public static String upload(MultipartFile file, String fileFileName, String path) {
        Log4j2Logger.info("开始上传文件" + fileFileName);
        try {
            char[] fileNameList=fileFileName.toCharArray();
            int fileNameLenght=0;
            for (int i=0;i<fileNameList.length;i++){
                if (StringUtil.isChineseChar(fileNameList[i])){
                    fileNameLenght+=2;
                }
                else {
                    fileNameLenght+=1;
                }
            }
            if (fileNameLenght>1024){
                Log4j2Logger.error("上传文件失败，文件名超长！");
                return "上传文件失败，文件名超长！（不超过512汉字/不超过1024字母）";
            }
            if (file.getSize() > 100 * 1024 * 1000) {
                Log4j2Logger.error("上传文件失败，上传文件超过100M！");
                return "上传文件失败，上传文件超过100M！";
            }
            File infile = new File(path);
            if (infile.exists()) {
                Log4j2Logger.error("上传文件失败，文件已存在：" + infile.getPath());
                return "上传文件失败，文件已存在";
            }
            FileOutputStream fos = new FileOutputStream(infile);
            InputStream is = file.getInputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while (((len = is.read(buffer)) > 0)) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
            Log4j2Logger.info("上传文件成功");
            return "SUCCESS";
        } catch (FileNotFoundException e) {
            Log4j2Logger.error("上传文件失败，文件未找到：" + e.getMessage() + e);
            e.printStackTrace();
            return "上传文件失败";
        } catch (IOException e) {
            Log4j2Logger.error("上传文件失败，写入文件失败：" + e.getMessage() + e);
            e.printStackTrace();
            return "上传文件失败";
        }
    }

    /**
     * 文件下载通用
     * @param path 文件路径
     * @param exportFileName 下载后的文件名
     * @param response
     * add by zhangliang 2020/04/14
     */
    public static void download(String path, String exportFileName, HttpServletResponse response) {
        InputStream input = null;
        OutputStream out = null;
        try {
            input=new FileInputStream(path);
            response.reset();
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String(exportFileName.getBytes("utf-8"), "ISO_8859_1") + "\"");
            response.addHeader("Content-Length", "" + input.available());
            out = response.getOutputStream();
            IOUtils.copy(input, out);
        }catch (Exception e){
            Log4j2Logger.error("--下载文件"+exportFileName+"发生错误！", e);
        }
        finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 文件删除
     * @param filePath
     */
    public static void deleteFile(String filePath){
        File file=new File(filePath);
        if (file.exists()){
            file.delete();
            Log4j2Logger.info("文件："+filePath+"已删除！");
        }else {
            Log4j2Logger.info("文件不存在");
        }
    }
    
    /**
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
     public static List<String> getFiles(String path) {
         List<String> files = new ArrayList<String>();
         File file = new File(path);
         File[] tempList = file.listFiles();

         for (int i = 0; i < tempList.length; i++) {
             if (tempList[i].isFile()) {
                 files.add(tempList[i].toString());
                 //文件名，不包含路径
                 //String fileName = tempList[i].getName();
             }
             if (tempList[i].isDirectory()) {
            	 getFiles(tempList[i].getAbsolutePath());
             }
         }
         return files;
     }
     
     /**
      * 批量打包下载文件
     * @param path 压缩文件初始位置
     * @param zipName 压缩文件名称
     * @param files 批量文件地址
     * @param request
     * @param response
     * @throws Exception
     */
    public static void batchDown(String path, String zipName, List<String> files, HttpServletRequest request, HttpServletResponse response) throws Exception {
 		// path 压缩文件初始设置 
 		String fileZip = zipName + ".zip"; // 拼接zip文件,之后下载下来的压缩文件的名字
 		String filePath = path + fileZip;// 之后用来生成zip文件
  
 		// 创建临时压缩文件
 		try {
 			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
 			ZipOutputStream zos = new ZipOutputStream(bos);
 			ZipEntry ze = null;
 			for (int i = 0; i < files.size(); i++) {// 将所有需要下载的文件都写入临时zip文件
 				BufferedInputStream bis = new BufferedInputStream(
 						new FileInputStream(files.get(i)));
 				ze = new ZipEntry(
 						files.get(i).substring(files.get(i).lastIndexOf(System.getProperty("file.separator"))));
 				zos.putNextEntry(ze);
 				int s = -1;
 				while ((s = bis.read()) != -1) {
 					zos.write(s);
 				}
 				bis.close();
 			}
 			zos.flush();
 			zos.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		// 以上，临时压缩文件创建完成
  
 		// 进行浏览器下载
 		// 获得浏览器代理信息
 		String agent = request.getHeader("User-Agent").toUpperCase();
 		// 判断浏览器代理并分别设置响应给浏览器的编码格式
 		String finalFileName = null;
 		if ((agent.indexOf("MSIE") > 0)
 				|| ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1)))
 			finalFileName = URLEncoder.encode(fileZip, "UTF-8");
 		else {
 			finalFileName = new String(fileZip.getBytes("UTF-8"), "ISO8859-1");
 		}
 		response.setContentType("application/x-download");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
 		response.setHeader("Content-Disposition", "attachment;filename=\""
 				+ finalFileName + "\"");// 下载文件的名称
 		//输出到本地
 		ServletOutputStream servletOutputStream = response.getOutputStream();
 		DataOutputStream temps = new DataOutputStream(servletOutputStream);
  
 		DataInputStream in = new DataInputStream(new FileInputStream(filePath));// 浏览器下载临时文件的路径
 		byte[] b = new byte[2048];
 		File reportZip = new File(filePath);// 之后用来删除临时压缩文件
 		try {
 			while ((in.read(b)) != -1) {
 				temps.write(b);
 			}
 			temps.flush();
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			if (temps != null)
 				temps.close();
 			if (in != null)
 				in.close();
 			if (reportZip != null)
 				reportZip.delete();// 删除服务器本地产生的临时压缩文件
 			/*servletOutputStream.close();*/
 		}
 	}
}
