package com.example.demo.util;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelDataChgUtil {

	
	public static void main(String args[]) {
		
		try {
			FileInputStream file = new FileInputStream("D:\\doc\\test\\CJFW-DE-01-���α׷�����(V0.5)_OSS DB��ȯ_����.xlsx"); 
			
			HSSFWorkbook hswb = new HSSFWorkbook(file);
			int sheetNum = hswb.getNumberOfSheets();
			
			System.out.println("sheetNum : " + sheetNum );
			
		}catch(Exception e ) {
			e.printStackTrace();
			
		}
		
	}
}
