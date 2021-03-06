package com.hikvision;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import com.alibaba.fastjson.JSONObject;

public class TimeControllerTest {


    private static final String TEST_HOST = "https://open.ys7.com:443/api/";

    // 获取服务器时间范例入口
    
    public void test() {
        List<NameValuePair> pairsList = new ArrayList<NameValuePair>();
        /** 测试用(开发者需换成自己的appkey) */
        pairsList.add(new NameValuePair("appKey", "c295e6dc623e4f03a0d915e43bdb0ae3"));

        sendHttpRequest(pairsList, "time/get");
        
    }


    protected static JSONObject sendHttpRequest(List<NameValuePair> pairsList, String method) {
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(TEST_HOST + method);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        NameValuePair[] valuePairs = pairsList.toArray(new NameValuePair[0]);
        postMethod.setRequestBody(valuePairs);
        JSONObject object = null;

        try {
            httpClient.executeMethod(postMethod);
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            String returnReult = IOUtils.toString(inputStream);

            System.out.println(returnReult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return object;
    }
}
