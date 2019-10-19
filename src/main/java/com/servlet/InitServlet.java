package com.servlet;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cache.Cache;
import com.cache.CacheManager;
import com.logisticscenter.model.TruckEntity;
import com.logisticscenter.service.*;
import com.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.general.BaseBean;
import com.general.SystemUpgrade;
import com.javabean.ClientBean;
import com.javabean.DriverInfoBean;
import com.javabean.FeeTypeBean;
import com.javabean.GoodsTypeBean;
import com.javabean.TruckBean;
import com.javabean.TruckSetBean;

import com.util.ConstantUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074690113764069753L;
	
	private static ArrayList threadPool = new ArrayList();
	
	private String rootPath;
	//应用设置服务类
	@Autowired
	private TruckSetService truckSetService;
	//货物类型服务类
	@Autowired
	private GoodsTypeService goodsTypeService;
	//客户服务类
	@Autowired
	private ClientService clientService;
	//司机服务类
	@Autowired
	private DriverService driverService;
	//费用类型服务类
	@Autowired
	private FeeTypeService feeTypeService;
	//车辆服务类
	@Autowired
	private TruckService truckService;


	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
//		truckSetService = (TruckSetService) applicationContextProvider.getBean("truckSetService");
		BaseBean bs = new BaseBean();
      //首先执行升级操作,防止修改了spring映射关系但是数据库中没有对应字段
		/*  ---升级操作start---  */
		SystemUpgrade systemupgrade = new SystemUpgrade();
		Thread u = new Thread(systemupgrade);
		threadPool.add(u);
		u.start();

		try {
			u.join();
		} catch (InterruptedException e) {
			bs.writeLog(e);
		}
		/*  ---升级操作end---  */
