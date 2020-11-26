package com.example.demo.web.test.controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.web.test.service.TestService;

@RestController
@EnableAutoConfiguration
public class TestController {

	final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/welcome")
	public String welcome() { 
		return "welcome"; 
	}
	
	@RequestMapping(value="/registPerfectwin", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Map<String, Object> registPerfectwin(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String,Object> param) {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		try {
			
			logger.info(">> registPerfectWin param : " + param.toString());
					
			rsltMap = testService.registPerfectwin(param);
			
		} catch(Exception e) {
			logger.error("!!! registPerfectWin Error Occur", e);
			
			rsltMap.put("RSLT","FAIL");
			rsltMap.put("MSG","시스템 에러 발생하였습니다.");
			rsltMap.put("RSLT_CODE","E9999");
			
		}finally {
			logger.info("<< registPerfectWin rsltMap : " + rsltMap.toString());
		}
		
		return rsltMap;
		
	}
	
	
	@RequestMapping(value="/doPerfectwinCall", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Map<String, Object> doPerfectwinCall(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String,Object> param) {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		try {
			
			logger.info(">> doPerfectwinCall param : " + param.toString());
					
			rsltMap = testService.doPerfectwinCall(param);
			
		} catch(Exception e) {
			logger.error("!!! doPerfectwinCall Error Occur", e);
			
			rsltMap.put("RSLT","FAIL");
			rsltMap.put("MSG","시스템 에러 발생하였습니다.");
			rsltMap.put("RSLT_CODE","E9999");
			
		}finally {
			logger.info("<< doPerfectwinCall rsltMap : " + rsltMap.toString());
		}
		
		return rsltMap;
		
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Map<String, Object> parseSqlx(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String,Object> param) {
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		try {
			
			logger.info(">> parseSqlx param : " + param.toString());
					
			rsltMap = testService.parseSqlx(param);
			
		} catch(Exception e) {
			logger.error("!!! parseSqlx Error Occur", e);
			
			rsltMap.put("RSLT","FAIL");
			rsltMap.put("MSG","시스템 에러 발생하였습니다.");
			rsltMap.put("RSLT_CODE","E9999");
			
		}finally {
			logger.info("<< parseSqlx rsltMap : " + rsltMap.toString());
		}
		
		return rsltMap;
		
	}
	
	
	
	
}
