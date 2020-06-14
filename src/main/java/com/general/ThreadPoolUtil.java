/*
 *
 * Copyright (c) 2001-2016 泛微软件.
 * 泛微协同商务系统,版权所有.
 * 
 */
package com.general;

import com.util.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池共通方法
 * 
 */
public class ThreadPoolUtil extends BaseBean {

	/**
	 * 默认线程名称
	 */
	private static String THREAD_DEFAULT = "THREAD_DEFAULT";
	/**
	 * 线程池环境变量
	 */
	private static ExecutorService fixedThreadPool = null;
	/**
	 * 存储线程的集合
	 */
	private static Map<String, ExecutorService> cacheMap = new ConcurrentHashMap<String, ExecutorService>();

	/**
	 * @param key 线程名称
	 * @param size 固定线程池线程数量
	 * @return 线程
	 */
	public static ExecutorService getThreadPool(String key, String size) {
		if (null == key || key.equals("")) {
			key = THREAD_DEFAULT;
			if (!cachePoolExits(THREAD_DEFAULT)) {
				newPool(THREAD_DEFAULT, "5");
			}
			return getCachePool(key);
		}
		if (cachePoolExits(key)) {
			return getCachePool(key);
		} else {
			newPool(key, size);
			return getCachePool(key);
		}

	}

	/**
	 * @param key 线程名称
	 * @param size 固定线程池线程数量
	 */
	public synchronized static void newPool(String key, String size) {
		try {
			if (!cachePoolExits(key)) {
				int threadcount = 0;
				if (Utils.getIntValue(size) > 0) {
					threadcount = Integer.parseInt(size);
				} else {
					threadcount = Utils.getIntValue(Utils
							.null2String((new ThreadPoolUtil()).getPropValue(
									"ThreadPoolConfig", "threadcount")));
					if (threadcount < 5) {
						threadcount = 5;
					}
				}
				fixedThreadPool = Executors.newFixedThreadPool(threadcount);
				cacheMap.put(key, fixedThreadPool);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param key 线程名称
	 * @return 是否存在该线程
	 */
	public synchronized static boolean cachePoolExits(String key) {
		return cacheMap.containsKey(key);
	}

	/**
	 * @param key 线程名称
	 * @return 对应key的线程
	 */
	public synchronized static ExecutorService getCachePool(String key) {
		return (ExecutorService) cacheMap.get(key);
	}

	/**
	 * @return fixedThreadPool
	 */
	public ExecutorService getFixedThreadPool() {
		return fixedThreadPool;
	}

	/**
	 * @param fixedThreadPool 线程池
	 */
	public void setFixedThreadPool(ExecutorService fixedThreadPool) {
		ThreadPoolUtil.fixedThreadPool = fixedThreadPool;
	}
	
	/**
	 * @return 获得线程池集合
	 */
	public static Map<String,ExecutorService>  getCacheMap() {
		return cacheMap;
	}

	public int getThreadPlooSize(String key){
		int count = 0;
		if(cacheMap.containsKey(key)){
			ExecutorService executorService = cacheMap.get(key);
			count = ((ThreadPoolExecutor)executorService).getActiveCount();
		}

		return count;
	}

}
