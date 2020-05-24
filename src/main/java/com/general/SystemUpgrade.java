/**
 * Title:		系统升级的时候执行升级脚本及一些相关操作
 * Company:	  泛微软件
 * @author:	  刘煜
 * @version:	 1.0
 * create date : 2003-7-24
 * modify log: 
 *
 *
 * Description:  在文件复制完成后 调用 java SystemUpgrade 来完成相关操作
 *
 *
 */

package com.general;

import com.common.ConvertService;
import com.file.FileManage;
import com.util.ConstantUtils;
import com.util.SpringUtil;
import com.util.TimeUtil;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;


public class SystemUpgrade extends BaseBean implements Runnable{
	private static int runFileCount = 0;
	private static int runFile = 0;
	private static StringBuffer errorbuffer = new StringBuffer();
	private List runSqlList = new ArrayList();   //SQL文件执行记录集合 TD20443  
	private SysUpgradeCominfo suc=new SysUpgradeCominfo();
	private static String filename = "";
	public static String getFilename() {
		return filename;
	}
	public static int getFileList2(){
		return runFileCount;
	}
	
	/*屏蔽之前的方法 使得Login.jsp页面不受控制*/
	public static int getFileList(){
		return 0;
	}
	public static int getCurrentFile(){
		return 0;
	}
	/*屏蔽之前的方法 使得Login.jsp页面不受控制*/
	
	public static int getCurrentFile2(){
		return runFile;
	}
	public static void setRunFileCount(int runFileCount1) {
		runFileCount = runFileCount1;
	}
	public static void setRunFile(int runFile1) {
		runFile = runFile1;
	}
	
	
	public static StringBuffer getErrorbuffer() {
		return errorbuffer;
	}
	public static void setErrorbuffer(StringBuffer errorbuffer) {
		SystemUpgrade.errorbuffer = errorbuffer;
	}



	private int status;
	private String logpath;
	private String ROOTFILEPATH ;
	private String NEWSQLFILEPATH ;
	private String SYSSQLFILEPATH ;
	private String SYSUPGRADELOG;
	private String currentDate;
	private String currentTime;
	private int errorLine;
	String sqldir = "";
	private int pagestatus;//0：未进行升级跳包检查  1：正常升级未跳包  2：跳包  3:脚本执行完成
	public void run(){

		procSql();
	}

