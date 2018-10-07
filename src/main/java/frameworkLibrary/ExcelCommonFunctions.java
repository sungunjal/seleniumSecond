package frameworkLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import manager.FileReaderManager;

public class ExcelCommonFunctions 
{
	String projectDir, testExcelFile,testExcelSheet;
	
	public void setProjectDirectory()
	{
		projectDir= System.getProperty("user.dir");
	}
	
	public void setExcelFileName()
	{
		if (FileReaderManager.getInstance().getConfigFileReader().getExcelFileName().equals(""))
		{
			testExcelFile = "StateForm";
		}
		else
		{
			testExcelFile = FileReaderManager.getInstance().getConfigFileReader().getExcelFileName();
		}
	}
	
	public void setExcelSheetName()
	{
		if (FileReaderManager.getInstance().getConfigFileReader().getExcelSheetName().equals(""))
		{
			testExcelSheet = "Test";
		}
		else
		{
			testExcelSheet = FileReaderManager.getInstance().getConfigFileReader().getExcelSheetName();
		}
	}
	
	public String getValueFormExcel(int row,int col) throws IOException
	{
		// Sting Filename and SheetName
		setExcelFileName();
		setExcelSheetName();
		String excelFilePath = System.getProperty("user.dir")+"\\"+testExcelFile+".xlsx"; 
		FileInputStream fis = new FileInputStream(new File(excelFilePath));	
		
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(testExcelSheet); 
		Row myrow = sheet.getRow(row);
		Cell myCell = myrow.getCell(col);
		String value = myCell.getStringCellValue();
		workbook.close();
		return value;
	}
	
	public Integer getRowCount() throws Exception
	{
		// Sting Filename and SheetName
		setExcelFileName();
		setExcelSheetName();
		String excelFilePath = System.getProperty("user.dir")+"\\"+testExcelFile+".xlsx"; 
		FileInputStream fis = new FileInputStream(new File(excelFilePath));	

		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(testExcelSheet); 
		Integer value = sheet.getLastRowNum();
		workbook.close();
		return value;
	}
}
