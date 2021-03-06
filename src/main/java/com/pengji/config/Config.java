package com.pengji.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.jfinal.core.Controller;
import com.pengji.aop.InjectManager;
import com.pengji.utils.EmptyUtil;
import com.pengji.utils.NumberUtils;
import com.pengji.utils.PathUtils;

public class Config {
	private final static URL classPathUrl = Config.class.getResource("/");
	private final static String classPath = new File(classPathUrl.getFile()).getPath();
	private static String configPath = "/conf/";
	private static Map<String, String> configMap = new HashMap<String, String>();
	private static Map<String, String> argMap = new HashMap<String, String>();
	
	static {
		setConfigMap();
	}
	
	public static String getStr(String key) {
		if (configMap.size() < 0 && argMap.size() < 0) {
			return null;
		}
		String value = argMap.get(key);
		
		if(EmptyUtil.isNullOrEmpty(value)){
			value = configMap.get(key);
		}
		
		return configMap.get(key);
	}

	public static int getToInt(String key) {
		String val = getStr(key);
		return NumberUtils.parseInt(val);
	}

	public static long getToLong(String key) {
		String val = getStr(key);
		return NumberUtils.parseLong(val);
	}
	
	public static double getToDbl(String key) {
		String val = getStr(key);
		return NumberUtils.parseDbl(val);
	}

	public static boolean getToBoolean(String key) {
		String val = getStr(key);
		try {
			return Boolean.valueOf(val);
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * 修改目录，配置重构
	 * 
	 * 2016年6月2日 下午3:27:29
	 * flyfox 330627517@qq.com
	 * @param configPath
	 */
	public static void rebuild(String configPath){
		Config.configPath = configPath;
		rebuild();
	}
	
	/**
	 * 配置重构
	 * 
	 * 2016年6月2日 下午3:27:13
	 * flyfox 330627517@qq.com
	 */
	public static void rebuild(){
		setConfigMap();
	}

	public static void test() {
		for (String key : configMap.keySet()) {
			System.out.println(key + "=" + configMap.get(key));
		}
	}
	
	private static void setConfigMap() {
		String filePath = classPath + configPath;
		filePath = PathUtils.rebuild(filePath);
		List<String> list = findFiles(filePath);
		
		Map<String, String> tmpConfigMap = new HashMap<String, String>();
		for (String configName : list) {
			Properties props = getProperties(filePath + configName);
			Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				if (!tmpConfigMap.containsKey(key)) {
					tmpConfigMap.put(key, value);
				} else {
					System.err.println("CONFIG EEOR:key(" + key + ") is exist;");
				}
			}
		}
		
		Config.configMap = tmpConfigMap;
	}

	/**
	 * 获取Properties文件
	 * 
	 * 2014年7月5日 上午12:23:14 flyfox 330627517@qq.com
	 * 
	 * @param file
	 * @return
	 */
	private static Properties getProperties(String fileName) {
		Properties props = new Properties();
		try {
			java.io.InputStream propFile = new FileInputStream(fileName);
			props.load(propFile);
		} catch (IOException e) {
			System.err.println("file read fail:" + fileName);
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 查找当前文件下所有properties文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 */
	private static List<String> findFiles(String baseDirName) {
		List<String> configFiles = new ArrayList<String>();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + " is not a dir！");
		} else {
			String[] filelist = baseDir.list();
			for (String fileName : filelist) {
				if (fileName.endsWith("properties")) {
					configFiles.add(fileName);
				}
			}
		}
		return configFiles;
	}
	
    public static void setStartArg(String key, Object value) {
        argMap.put(key, value.toString());
    }

    /**
     * 获取启动参数
     *
     * @param key
     * @return
     */
    public static String getStartArg(String key) {
        if (argMap == null) return null;
        return argMap.get(key);
    }

    public static Map<String, String> getStartArgs() {
        return argMap;
    }

    /**
     * 获取配置信息
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T config(Class<T> clazz) {
        return ConfigManager.me().get(clazz);
    }

    /**
     * 读取配置文件信息
     *
     * @param clazz
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> T config(Class<T> clazz, String prefix) {
        return ConfigManager.me().get(clazz, prefix);
    }
    
    /**
     * 获取被增强的，可以使用AOP注入的实体类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T bean(Class<T> clazz) {
        return InjectManager.me().getInjector().getInstance(clazz);
    }


    /**
     * 对某个对象内部的变量进行注入
     *
     * @param object
     */
    public static void injectMembers(Object object) {
        InjectManager.me().getInjector().injectMembers(object);
    }

    private static BiMap<String, Class<? extends Controller>> controllerMapping = HashBiMap.create();

    public static Class<? extends Controller> getControllerByPath(String path) {
        return controllerMapping.get(path);
    }

    public static String getPathByController(Class<? extends Controller> controllerClass) {
        return controllerMapping.inverse().get(controllerClass);
    }

    public static void setMapping(String path, Class<? extends Controller> controllerClass) {
        controllerMapping.put(path, controllerClass);
    }

}
