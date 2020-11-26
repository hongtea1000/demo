package com.example.demo.util;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientUtil {

	final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	
	public static HashMap<String,Object> doPost(String url, HashMap<String,String> param) {

		HashMap<String,Object> rslt = new HashMap<String,Object>();
		
		try {
			
			String accept = StringUtils.defaultIfEmpty((String)param.get("accept"), "");
			String contentType = StringUtils.defaultIfEmpty((String)param.get("contentType"), "");
			String paramMsg = StringUtils.defaultIfEmpty((String)param.get("paramMsg"), "");
			
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 积己
			
			HttpPost postRequest = new HttpPost(url); //POST 皋家靛 URL 货己 
			postRequest.setHeader("Accept", accept);
			postRequest.setHeader("Connection", "keep-alive");
			postRequest.setHeader("Content-Type", contentType);
						
			postRequest.setEntity(new StringEntity(paramMsg,"UTF-8")); 

			HttpResponse response = client.execute(postRequest);

			rslt.put("res_status_code", Integer.toString(response.getStatusLine().getStatusCode()));
			
			//Response 免仿
			if (response.getStatusLine().getStatusCode() == 200) 
			{
				ResponseHandler<String> handler = new BasicResponseHandler();
				rslt.put("res_body", handler.handleResponse(response));
				
			} else {
				logger.error("response is error : " + response.getStatusLine().getStatusCode());
			}

		} catch (Exception e){
			logger.error("HttpClientUtil Error ",e);
		}
		
		return rslt;
		
	}
	
	
	
	public static HashMap<String,Object> doGet(String url, HashMap<String,String> param) {

		
		HashMap<String,Object> rslt = new HashMap<String,Object>();
		
		try {
			
			String accept = StringUtils.defaultIfEmpty((String)param.get("accept"), "");
			String contentType = StringUtils.defaultIfEmpty((String)param.get("contentType"), "");
			
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 积己
			
			HttpGet getRequest = new HttpGet(url); //POST 皋家靛 URL 货己 
			getRequest.setHeader("Accept", accept);
			getRequest.setHeader("Connection", "keep-alive");
			getRequest.setHeader("Content-Type", contentType);
			
			HttpResponse response = client.execute(getRequest);

			rslt.put("res_status_code", Integer.toString(response.getStatusLine().getStatusCode()));
			
			
			//Response 免仿
			if (response.getStatusLine().getStatusCode() == 200) 
			{
				ResponseHandler<String> handler = new BasicResponseHandler();
				rslt.put("res_body", handler.handleResponse(response));
				
			} else {
				logger.error("response is error : " + response.getStatusLine().getStatusCode());
			}

		} catch (Exception e){
			logger.error("HttpClientUtil Error ",e);
		}
		
		return rslt;
		
	}
	
	
}