	public void procSql() {

	   currentDate = TimeUtil.getCurrentDateString()  ;
	   currentTime = TimeUtil.getCurrentTimeString().substring(11);
		// 获得sql文件的根目录
		ROOTFILEPATH = ConstantUtils.getRootPath() ;
		NEWSQLFILEPATH = ROOTFILEPATH + "sqlupgrade" + File.separatorChar ;
		SYSSQLFILEPATH = ROOTFILEPATH + "data" + File.separatorChar ;
		SYSUPGRADELOG = ROOTFILEPATH + "sysupgradelog"+ File.separatorChar ;

		String newrunsqlfilepath = "" ;
		String sysrunsqlfilepath = "" ;
		String scriptbreak = "" ;

		// 获得需要升级的数据库脚本目录和系统原有的数据库脚本目录
		newrunsqlfilepath = NEWSQLFILEPATH + "SQLServer" + File.separatorChar  ;
		sysrunsqlfilepath = SYSSQLFILEPATH + "SQLServer" + File.separatorChar  ;
		scriptbreak = "GO" ;
		Connection jdbc_conn=null;
		Statement statement=null;
		
		procSqlEnd:
		try {
			String driverClasses = SpringUtil.getApplicationContext().getEnvironment().getProperty("spring.datasource.druid.driver-class-name");
			String url = SpringUtil.getApplicationContext().getEnvironment().getProperty("spring.datasource.druid.url");							  //链接的url
			String user = SpringUtil.getApplicationContext().getEnvironment().getProperty("spring.datasource.druid.username");							   //链接的用户名
			String password = SpringUtil.getApplicationContext().getEnvironment().getProperty("spring.datasource.druid.password");					   //链接的密码
			Driver driver = (Driver)Class.forName(driverClasses).newInstance();
			DriverManager.registerDriver(driver);
			Properties props = new Properties();
			props.put("user", user);
			props.put("password", password);
			props.put("CHARSET", "ISO");
			jdbc_conn = DriverManager.getConnection(url, props);
			statement=jdbc_conn.createStatement();
			// 获得需要升级的数据库脚本文件并按照从小到大的顺序排列
			File rundir = new File(newrunsqlfilepath);
			String runfilelist[] = rundir.list();

			try{
				runFileCount = runfilelist.length;
			}catch(Exception e){
				e.printStackTrace();
			}
			//修改升级状态 2:升级开始
			suc.ChangeProp("2",logpath,1,0,"","");
			if( runfilelist != null && runfilelist.length > 0 ) {
				//fuji 2011-1-4
				ResultSet rs = statement.executeQuery("select sqlfilename from SqlFileLogInfo");
				while(rs.next()){
					runSqlList.add(rs.getString(1));			  //已执行过的SQL脚本   TD20443
				}
				String errorfile  = suc.getErrorFile();
				int errorline  = suc.getErrorLine();
				logpath=suc.getUpgreadLogPath();

				for(int i=0;i<runfilelist.length-1;i++){
					for(int j=i+1;j<runfilelist.length;j++){
						if(runfilelist[i].compareTo(runfilelist[j])>0){
							String tmpfileList=runfilelist[i];
							runfilelist[i]=runfilelist[j];
							runfilelist[j]=tmpfileList;
						}
					}
				}
				
				if(rs != null){   // 关闭记录集   
			        try{   
			            rs.close() ;   
			        }catch(SQLException e){   
			            e.printStackTrace() ;   
			        } 
				}

				// 执行sql脚本，并将成功执行的脚本移动系统原有的脚本目录中去
				for(int i=0;i<runfilelist.length;i++){				   
					//hubo
					runFile = i+1;
					System.out.println("正在执行第("+(i+1)+")个脚本，共("+runfilelist.length+")个。");

					String sqlfilename = ConvertService.null2String(runfilelist[i]) ;
					String runsqlfile = newrunsqlfilepath + sqlfilename ;
					String runedsqlfile = sysrunsqlfilepath + sqlfilename ;
					filename = sqlfilename;
					// 如果不是sql脚本文件或者在原有系统中已经存在该升级脚本文件，直接删除			   
					if( sqlfilename.indexOf("sql") < 0 || (new File(runedsqlfile)).exists()) {
						FileManage.DeleteFile( runsqlfile ) ;
						continue ;
					}else if(runSqlList.contains(sqlfilename)){  // 增加判断，如果数据库（sql文件执行记录SqlFileLogInfo）中已存在记录,但data下没有该条记录也不执行，直接拷贝到data下；TD20443
						FileManage.moveFileTo(runsqlfile, runedsqlfile) ;
						continue ;
					}
					File fin = new File(runsqlfile);
					String sqlstr = "" ;
					String readline = null ;

					// BufferedReader is
					//= new BufferedReader(new InputStreamReader(new FileInputStream(fin)));
			  //指定升级脚本的文件流编码为GBK 避免依赖jdk的编码参数 导致在某些环境下 出现升级脚本乱码的问题
	  			BufferedReader is = new BufferedReader(new InputStreamReader(
	  						new FileInputStream(fin),"gbk"));
					
					int rownum=0;
					while ( ( readline = is.readLine() ) != null )   {
						readline = readline.trim() ;
						rownum++;
						//System.out.println(rownum+"+readline:"+readline);
						if(SysUpgradeCominfo.continueFlag && sqlfilename.equals(errorfile) && errorline >= rownum) {
							continue;
						}
						
						if(!readline.equalsIgnoreCase(scriptbreak)) sqlstr += " "+ readline ;
						else {
							try{
							statement.execute(sqlstr);
							}catch(Exception e){
								writeLog(e) ;
								//执行出错 需要记升级日志 并跳出循环
								FileManage.createDir(SYSUPGRADELOG) ;
								String logFilePath = SYSUPGRADELOG+currentDate+".log";
								PrintWriter outtext = new PrintWriter(new FileWriter(logFilePath,true));
								errorbuffer = new StringBuffer();
								outtext.println("错误时间");
								outtext.println("  "+this.currentTime);
								outtext.println("错误位置");
								errorbuffer.append("错误位置").append("<br>");
								outtext.println("  "+sqlfilename+"第"+rownum+"行:");
								errorbuffer.append("  "+sqlfilename+"第"+rownum+"行:").append("<br>");
								outtext.println("错误语句");
								errorbuffer.append("错误语句").append("<br>");
								outtext.println(sqlstr);
								errorbuffer.append(sqlstr).append("<br>");
								outtext.println("错误原因");
								errorbuffer.append("错误原因").append("<br>");
								outtext.println("  "+e.toString());
								String errormessage = e.toString();
								errormessage = errormessage.replaceAll("'","  ");
								errorbuffer.append("  "+errormessage).append("<br>");
								outtext.println("");
								outtext.println("");
								outtext.flush();
								outtext.close();
								//判断是不是已添加  已存在的报错  如果是则自动跳过 日志照常输出
								String error = e.toString();
								if(error.indexOf("已存在")>=0||error.indexOf("已存在")>=0||error.indexOf("多次指定了列名")>=0) {
									
								} else {
									 is.close();//关闭IO 否则在点击继续的时候文件被锁住了  无法进行删除和拷贝
									 //修改升级状态 1:脚本执行错误
									suc.ChangeProp("1",ConvertService.StringReplace(logFilePath,"\\","/"),pagestatus,rownum,sqlfilename,"");
									break procSqlEnd;
								}
							}						  
							//rs.executeSql(sqlstr) ;
							sqlstr = "" ;
						}
					}
					//记录已成功执行SQL文件集合 TD20443 fuji 2011-1-4
					statement.executeUpdate("insert into SqlFileLogInfo(sqlfilename,rundate,runtime)values('"+sqlfilename+"','"+currentDate+"','"+currentTime+"')");
					is.close();
					try{
						FileManage.moveFileTo(runsqlfile, runedsqlfile) ;
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				//修改升级状态 0:升级成功
				//判断脚本是否执行完成  脚本执行完成把状态pagestatus转台改成
				String runfilelistsize[] = rundir.list();
				if(runfilelistsize.length == 0) {
					Calendar calendar = Calendar.getInstance();
	  				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  				String time = format.format(calendar.getTime());
					suc.ChangeProp("0",logpath,0,0,"",time);
					//changebeanname();
				} else {
					 suc.ChangeProp("0",logpath,1,0,"","");
				}
			   
			  
				
			}else{
				//当升级过程中最后一个脚本报错的时候，由于 是手动的在数据库执行了；使得sqlupgrade目录查下的脚本
				//数量变成了0，导致upgrade.properties文件的状态没有被改过来
				int status = suc.getPagestatus();
				if(status == 1) {
					Calendar calendar = Calendar.getInstance();
	  				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  				String time = format.format(calendar.getTime());
					suc.ChangeProp("0",logpath,0,0,"",time);
					//changebeanname();
				}
			}

		}catch(Exception ex) {
			writeLog(ex) ;
		}finally{
			try {
				if(statement!=null){
					statement.close();
				}
				jdbc_conn.close();
			} catch (Exception e) {
				writeLog(e) ;
			}
		}
	}
	
}