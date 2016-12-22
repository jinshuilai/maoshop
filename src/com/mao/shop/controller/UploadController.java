package com.mao.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mao.shop.utils.MaoUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/uploadPic.do")
	public void uploadPic(HttpServletRequest request,PrintWriter out,String lastPath) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//获得文件
		Iterator<String> iter = mr.getFileNames();
		String fileInputName = iter.next();
		MultipartFile file = mr.getFile(fileInputName);
		//获得文件的字节数组
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//获取原始文件名
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		//获取文件服务器主机地址
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
		String relativePath = "/upload/" + fileName + suffix;
			
		//创建Jersey客户端
		Client client = Client.create();
		//检查上次是否上传过图片
		if (StringUtils.isNotEmpty(lastPath)) {
			WebResource wr1 = client.resource(lastPath);
			wr1.delete();
		}
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		//ajax返回多个值
		JSONObject jo = new JSONObject();
		jo.accumulate("realPath", realPath);
		jo.accumulate("relativePath", relativePath);
		String result = jo.toString();
		out.write(result);
	}
	
	@RequestMapping("/uploadPics.do")
	public void uploadPics(HttpServletRequest request,PrintWriter out,Integer picNum) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//获得文件
		List<MultipartFile> files = mr.getFiles("fileNames");
		MultipartFile file = files.get(picNum - 1);
		//获得文件的字节数组
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//获取原始文件名
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		System.out.println("=============获取文件成功，正在准备上传===========");
		//获取文件服务器主机地址
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
		String relativePath = "/upload/" + fileName + suffix;
			
		//创建Jersey客户端
		Client client = Client.create();
		//检查上次是否上传过图片
		String lastPath = request.getParameter("lastPath"+picNum);
		if (StringUtils.isNotEmpty(lastPath)) {
			WebResource wr1 = client.resource(lastPath);
			wr1.delete();
		}
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		//ajax返回多个值
		JSONObject jo = new JSONObject();
		jo.accumulate("realPath", realPath);
		jo.accumulate("relativePath", relativePath);
		String result = jo.toString();
		out.write(result);
		System.out.println("=============文件上传成功===========");
	}
	
	@RequestMapping("/uploadForEditor.do")
	public void uploadForEditor(HttpServletRequest request,PrintWriter out) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//获得文件
		Iterator<String> iter = mr.getFileNames();
		String fileInputName = iter.next();
		MultipartFile file = mr.getFile(fileInputName);
		//获得文件的字节数组
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//获取原始文件名
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		//获取文件服务器主机地址
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
			
		//创建Jersey客户端
		Client client = Client.create();
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		
		out.print(realPath);
		
	}
}
