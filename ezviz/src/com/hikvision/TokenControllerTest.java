package com.hikvision;


import java.util.HashMap;
import java.util.Map;

public class TokenControllerTest extends PublicControllerTest {
    // 获取token范例入口
    
    public void getAccessToken() {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("phone", "13335313557");
        Map<String, Object> map = paramsInit("token/getAccessToken", paramsMap);
        doPost(map);
    }
    
    public static void main(String[] args)
    {
    	TokenControllerTest tokenControllerTest = new TokenControllerTest();
    	tokenControllerTest.getAccessToken();
    }

}
