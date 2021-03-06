﻿import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpInvoker {

	public static String httpGet(String getUrl) throws IOException {
		URL getURL = new URL(getUrl);
		HttpURLConnection connection = (HttpURLConnection) getURL
				.openConnection();
		connection.connect();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			sbStr.append(line);
		}
		bufferedReader.close();
		connection.disconnect();
		return new String(sbStr.toString().getBytes(), "utf-8");
	}

	public static String httpPost(String postUrl,
			Map<String, String> postHeaders, String postEntity)
			throws IOException {

		URL postURL = new URL(postUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) postURL
				.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setInstanceFollowRedirects(true);
		httpURLConnection.setRequestProperty(" Content-Type ",
				" application/x-www-form-urlencoded ");

		if (postHeaders != null) {
			for (String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey,
						postHeaders.get(pKey));
			}
		}
		if (postEntity != null) {
			DataOutputStream out = new DataOutputStream(
					httpURLConnection.getOutputStream());
			out.writeBytes(postEntity);
			out.flush();
			out.close(); // flush and close
		}
		// connection.connect();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(httpURLConnection.getInputStream()));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			sbStr.append(line);
		}
		bufferedReader.close();
		httpURLConnection.disconnect();
		return new String(sbStr.toString().getBytes(), "utf-8");
	}

	public static void main(String[] args) {
		String resultString;
		try {
			resultString = HttpInvoker
					.httpPost(
							"http://kk.bigk2.com:8080/KOAuthDemeter/accessToken",
							null,
							"grant_type=authorization_code&client_id=4yCOi48I687EN2Ix&client_secret=1K742MBPCd5LAL24&redirect_uri=www.baidu.com");
			System.out.println(resultString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