//		//设置缓存<应用设置缓存>
//		ApplicationContext context1 = new ClassPathXmlApplicationContext(
//				new String[] { "applicationContext_iBatis.xml" });
//		//设置缓存<应用设置缓存>
//		truckSetService = (TruckSetService) context1.getBean("truckSetService");
//		if(CacheManager.getCacheInfo("truckSettingBean_CACHE")!=null){
//			;
//		}else{
////			TruckSetBean bean= truckSetService.getTruckSet(null);
//			TruckSetBean bean= new TruckSetBean();
//			Cache cache = new Cache();
//			Date date = new Date();
//			//设置应用设置缓存
//			cache.setKey("truckSetting");
//			cache.setTimeOut(date.getTime());
//			cache.setValue(bean);
//			CacheManager.putCache("truckSettingBean_CACHE", cache);
//		}
//
//		//设置缓存<货物类型设置缓存>
//		goodsTypeService = (GoodsTypeService) context1.getBean("goodsTypeService");
//		if(CacheManager.getCacheInfo("goodsTypeBean_CACHE")!=null){
//			;
//		}else{
//			List<GoodsTypeBean> beanLst= goodsTypeService.getAllGoodsType();
//			Cache cache = null;
//			Date date = new Date();
//			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			//货物类型设置缓存
//			for(int i = 0;i<beanLst.size();i++){
//				cache = new Cache();
//				cache.setKey(beanLst.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(beanLst.get(i));
//				beanCacheLst.add(cache);
//			}
//			CacheManager.putCacheList("goodsTypeBean_CACHE", beanCacheLst);
//		}
//
//		//设置缓存<客户设置缓存>
//		clientService = (ClientService) context1.getBean("clientService");
//		if(CacheManager.getCacheInfo("clientBean_CACHE")!=null){
//			;
//		}else{
////			List<ClientBean> beanLst= clientService.getAllClient();
//			List<ClientBean> beanLst = new ArrayList<ClientBean>();
//			Cache cache = null;
//			Date date = new Date();
//			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			//货物类型设置缓存
//			for(int i = 0;i<beanLst.size();i++){
//				cache = new Cache();
//				cache.setKey(beanLst.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(beanLst.get(i));
//				beanCacheLst.add(cache);
//			}
//			CacheManager.putCacheList("clientBean_CACHE", beanCacheLst);
//		}
//
//		//设置缓存<司机设置缓存>
//		driverService = (DriverService) context1.getBean("driverService");
//		if(CacheManager.getCacheInfo("driverBean_CACHE")!=null){
//			;
//		}else{
//			List<DriverInfoBean> beanLst= driverService.getAllDriverInfo();
//			Cache cache = null;
//			Date date = new Date();
//			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			//司机设置缓存
//			for(int i = 0;i<beanLst.size();i++){
//				cache = new Cache();
//				cache.setKey(beanLst.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(beanLst.get(i));
//				beanCacheLst.add(cache);
//			}
//			CacheManager.putCacheList("driverBean_CACHE", beanCacheLst);
//		}
//
//		//设置缓存<司机设置缓存>
//		feeTypeService = (FeeTypeService) context1.getBean("feeTypeService");
//		if(CacheManager.getCacheInfo("feeTypeBean_CACHE")!=null){
//			;
//		}else{
//			List<FeeTypeBean> beanLst= feeTypeService.getAllFeeType();
//			Cache cache = null;
//			Date date = new Date();
//			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			String feeTypeColumns = "";
//			String feeTypeNames = "";
//			//货物类型List设置缓存
//			for(int i = 0;i<beanLst.size();i++){
//				cache = new Cache();
//				cache.setKey(beanLst.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(beanLst.get(i));
//				beanCacheLst.add(cache);
//				if(beanLst.get(i).getIsUse() == 0 || beanLst.get(i).getShowType() == 3) continue;
//				feeTypeColumns+=","+beanLst.get(i).getFeeTypeColumn();
//				feeTypeNames +=","+beanLst.get(i).getFeeName();
//			}
//			if(feeTypeColumns.length()>0){
//				ConstantUtils.setFeeTypeColumns(feeTypeColumns.substring(1));
//				ConstantUtils.setFeeTypeNames(feeTypeNames.substring(1));
//			}
//			CacheManager.putCacheList("feeTypeBean_CACHE", beanCacheLst);
//		}
//
//		//设置缓存<司机设置缓存>
//		truckService = (TruckService) context1.getBean("truckService");
//		if (CacheManager.getCacheInfo("truckEntity_CACHE")!=null){
//			;
//		}else{
//			Map truckInfoMap= truckService.getTruck(new HashMap());
//			List<TruckEntity> entityList = (List<TruckEntity>)truckInfoMap.get("data");
//			Cache cache = null;
//			Date date = new Date();
//			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			//货物类型List设置缓存
//			for(int i = 0;i<entityList.size();i++){
//				cache = new Cache();
//				cache.setKey(entityList.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(entityList.get(i));
//				beanCacheLst.add(cache);
//			}
//			CacheManager.putCacheList("truckBean_CACHE", beanCacheLst);
//		}
		
		
	}
	
	/**
     * 获取本机IP列表（包含内网ip，外网ip），存储在集合中<br>
     * @return
     * @throws SocketException
     */
     public static ArrayList<String> getRealIp()  {
        String localip = null; // 本地IP
        String netip = null;  // 外网IP
        ArrayList<String> ips = new ArrayList<String>();//用于存储所有获取到的本机IP
        
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        } //返回此机器上的所有网络接口，如果所有网络接口都被禁止 则返回null
        InetAddress ip = null;
        while (netInterfaces.hasMoreElements()) { // 遍历多网卡
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses(); //获取所有的inetaddresses或绑定到该网络接口的子集枚举对象
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP(三个条件分别为 不为局域网IP，不为环路IP,不为ipv6 IP)
                    netip = ip.getHostAddress();
                    if(netip != null && !"".equals(netip)){
                        ips.add(netip);
                    }
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP(三个条件分别为 是局域网IP，不是环路IP，不是ipv6 Ip)
                    localip = ip.getHostAddress();
                    ips.add(localip);
                }
            }
        }
        return ips;
    }
     
     /**
      * 实现 HttpServlet 的 doGet 方法，不作任何操作
      *
      * @param request
      * @param response
      * @return void
      * @throws ServletException, IOException
      */
 	public void doGet(HttpServletRequest request, HttpServletResponse response)
 			throws ServletException, IOException {

 		doPost(request, response);
 	}

 	/**
     * 实现 HttpServlet 的 doPost 方法，不作任何操作
     *
     * @param request
     * @param response
     * @return void
     * @throws ServletException, IOException
     */
 	public void doPost(HttpServletRequest request, HttpServletResponse response)
 			throws ServletException, IOException {
 	}
 	
 	public static ArrayList getThreadPool(){
        return threadPool;
    }

    public ServletConfig getConfig(){
		ServletConfig config = this.getServletConfig();
		return config;
	}

}
