package com.example.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.Node;
//import org.dom4j.io.DOMReader;
//import org.dom4j.io.SAXReader;



public class ParseUtil {

	private static ArrayList<String> list = new ArrayList<String>();
	private static String filePath = "C:\\FOWEB\\workspace\\FOWEB-Library\\src\\cj";
	
	public static ArrayList<HashMap<String, Object>> doParse(String dirPath) {

		ArrayList<HashMap<String, Object>> list = null;
		if (dirPath != null) {

			File rootPath = new File(dirPath);

			rootPath.listFiles();

		}

//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Doument doc = builder.parse("");
//		     
//		NodeList list = doc.getElementsByTagName("Customer");
//		  
//		for(Node node= listTot.item(0).getFirstChild() ; node !=null ; node=node.getNextSibling()) {
//		      if(node.getNodeName().equals("name")) {
//		            Name = node.getTextContent();
//		      }
//		      else if(node.getNodeName().equals("age")) {
//		            age = Integer.parseInt(node.getTextContent());
//		      }
//		      else if(node.getNodeName().equals("address")) {
//		            address = node.getTextContent();
//		      }
//		    
//		} 

		return list;
	}

	public static void main(String[] args) throws IOException {

		ArrayList<HashMap<String,String>> sqlIdList = new ArrayList<HashMap<String,String>>();
		
		
		File path = new File(filePath);

		if (path.exists() == false) {
			System.out.println("경로가 존재하지 않습니다");
		}

		File[] fileList = path.listFiles();

		int tab = 1;

		for (int i = 0; i < fileList.length; i++) {

			if (fileList[i].isDirectory()) {
				subDirList(fileList[i].getCanonicalPath().toString(), tab);
			}
		}
		
		if(list != null) {
			for(String sqlFilePath : list) {
				
				try {
					String filePath = StringUtils.substringBeforeLast(sqlFilePath, "\\");
					String fileName = StringUtils.substringAfterLast(sqlFilePath, "\\");
					
					
					File sqlFile = new File(sqlFilePath);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					
					Document doc = builder.parse(sqlFile);
					
					NodeList list = doc.getElementsByTagName("sqlMap");
					
					for(int i = 0; i < list.getLength(); i++){
						Node node = list.item(i);
						
						if(node.getNodeType() == Node.ELEMENT_NODE){ 
							Element ele = (Element)node;
							String nodeName = ele.getNodeName();
							
							NodeList childList = ele.getChildNodes();
							
							for( int j=0; j<childList.getLength(); j++) {
								
								Node childNode = childList.item(j);
								
								if(childNode.getNodeType() == Node.ELEMENT_NODE){ 
									Element childElement = (Element)childNode;
									
									String childNodeName = childElement.getNodeName();
									
									if( childNodeName.equals("select") || childNodeName.equals("insert") || childNodeName.equals("update") || 
										childNodeName.equals("delete") || childNodeName.equals("statement") || childNodeName.equals("procedure")  ) {
										
										String sqlId = childElement.getAttribute("id");
										
										
										
										HashMap<String,String> sqlInfoMap = new HashMap<String,String>();
										sqlInfoMap.put("pull_file_path", sqlFilePath);
										sqlInfoMap.put("sql_type", childNodeName);
										sqlInfoMap.put("sql_id", sqlId);
										sqlInfoMap.put("file_path", filePath);
										sqlInfoMap.put("file_name", fileName);
										
										sqlIdList.add(sqlInfoMap);
									}
									
								}
							}
							
							
						}
					}
					
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				
			}
		}
		
		
		if(sqlIdList != null && sqlIdList.size() > 0) {
			generateExcel(sqlIdList);
//			for(HashMap<String,String> sqlInfoMap : sqlIdList) {
//				System.out.println("sqlInfoMap : " + sqlInfoMap.toString());
//			}
		}
	}

	private static void subDirList(String source, int tab) {

		File dir = new File(source);

		File[] fileList = dir.listFiles();

		try {
			
			if(fileList == null) {
				System.out.println("fileList is null");
			}
			
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];

				if (file.isFile()) {
					if(file.getName().contains(".sqlx")) {
						list.add(file.getCanonicalPath().toString());
					}
				} else if (file.isDirectory()) {
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString(), tab + 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void generateExcel(ArrayList<HashMap<String,String>> sqlIdList) {
		
		try {
			if(sqlIdList != null && sqlIdList.size() > 0) {
				
				
				// Workbook 생성
		        Workbook xlsWb = new HSSFWorkbook(); // Excel 2007 이전 버전
		        
		 
		        // *** Sheet-------------------------------------------------
		        // Sheet 생성
		        Sheet sheet1 = xlsWb.createSheet("firstSheet");
		 
		        // 컬럼 너비 설정
		        sheet1.setColumnWidth(0, 3000);
		        sheet1.setColumnWidth(1, 20000);
		        sheet1.setColumnWidth(2, 8000);
		        sheet1.setColumnWidth(3, 4000);
		        sheet1.setColumnWidth(4, 10000);
		        // ----------------------------------------------------------
		         
		        // *** Style--------------------------------------------------
		        // Cell 스타일 생성
		        CellStyle cellStyle = xlsWb.createCellStyle();
		         
		        // 줄 바꿈
		        cellStyle.setWrapText(true);
		         
		        // Cell 색깔, 무늬 채우기
		        cellStyle.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		        //cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
		         
		        Row row = null;
		        Cell cell = null;
		        //----------------------------------------------------------

		        int i=0;
		        
		        if(i == 0 ) {
		        
		        	// 첫 번째 줄
			        row = sheet1.createRow(0);
			         
			        // 첫 번째 줄에 Cell 설정하기-------------
			        cell = row.createCell(0);
			        cell.setCellValue("번호");
			        cell.setCellStyle(cellStyle); // 셀 스타일 적용
			        
			        
			        cell = row.createCell(1);
			        cell.setCellValue("경로");
			         
			        cell = row.createCell(2);
			        cell.setCellValue("파일명");
			        
			        cell = row.createCell(3);
			        cell.setCellValue("유형");
			        
			        cell = row.createCell(4);
			        cell.setCellValue("SQL ID");
			        
		        }
		        
		        i++;


				if(sqlIdList != null && sqlIdList.size() > 0) {
					for(HashMap<String,String> sqlInfoMap : sqlIdList) {
						row = sheet1.createRow(i);
						
						String filePath	= StringUtils.defaultString((String)sqlInfoMap.get("file_path"), "");
						String fileName	= StringUtils.defaultString((String)sqlInfoMap.get("file_name"), "");
						String sqlType	= StringUtils.defaultString((String)sqlInfoMap.get("sql_type"), "");
						String sqlId	= StringUtils.defaultString((String)sqlInfoMap.get("sql_id"), "");
						
						cell = row.createCell(0);
						cell.setCellValue(i);
						 
						cell = row.createCell(1);
						cell.setCellValue(filePath);
						 
						cell = row.createCell(2);
						cell.setCellValue(fileName);
   
						cell = row.createCell(3);
						cell.setCellValue(sqlType);
						
						cell = row.createCell(4);
						cell.setCellValue(sqlId);
						
						i++;
					}
				}
		       
		         
		        // excel 파일 저장
		        try {
		            File xlsFile = new File("D:\\excel\\list.xls");
		            FileOutputStream fileOut = new FileOutputStream(xlsFile);
		            xlsWb.write(fileOut);
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
