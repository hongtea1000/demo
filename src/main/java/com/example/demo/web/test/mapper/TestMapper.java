package com.example.demo.web.test.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	public void insertPerfectwin(Map<String,Object> param) throws Exception;
	
	public ArrayList<HashMap<String,Object>> selectPerfectwinList(Map<String,Object> param) throws Exception;
	
	public ArrayList<HashMap<String,Object>> selectDistinctPerfectwinList(Map<String,Object> param) throws Exception;
	
	public void insertPerfectwinRslt(Map<String,Object> param) throws Exception;
	
}
