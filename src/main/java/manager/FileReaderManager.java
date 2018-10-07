package manager;

import frameworkLibrary.CreateTestNGxml;
import frameworkLibrary.ExcelCommonFunctions;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager= new FileReaderManager();
	private static ConfigFileReader configFileReader = new ConfigFileReader();
	private ExcelCommonFunctions excelCommonFunction = new ExcelCommonFunctions();
	private CreateTestNGxml createTestNGxmlFile = new CreateTestNGxml();
	
	public FileReaderManager() {}
	
	public static FileReaderManager getInstance()
	{
		return fileReaderManager;
	}
	
	public ConfigFileReader getConfigFileReader()
	{
		return (configFileReader == null) ? configFileReader = new ConfigFileReader() : configFileReader;
	}
	
	public ExcelCommonFunctions getExcelComonFunction()
	{
		return (excelCommonFunction == null) ? excelCommonFunction = new ExcelCommonFunctions() : excelCommonFunction;
	}
	
	public CreateTestNGxml getCreateTestNGxmlFile()
	{
		return (createTestNGxmlFile == null) ? createTestNGxmlFile = new CreateTestNGxml() : createTestNGxmlFile;
	}
}
