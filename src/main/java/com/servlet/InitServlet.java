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
import com.util.SpringUtil;
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

public class InitServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074690113764069753L;
	
	private static ArrayList threadPool = new ArrayList();
	
	private String rootPath = System.getProperty("user.dir");
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
	//推送消息服务类
	@Autowired
	private MessageService messageService;

	@Autowired
	private PartnerService partnerService;


	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
		super();
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 */
	public void init() {
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
//		truckSetService = (TruckSetService) applicationContextProvider.getBean("truckSetService");
		BaseBean bs = new BaseBean();
		ConstantUtils.setRootPath(rootPath+"\\");
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
		ApplicationContext applicationContext = SpringUtil.getApplicationContext();
		goodsTypeService = applicationContext.getBean(GoodsTypeService.class);
		goodsTypeService.getAllGoodsType();

		driverService = applicationContext.getBean(DriverService.class);
		driverService.getAllDriverInfo();

		clientService = applicationContext.getBean(ClientService.class);
		clientService.getAllClient();

		messageService = applicationContext.getBean(MessageService.class);
		messageService.getAllMessage();
		messageService.getAllSendType();
		messageService.getAllWorkflowType();

		truckService = applicationContext.getBean(TruckService.class);
		truckService.getAllTruck();

		partnerService = applicationContext.getBean(PartnerService.class);
		partnerService.getAllPartner();

		feeTypeService = applicationContext.getBean(FeeTypeService.class);
		feeTypeService.getAllFeeType();
		/*  ---升级操作end---  */
		
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


}
