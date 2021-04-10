package com.koreait.app.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;

public class FileDownloadAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String dwName = request.getParameter("filename");
		String realName = request.getParameter("realname");
		String saveFolder = request.getServletContext().getRealPath("app/files");
		String filename = saveFolder+"\\"+dwName;
		String client = "";
		System.out.println(saveFolder);
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		PrintWriter out = response.getWriter();
		
		file = new File(filename);
		is = new FileInputStream(file);
		client = request.getHeader("User-Agent");
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");
		
		try {
			try {
				//day10.sql					day10.sql
				dwName = URLEncoder.encode(realName,"UTF-8").replaceAll("\\+","%20");
			} catch (Exception e) {
				dwName = URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+", "%20");
			}
			
			dwName=new String(dwName.getBytes("UTF-8"),"ISO-8859-1");
			
			if(client.indexOf("MSIE") != -1) {
				//IE 인 경우
				response.setHeader("Content-Disposition","attachment; fileName="+dwName);
			}else {
				//그 외 경우
				response.setHeader("Content-Disposition","attachment; fileName=\""+dwName+"\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			}
			response.setHeader("Content-Length", file.length()+"");
			out.flush();
			os = response.getOutputStream();
			byte b[] = new byte[(int)file.length()];
			int leng=0;
			while((leng=is.read(b,0,b.length))!=-1) {
				os.write(b,0,leng);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if(is != null) {
				is.close();
			}
			if(os != null) {
				os.close();
			}
			if(out != null) {
				out.close();
			}
		}
		return null;
	}
}
















