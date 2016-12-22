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
		//����ļ�
		Iterator<String> iter = mr.getFileNames();
		String fileInputName = iter.next();
		MultipartFile file = mr.getFile(fileInputName);
		//����ļ����ֽ�����
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//��ȡԭʼ�ļ���
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		//��ȡ�ļ�������������ַ
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
		String relativePath = "/upload/" + fileName + suffix;
			
		//����Jersey�ͻ���
		Client client = Client.create();
		//����ϴ��Ƿ��ϴ���ͼƬ
		if (StringUtils.isNotEmpty(lastPath)) {
			WebResource wr1 = client.resource(lastPath);
			wr1.delete();
		}
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		//ajax���ض��ֵ
		JSONObject jo = new JSONObject();
		jo.accumulate("realPath", realPath);
		jo.accumulate("relativePath", relativePath);
		String result = jo.toString();
		out.write(result);
	}
	
	@RequestMapping("/uploadPics.do")
	public void uploadPics(HttpServletRequest request,PrintWriter out,Integer picNum) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//����ļ�
		List<MultipartFile> files = mr.getFiles("fileNames");
		MultipartFile file = files.get(picNum - 1);
		//����ļ����ֽ�����
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//��ȡԭʼ�ļ���
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		System.out.println("=============��ȡ�ļ��ɹ�������׼���ϴ�===========");
		//��ȡ�ļ�������������ַ
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
		String relativePath = "/upload/" + fileName + suffix;
			
		//����Jersey�ͻ���
		Client client = Client.create();
		//����ϴ��Ƿ��ϴ���ͼƬ
		String lastPath = request.getParameter("lastPath"+picNum);
		if (StringUtils.isNotEmpty(lastPath)) {
			WebResource wr1 = client.resource(lastPath);
			wr1.delete();
		}
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		//ajax���ض��ֵ
		JSONObject jo = new JSONObject();
		jo.accumulate("realPath", realPath);
		jo.accumulate("relativePath", relativePath);
		String result = jo.toString();
		out.write(result);
		System.out.println("=============�ļ��ϴ��ɹ�===========");
	}
	
	@RequestMapping("/uploadForEditor.do")
	public void uploadForEditor(HttpServletRequest request,PrintWriter out) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		//����ļ�
		Iterator<String> iter = mr.getFileNames();
		String fileInputName = iter.next();
		MultipartFile file = mr.getFile(fileInputName);
		//����ļ����ֽ�����
		byte[] bytpArr = file.getBytes();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName += random.nextInt(10);
		}
		//��ȡԭʼ�ļ���
		String oriFileName = file.getOriginalFilename();
		if (oriFileName.isEmpty()) {
			return;
		}
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		
		//��ȡ�ļ�������������ַ
		String filePath = MaoUtils.readProp("file_path");
		String realPath = filePath + "/upload/" + fileName + suffix;
			
		//����Jersey�ͻ���
		Client client = Client.create();
		WebResource wr = client.resource(realPath);
		wr.put(bytpArr);
		
		out.print(realPath);
		
	}
}
