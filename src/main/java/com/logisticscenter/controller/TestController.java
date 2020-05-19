package com.logisticscenter.controller;

import com.asprise.imaging.core.Request;
import com.asprise.imaging.core.Result;
//import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;
import com.common.ConvertService;
import com.javabean.ImageFileBean;
import com.logisticscenter.service.ImageFileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {
	
	public static void main(String[] args) {
//		BaseBean bs = new BaseBean();
//		String name = bs.getPropValue("columnCheck", "column");
//		System.out.println(name);
//		try{
//			Resources.getResourceAsReader("sqlupgrade"+File.separatorChar+"SQLServer"+File.separatorChar+"aa.sql");
//		}catch(Exception e){
//			e.printStackTrace();
//		}\
//		Map newOld = new HashMap();
//		newOld.put("a", "1");
//		newOld.put("b", "2");
//		newOld.put("c", "3");
//		newOld.put("d", "4");
//		setMap(newOld);
//		System.out.println(newOld.toString());
//		String turnDate = "";
//			String beginDate = "2016-08-08 12:00";
//		turnDate = beginDate.substring(0,4)+beginDate.substring(5,7)+beginDate.substring(8,10);
//		Pattern p =  Pattern.compile(ConstantUtils.dateRegex);//复杂匹配  
//		Matcher m = p.matcher(beginDate);  
//		System.out.println(m.matches());
//		try{
//			Imaging image = new Imaging(null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try {
//			Result result = new AspriseScanUI().setRequest(
//					  Request.fromJson("{" +
//					    "  \"twain_cap_setting\" : {" +
//					    "    \"ICAP_PIXEXELTYPE\" : \"TWPT_RGB\"," +
//					    "    \"ICAP_SUPPORPORTEDSIZES\" : \"TWSS_USLESLETTER\"" +
//					    "  }," +
//					    "  \"output_settings\" : [ {" +
//					    "    \"type\" : \"save\"," +
//					    "    \"format\" : \"jpg\"," +
//					    "    \"save_path\" : \".\\\\${TMS}-thumb${EXT}\"" +
//					    "  }, {"+
//					    "    \"type\" : \"save-thumbnail\"," +
//					    "    \"format\" : \"jpg\"," +
//					    "    \"save_path\" : \".\\\\${TMS}-thumb${EXT}\"" +
//					    "  } ]" +
//					    "}")
//					  ).showDialog(null, "Scan", true, null); // owner can be null
//
//			List<File> files  = null;
//			//List<File> files = result.getImageFiles(); // Gets files
//			File file = null;
//			ApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext_bean.xml");
//			ApplicationContext context1 = new ClassPathXmlApplicationContext(new String[] { "applicationContext_bean.xml" });
//			ImageFileService imageFileService = (ImageFileService) context1.getBean("imageFileService");
//			//设置目录
//			String dateTemp=ConvertService.getDate();;
//			String root = "D:\\temp\\"+dateTemp;
//			File fileRoot = new File(root);
//			if(!fileRoot.isDirectory()){
//				fileRoot.mkdirs();
//			}
//			String realName = "";
//			for(int i = 0;i<files.size();i++){
//				//获得唯一序列号
//				InputStream is = new FileInputStream(files.get(i));
//				realName = files.get(i).getName();
//				//String fileArr[] = fileFileName[i].split("\\.");
//				//String fileName = fileArr[0];
//				//String suffix = fileArr[1];
//				//String contentType = fileContentType[i];
//				//String realName = idGenerator.next();
//				OutputStream os = new FileOutputStream(new File(root, realName));
//
//
//				//因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
//				System.out.println("file: " + files.get(i).getName());
//				System.out.println("file: " + files.get(i).getPath());
//
//				byte[] buffer = new byte[500];
//				int length = 0;
//
//				while(-1 != (length = is.read(buffer, 0, buffer.length)))
//				{
//					os.write(buffer);
//				}
//
//				os.close();
//				is.close();
//				ImageFileBean insertInfo = new ImageFileBean();
//				insertInfo.setImageFileName(realName);
//				insertInfo.setFilerealpath(root);
//				insertInfo.setImagefiletype("");
//				insertInfo.setDownloads(0);
//				insertInfo.setImagefileused(0);
//				insertInfo.setIszip("");
//				String retId = imageFileService.insertImageFile(insertInfo)+"";
//				System.out.println("retId==="+retId);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // owner can be null
		}
	
	public static void setMap( Map oldMap){
		oldMap.put("e", "5");
		oldMap.put("f", "6");
	}

}
