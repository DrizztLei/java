package com.hikvision;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;

public class PublicControllerTest {
	protected Map<String, Object> paramsInit(String method, Map<String, Object> paramsMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		long time = System.currentTimeMillis() / 1000;

		/** 测试用(开发者需换成自己的appkey和secret) */
		String key = "c295e6dc623e4f03a0d915e43bdb0ae3";
		String secret = "ccb7eaa12d2b3f6108c3bb4657bc92b1";

		StringBuilder paramString = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
			String key1 = it.next();
			String param = key1 + ":" + paramsMap.get(key1);
			paramList.add(param);
		}
		String[] params = paramList.toArray(new String[paramList.size()]);
		Arrays.sort(params);
		for (String param : params) {
			paramString.append(param).append(",");
		}
		paramString.append("method").append(":").append(method).append(",");
		paramString.append("time").append(":").append(time).append(",");
		paramString.append("secret").append(":").append(secret);
		//System.out.println(paramString.toString().trim());

		String sign = null;
		try {
			sign = DigestUtils.md5Hex(paramString.toString().trim().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Map<String, Object> systemMap = new HashMap<String, Object>();
		systemMap.put("ver", "1.0");
		systemMap.put("sign", sign);
		systemMap.put("key", key);
		systemMap.put("time", time);

		map.put("system", systemMap);
		map.put("method", method);
		map.put("params", paramsMap);
		map.put("id", "4895367d1b82cbcf");
		return map;
	}

	protected void doPost(Map<String, Object> map) {
		String json = JSON.toJSONString(map);
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient();
		// 使用POST方法
		PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
		
		try {
			RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
			method.setRequestEntity(entity);
			client.executeMethod(method);

			InputStream inputStream = method.getResponseBodyAsStream();
			String restult = IOUtils.toString(inputStream);
			System.out.println(restult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			method.releaseConnection();
		}
	}

}
