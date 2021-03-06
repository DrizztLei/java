﻿import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Demo {

	private static Gson gson = new Gson();

	final static String url0 = buildURL("UserInfo", "");

	final static String url1 = buildURL("User", "getAvatar");

	final static String url2 = buildURL("User", "getKList");

	final static String url3 = buildURL("KControl", "doSwitchK");

	final static String url4 = buildURL("KInfo", "getKTimerList");

	final static String url5 = buildURL("KInfo", "getKElectricity");

	final static String url6 = buildURL("KInfo", "getKDelayOpenInfo");

	final static String url7 = buildURL("KInfo", "getKState");

	final static String url8 = buildURL("User", "getKInfo");

	final static String url9 = buildURL("User", "switchKLight");

	final static String url10 = buildURL("User", "getKLightInfo");

	final static String url11 = buildURL("User", "getKSceneList");

	final static String url12 = buildURL("KInfo", "getKDelayCloseInfo");

	final static String url13 = buildURL("User", "getGeneralRemoteList");

	final static String url14 = buildURL("KControl", "sendGeneralRemoteOrder");

	final static String url15 = buildURL("User", "getAirConditionerRemoteList");

	final static String url16 = buildURL("KControl", "sendAirConditionerOrder");

	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param access_token
	 *            访问需要携带的access_token
	 * @param msg
	 *            请求参数内容 json字符串,例如: "{ \"userid\":\"0123456789\" }"
	 * @param info
	 *            请求附加信息，非必须，用于控制台打印
	 * @return 成功:返回json字符串
	 */
	
	public static String post(String strURL, String access_token, String msg,
			String info) {
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setRequestProperty("Accept-Encoding", "identity");
			// 将token放到头部
			connection.setRequestProperty("Authorization", "Bearer "
					+ access_token);

			connection.connect();

			// 向服务器POST信息
			if (null != msg && msg.length() > 0) {
				OutputStreamWriter out = new OutputStreamWriter(
						connection.getOutputStream(), "UTF-8"); // 服务器采用UTF-8编码
				out.append(msg);
				out.flush();
				out.close();
			}

			// 读取服务器响应(最大长度10K)
			int length = 10 * 1024;
			// int length =connection.getContentLength();// 获取长度,这里一直返回0,不知道什么原因
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];// 每次读取512字节
				int readLen = 0;// 单次读取的长度
				int destPos = 0;// 总字节数
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // 响应也是UTF-8编码
				System.out.println(info + "-服务器返回结果：" + result);
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

	/**
	 * 组装请求URL
	 * 
	 * @param in
	 *            接口名称
	 * @param mn
	 *            方法名称
	 * @return 组装的URL
	 */
	private static String buildURL(String in, String mn) {
		StringBuffer sb = new StringBuffer(
				"http://kk.bigk2.com:8080/KOAuthDemeter/");
		if (null != in && in.length() > 0) {
			sb.append(in);
		}

		if (null != mn && mn.length() > 0) {
			sb.append("/");
			sb.append(mn);
		}
		return sb.toString();
	}

	/**
	 * 依次测试所有接口
	 * 
	 * @param access_token
	 *            获取到的access_token
	 * @param msg
	 *            请求参数的Json字符串
	 * @param threadName
	 *            线程名称，随便输入
	 */
	private static void test(String access_token, String msg, String threadName) {

		//post(url0, access_token, null, threadName + "#getUserId");
		
		// post(url1, access_token, msg, threadName + "#getAvatar");
		// post(url2, access_token, msg, threadName + "#getKList");
		post(url3, access_token, msg, threadName + "#doSwitchK");
		// post(url4, access_token, msg, threadName + "#getKTimerList");
		// post(url5, access_token, msg, threadName + "#getKElectricity");
		// post(url6, access_token, msg, threadName + "#getKDelayOpenInfo");
		// post(url7, access_token, msg, threadName + "#getKState");
		// post(url8, access_token, msg, threadName + "#getKInfo");
		// post(url9, access_token, msg, threadName + "#switchKLight");
		// post(url10, access_token, msg, threadName + "#getKLightInfo");
		/*
		post(url11, access_token, msg, threadName + "#getKSceneList");
		// post(url12, access_token, msg, threadName + "#getKDelayCloseInfo");
		post(url13, access_token, msg, threadName + "#getGeneralRemoteList");
		// post(url14, access_token, msg, threadName +
		// "#sendGeneralRemoteOrder");
		post(url15, access_token, msg, threadName
				+ "#getAirConditionerRemoteList");
				*/
		// post(url16, access_token, msg, threadName +
		// "#sendAirConditionerOrder");
	}

	public static void main(String[] args) {

		String access_token = "155bbd381a287e9b505e987379fa685e";
		
		// 请求参数。因为只是一个DEMO，此处是将所有参数(不管是用到还是没用到)放到一起传
		/* 实际操作中不建议这么做 */
		RequestParamsBean bean = new RequestParamsBean();

		// 不操作或者获取具体某个小K时，此参数可不填
		bean.setKey("open");// 开关状态，只能为open或者close

		bean.setKid("0");// 不操作或者获取具体某个小K时，此参数可不填

		bean.setUserid("elvis.linuxer@gmail.com");// 此参数必须

		// 操作普通红外(type=1)或者射频(type=1)的参数
		bean.setRemoteType(1);
		bean.setOrder("order");// 遥控器名称

		// 操作空调红外的参数
		//bean.setBaseOrder("此处替换成你获取到的空调红外基指令，格式类似于TOSHIBA/Midea&1");// 空调红外基指令
		bean.setBaseOrder("base order");
		//bean.setExtraOrder("此处替换成你想设置的空调状态，格式类似于1.1.3.18，指令含义和范围要求请参照文档");// 空调红外参数
		bean.setExtraOrder("extra order");

		String msg = gson.toJson(bean);// 将参数Json化，此处我采用的是Gson
		// System.out.println("msg=" + msg);
		String threadName = Thread.currentThread().getName();// 获取当前线程名称
		test(access_token, msg, threadName);
	}

}
