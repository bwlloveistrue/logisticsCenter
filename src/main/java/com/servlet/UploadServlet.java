package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.logisticscenter.service.ImageFileService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.common.ConvertService;
import com.javabean.ImageFileBean;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	File tmpDir = null;

	File fileDir = null;
	
	private ImageFileService imageFileService;
	
	
	private String filePath;  
    private String tempPath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------UploadServlet start---------");
		//设置目录
		String dateTemp=ConvertService.getDate();;
		String root = "D:\\temp\\"+dateTemp;
		String accessorys = "";
		String fileNames = "";
		if (!new File("D:\\tempScan\\"+dateTemp).isDirectory())

			new File("D:\\tempScan\\"+dateTemp).mkdirs();
		if (!new File(root).isDirectory())

			new File(root).mkdirs();
		try {
			Map retResult = new HashMap();

			if (ServletFileUpload.isMultipartContent(request)) {

				DiskFileItemFactory dff = new DiskFileItemFactory();

				dff.setRepository(tmpDir);

				dff.setSizeThreshold(10240000);

				ServletFileUpload sfu = new ServletFileUpload(dff);

				sfu.setFileSizeMax(20 * 1024 * 1024);

				sfu.setSizeMax(500 * 1024 * 1024);

				FileItemIterator fii = sfu.getItemIterator(request);
				
				
				String fileName = "";
				
				while (fii.hasNext()) {

					FileItemStream fis = fii.next();

					if (!fis.isFormField() && fis.getName().length() > 0) {

						fileName = fis.getName();
						
						int index = fileName.lastIndexOf("\\");
						
						fileName = fileName.substring(index+1,fileName.length());

						String uploadPath = filePath;

						System.out.println("uploadPath=="+ uploadPath);
						
						if (!new File(uploadPath).isDirectory())

							new File(uploadPath).mkdirs();

						System.out.println("uploadPath=" + uploadPath);


						Date time = new Date();

						String dirTime = String.valueOf(time.getTime());

						//

						BufferedInputStream in = new BufferedInputStream(
								fis.openStream());

						// BufferedOutputStream out = new
						// BufferedOutputStream(new FileOutputStream(new
						// File(saveDir+"\\"+dirTime+fileName)));

						BufferedOutputStream out = new BufferedOutputStream(
								new FileOutputStream(new File(root + File.separator+fileName)));

						Streams.copy(in, out, true);
						
						ImageFileBean insertInfo = new ImageFileBean();
						insertInfo.setImageFileName(fileName);
						insertInfo.setFilerealpath(root);
						insertInfo.setImagefiletype("");
						insertInfo.setDownloads(0);
						insertInfo.setImagefileused(0);
						insertInfo.setIszip("");
//						accessorys += ","+imageFileService.insertImageFile(insertInfo);
						fileNames += ","+fileName;
					}
				}

				//RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.jsp?fileSrc="+pp);
				//System.out.println("---------"+"/upload.jsp?fileSrc="+pp+"-------------");
				//dispatcher .forward(request, response);
				PrintWriter out = response.getWriter();
				accessorys = accessorys.substring(1);
				fileNames = fileNames.substring(1);
				retResult.put("fileNames", fileNames);
				retResult.put("accessorys", accessorys);
				response.setContentType("text/html; charset=utf-8");
//				JSONObject obj = JSONObject.parseObject(retResult.toString());
//				out.print(obj.toString());
				out.flush();
				out.close();
				return;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();

        filePath = config.getInitParameter("filepath");  
        tempPath = config.getInitParameter("temppath");  


		tmpDir = new File(tempPath);

		fileDir = new File(filePath);
		if (!tmpDir.isDirectory())

			tmpDir.mkdir();

		if (!fileDir.isDirectory())

			fileDir.mkdir();
		
		ApplicationContext context1 = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext_iBatis.xml" });
		imageFileService = (ImageFileService) context1.getBean("imageFileService"); 
	}

}
