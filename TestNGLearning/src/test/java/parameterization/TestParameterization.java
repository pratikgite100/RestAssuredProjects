package parameterization;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.Constants;
import utilities.ExcelReader;

public class TestParameterization {
	
	@Test(dataProvider="getData")
	public void testData(Hashtable<String,String> data) {
		
		System.out.println(data.get("id")+"----"+data.get("customer")+"----"+data.get("quantity")+"----"+data.get("price"));
		
	}
	
	@DataProvider
	public static Object[][] runMode() {
       ExcelReader excel = new ExcelReader("C:\\Users\\141572\\eclipse-workspace\\RestAssured\\TestNGProjects\\TestNGLearning\\src\\test\\resources\\testdata\\TestSuiteData3.xlsx");
	    
		int rows = excel.getRowCount(Constants.DATA_SHEET_FOR_RUNMODE);
		//System.out.println("Total Rows are: "+rows);
		
		int cols = excel.getColumnCount(Constants.DATA_SHEET_FOR_RUNMODE);
		
		
		/*
		String testName = "testcases";
				
		
		int testCaseRowNum =1;
		
		for(testCaseRowNum=1; testCaseRowNum <= rows; testCaseRowNum++) {
			
			String testCaseName = excel.getCellData(Constants.DATA_SHEET_FOR_RUNMODE, 0, testCaseRowNum);
			
			 if(testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}
			//System.out.println("test case starts from row numbe: "+testCaseRowNum);
			
			int dataStartRowNum = testCaseRowNum+2;
			
			int testRows = 0;
			
			while(!excel.getCellData(Constants.DATA_SHEET_FOR_RUNMODE, 0, dataStartRowNum+testRows).equals("")) {
				
				testRows++;
			}
			
			System.out.println("Total rows of data are: "+testRows);
			
			
			int colStartColNum = testCaseRowNum+1;
			int testCols = 0;
			
			while(!excel.getCellData(Constants.DATA_SHEET_FOR_RUNMODE, 0, colStartColNum+testCols).equals("")) {
				
				testCols++;
			}
			
			
			System.out.println("Total colums of data are: "+testCols);
			
			*/
			
			Object data[][] =new Object[rows][1];
		    
			int i=0;
			for(int rNum=2; rNum<rows; rNum++) {
				
                Hashtable<String,String> table = new Hashtable<String,String>();
				
				for(int cNum=0;cNum<cols; cNum++) {
					
					//System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				   String testData = excel.getCellData(Constants.DATA_SHEET_FOR_RUNMODE, cNum, rNum);
				   String colName = excel.getCellData(Constants.DATA_SHEET_FOR_RUNMODE, cNum, 1);
				   
				   table.put(colName, testData);
				}
				data[i][0]=table;
				i++;
			}
			
	
		return data;
		
		
	}
	
	@DataProvider
	public static Object[][] getData(){
		
		ExcelReader excel = new ExcelReader("C:\\Users\\141572\\eclipse-workspace\\RestAssured\\TestDataProject\\src\\test\\resources\\testdata\\TestSuiteData.xlsx");
	    
		int rows = excel.getRowCount(Constants.DATA_SHEET);
		System.out.println("Total Rows are: "+rows);
		
		
		String testName = "AddCustomerTest";
		
		
				
		
		int testCaseRowNum =1;
		
		for(testCaseRowNum=1; testCaseRowNum <= rows; testCaseRowNum++) {
			
			String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
			
			if(testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}
			System.out.println("test case starts from row numbe: "+testCaseRowNum);
			
			int dataStartRowNum = testCaseRowNum+2;
			
			int testRows = 0;
			
			while(!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")) {
				
				testRows++;
			}
			
			System.out.println("Total rows of data are: "+testRows);
			
			
			int colStartColNum = testCaseRowNum+1;
			int testCols = 0;
			
			while(!excel.getCellData(Constants.DATA_SHEET, 0, colStartColNum+testCols).equals("")) {
				
				testCols++;
			}
			
			
			System.out.println("Total colums of data are: "+testCols);
			
			Object data[][] =new Object[testRows][1];
		    
			int i=0;
			for(int rNum=dataStartRowNum; rNum<(dataStartRowNum+testRows); rNum++) {
				
                Hashtable<String,String> table = new Hashtable<String,String>();
				
				for(int cNum=0;cNum<testCols+2; cNum++) {
					
					//System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				   String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				   String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
				   
				   table.put(colName, testData);
				}
				data[i][0]=table;
				i++;
			}
			
	
		return data;
	}

}
