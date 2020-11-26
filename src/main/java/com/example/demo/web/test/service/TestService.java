package com.example.demo.web.test.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.file.CsvFileReader;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.web.test.mapper.TestMapper;

@Service
public class TestService {

	@Autowired
	TestMapper testMapper;
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class})
	public Map<String, Object> registPerfectwin(Map<String,Object> param) throws Exception {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		
		List<HashMap<String,Object>> listData = CsvFileReader.readCSV();
		
		int i=0;
		for(HashMap<String,Object> data:listData) {
			//System.out.println(data.toString());
			
			if(i> 0 ) {
				testMapper.insertPerfectwin(data);	
			}
			
			i++;
		}
		
	
		rsltMap.put("RSLT","SUCC");
		rsltMap.put("MSG","등록 성공 하였습니다.");
		rsltMap.put("RSLT_CODE","S0000");
		
		return rsltMap;
		
	}
	
	
	public Map<String, Object> doPerfectwinCall(Map<String,Object> param) throws Exception {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		
		ArrayList<HashMap<String,Object>> pList= testMapper.selectPerfectwinList(param);
		
		for(HashMap<String,Object> pData:pList) {
			//System.out.println(data.toString());
			
			String tuid			= StringUtils.defaultIfEmpty((String)pData.get("tuid"), "");
			String tobeMsgId	= StringUtils.defaultIfEmpty((String)pData.get("tobe_msg_id"), "");
			
			String paramMsg		= StringUtils.defaultIfEmpty((String)pData.get("ref1"), "");
			String ref3			= StringUtils.defaultIfEmpty((String)pData.get("ref3"), "");
			String method		= StringUtils.defaultIfEmpty((String)pData.get("ref4"), "");
			String url   		= "http://localhost:7001"+StringUtils.defaultIfEmpty((String)pData.get("ref5"), "");
			String accept 		= StringUtils.defaultIfEmpty((String)pData.get("ref6"), "");
			String contentType	= StringUtils.defaultIfEmpty((String)pData.get("ref7"), "");
					
			HashMap<String,String> hParam = new HashMap<String,String>();
			hParam.put("paramMsg", paramMsg);
			hParam.put("accept", accept);
			hParam.put("contentType", contentType);
			
			HashMap<String,Object> hRslt = null; 
					
			if(method.equals("GET")) {
				
				hRslt = HttpClientUtil.doGet(url, hParam);
				
			}else if(method.equals("POST")) {
				hRslt = HttpClientUtil.doPost(url, hParam);	
			}
			
			if(hRslt != null) {
				
				String resBody			= StringUtils.defaultIfEmpty((String)hRslt.get("res_body"), "");
				String resStatusCode	= StringUtils.defaultIfEmpty((String)hRslt.get("res_status_code"), "");
				
				
				HashMap<String,Object> pRslt = new HashMap<String,Object>(); 	
				pRslt.put("tuid", tuid);
				pRslt.put("tobe_msg_id", tobeMsgId);
				pRslt.put("ref3", ref3);
				pRslt.put("res_status_code", resStatusCode);
				pRslt.put("resBody", resBody);
				
				//System.out.println(pRslt.toString());
				
				testMapper.insertPerfectwinRslt(pRslt);
				
			}
			
		}
		
		rsltMap.put("RSLT","SUCC");
		rsltMap.put("MSG","실행 성공 하였습니다.");
		rsltMap.put("RSLT_CODE","S0000");
		
		return rsltMap;
		
	}
	
	
	
	public Map<String, Object> parseSqlx(Map<String,Object> param) throws Exception {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		
		
		
		rsltMap.put("RSLT","SUCC");
		rsltMap.put("MSG","실행 성공 하였습니다.");
		rsltMap.put("RSLT_CODE","S0000");
		
		return rsltMap;
		
	}
	
	
	
	
	
}
