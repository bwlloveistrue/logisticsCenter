package com.ln;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author lsh 获取mac信息
 */
public class GetPhysicalAddress{

	private static String cacheAddress = "";
	
	public String getPhysicalAddress() {
		// 判断是否有缓存
		if (cacheAddress != null && !"".equals(cacheAddress)) {
			return cacheAddress;
		} else {
			try {
				cacheAddress = getMACAddrByLsh();
				return cacheAddress;
			} catch (Exception e) {
				// 报错的话获取失败，采用第二种方案获取。
				cacheAddress = getMultiMacAddressByLsh();
				return cacheAddress;
			}
		}
	}

	/**
	 * 兼容蔡正龙的license的jar包
	 * 
	 * @return
	 * @author lsh
	 */
	public CopyOnWriteArrayList<String> getPhysicalAddressByCzl() {
		CopyOnWriteArrayList<String> addrList = new CopyOnWriteArrayList<String>();
		addrList.add(getPhysicalAddress());
		return addrList;
	}

	/**
	 * 修改
	 * 
	 * @return mac信息
	 * @author liangshenghai
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public String getMACAddrByLsh() throws SocketException,UnknownHostException {
		StringBuilder sb = new StringBuilder();
		
		NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		// 获得Mac地址的byte数组
		// UUID.randomUUID(); 取不到mac地址，使用前调用下这个类（jdk1.6的底层bug，暂时注释）
		byte[] macAddr = netInterface.getHardwareAddress();
		// 循环输出
		for (int i = 0; i < macAddr.length; i++) {
			sb.append(String.format("%02X%s", macAddr[i],(i < macAddr.length - 1) ? "-" : ""));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 暂时弃用，先保留
	 */
	private String toHexString(int integer) {
		// 将得来的int类型数字转化为十六进制数
		String str = Integer.toHexString((int) (integer & 0xff));
		// 如果遇到单字符，前置0占位补满两格
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 多网卡信息下获取
	 * 
	 * @author liangshenghai
	 * @return mac信息
	 */
	public String getMultiMacAddressByLsh() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			byte[] mac = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual()|| !netInterface.isUp()) {
					continue;
				} else {
					mac = netInterface.getHardwareAddress();
					if (mac != null) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X%s", mac[i],(i < mac.length - 1) ? "-" : ""));
						}
						if (sb.length() > 0) {
							return sb.toString();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "not found";
	}
}