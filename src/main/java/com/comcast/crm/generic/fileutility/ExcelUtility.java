package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtility {
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable, IOException
	{
		FileInputStream fis =new FileInputStream("C:\\Users\\Admin\\git\\RepoGitHub\\com.AutomationFramework\\testdata\\org.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	public int getRowCount(String sheetName) throws Throwable, IOException
	{
		FileInputStream fis =new FileInputStream("C:\\Users\\Admin\\git\\RepoGitHub\\com.AutomationFramework\\testdata\\org.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowNum=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowNum;
	}
	public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable {
		FileInputStream fis =new FileInputStream("C:\\Users\\Admin\\git\\RepoGitHub\\com.AutomationFramework\\testdata\\org.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\Admin\\git\\RepoGitHub\\com.AutomationFramework\\testdata\\org.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
