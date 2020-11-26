package com.example.demo.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CsvFileReader {
	
	
	
	public static void main(String[] args){
		
		
		List<HashMap<String,Object>> allData =  readCSV();
		
//        //출력 스트림 생성
//        BufferedWriter bufWriter = null;
//        try{
//            //bufWriter = Files.newBufferedWriter(Paths.get("D:\\MyPjt\\csv\\perfectwin3.csv"),Charset.forName("UTF-8"));
//            
//            //csv파일 읽기
//            List<List<String>> allData = readCSV();
//            
//            for(List<String> newLine : allData){
//                List<String> list = newLine;
//                for(String data : list){
//                    bufWriter.write(data);
//                    bufWriter.write(",");
//                }
//                //추가하기
//                //bufWriter.write("주소");
//                //개행코드추가
//                //bufWriter.newLine();
//            }
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }catch(IOException e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(bufWriter != null){
//                    bufWriter.close();
//                }
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
    }
    
    public static List<HashMap<String,Object>> readCSV(){
    	
    	System.out.println("start readCSV ");
        //반환용 리스트
        List<HashMap<String,Object>> ret = new ArrayList<HashMap<String,Object>>();
        BufferedReader br = null;
        HashMap<String,Object> dataRslt = null;
        
        try{
            br = Files.newBufferedReader(Paths.get("D:/MyPjt/csv/test.sql"));
            //Charset.forName("UTF-8");
            String line = "";
            
            
            while((line = br.readLine()) != null){
            	
            	dataRslt = new HashMap();
            	//CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                //배열에서 리스트 반환
                
                String proc_type = StringUtils.substringBefore(array[2], ";");
                		
                dataRslt.put("proc_start_time", array[0]);
                dataRslt.put("tuid", array[1]);
                dataRslt.put("tobe_msg_id", array[2]);
                dataRslt.put("auid", array[3]);
                dataRslt.put("asis_raw_file_loc", array[4]);
                dataRslt.put("tobe_raw_file_loc", array[5]);
                dataRslt.put("asis_http_cd", array[6]);
                dataRslt.put("tobe_http_cd", array[7]);
                dataRslt.put("pre_proc_status", array[8]);
                dataRslt.put("result_status", array[9]);
                dataRslt.put("result_dt", array[10]);
                dataRslt.put("proc_type", proc_type);
                
                
//                System.out.println("proc_start_time : " + array[0]);
//                System.out.println("tuid : " + array[1]);
//                System.out.println("tobe_msg_id : " + array[2]);
//                System.out.println("auid : " + array[3]);
//                System.out.println("asis_raw_file_loc : " + array[4]);
//                System.out.println("tobe_raw_file_loc : " + array[5]);
//                System.out.println("asis_http_cd : " + array[6]);
//                System.out.println("tobe_http_cd : " + array[7]);
//                System.out.println("pre_proc_status : " + array[8]);
//                System.out.println("result_status : " + array[9]);
//                System.out.println("result_dt : " + array[10]);
                
                //System.out.println("type : " + StringUtils.substringBefore(array[2], ";"));
                
                //System.out.println("tobe_msg_id : " + array[2]);
                
                String fileName = "D://메가존클라우드/20200930/20200930/applied/"+StringUtils.substringAfterLast(array[5], "\\");
                
                //System.out.println("fileName : " + fileName);
                
                BufferedReader fbr = null;
                
                try{
	                fbr = Files.newBufferedReader(Paths.get(fileName));
	                String fline = "";
	                StringBuffer fsb = new StringBuffer();
	                int i=0;
	                String ref1 = null;		//pc param
	                String ref2 = null;		//mobile pram
	                String ref3 = null;		//TOBEREQUEST number
	                String ref4 = null;		//get / post
	                String ref5 = null;		//URL
	                String ref6 = null;		//Accept
	                String ref7 = null;		//ContentTypte
	                String ref8 = null;		//URL
	                String ref9 = null;		//URL
	                String ref10 = null;	//URL
	                
	                while((fline = fbr.readLine()) != null){
	                	fsb.append(fline).append(System.lineSeparator());
	                	
	                	if(i == 0) {
	                		if(fline.contains("POST")) {
	                			ref4 = "POST";
	                			ref5 = StringUtils.substringBefore(StringUtils.substringAfterLast(fline, "POST"), "HTTP") ;
	                		}else if(fline.contains("GET")) {
	                			ref4 = "GET";
	                			ref5 = StringUtils.substringBefore(StringUtils.substringAfterLast(fline, "GET"), "HTTP") ;
	                		}
	                	}
	                	
	                	if(fline.contains("|TOBERESPONSE")) {
	                		ref2 = StringUtils.substringBefore(fline, "|TOBERESPONSE") ;
	                	}
	                	
	                	if(fline.contains("TOBEREQUEST|")) {
	                		ref3 = StringUtils.substringBefore( StringUtils.substringAfterLast(fline, "TOBEREQUEST|"), "|") ;
	                	}
	                	
	                	if(fline.contains("Accept:")) {
	                		ref6 = StringUtils.substringAfterLast(fline, "Accept:");
	                	}
	                	
	                	if(fline.contains("Content-Type:")) {
	                		ref7 = StringUtils.substringAfterLast(fline, "Content-Type:");
	                	}
	                	
	                	
	                	i++;
	                	
	                }
	                
	                String result_file_data = fsb.toString();
	                
	                String strParam = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	                String endParam = "</root>";
	                
	                if(result_file_data.contains(strParam)) {
	                	ref1   = strParam; 
	                	ref1  += StringUtils.substringBefore(StringUtils.substringAfterLast(result_file_data,strParam),endParam );
	                	ref1  += endParam;
	                }
	                
	                //System.out.println(fsb.toString());
	                dataRslt.put("ref1", ref1);
	                dataRslt.put("ref2", ref2);
	                dataRslt.put("ref3", ref3);
	                dataRslt.put("ref4", ref4);
	                dataRslt.put("ref5", StringUtils.trimToEmpty(ref5));
	                dataRslt.put("ref6", ref6);
	                dataRslt.put("ref7", ref7);
	                dataRslt.put("result_file_data", result_file_data);
	                
//	                if(array[1].equals("a8a07b75-b88b-4d60-9350-0e89c034de72")) {
//	                	System.out.println("a8a07b75" + dataRslt.toString());
//	                }
	                
                }catch(FileNotFoundException ife){
	                ife.printStackTrace();
	            }catch(IOException ie){
	                ie.printStackTrace();
	            }finally{
	                try{
	                    if(fbr != null){
	                    	fbr.close();
	                    }
	                }catch(IOException ice){
	                	ice.printStackTrace();
	                }
	            }
               
                
                //tmpList = Arrays.asList(array);
                //System.out.println("tmpList ; " + tmpList);
                ret.add(dataRslt);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        System.out.println("end readCSV ");
        return ret;
    }
	
}
