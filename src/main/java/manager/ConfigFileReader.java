package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import enums.DriverType;


public class ConfigFileReader {

	private Properties properties;
	private final String properiesFilePath = "Config\\configuration.properties";
	
	public ConfigFileReader()
	{
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(properiesFilePath));
			properties = new Properties();
			try
			{
				properties.load(reader);
				reader.close();
			}
			catch(Exception ex)
			{
				System.out.println("IO Execptin occurred:"+ex);
			}
		}
		catch(Exception ex)
		{
			System.out.println("File Not Found:"+ex);
		}
	}
	
	public String getAppUrl()
	{
		String url = properties.getProperty("appUrl");
		if (url == null)
		{
			throw new RuntimeException("app url not specified in the Configuration.properties file.");
		}
		return url;
	}
	
	public String getExcelFileName()
	{
		String filename = properties.getProperty("filename");
		if (filename == null)
		{
			filename="StateForm";
			//throw new RuntimeException("filename not specified in the Configuration.properties file.");
		}
		return filename;
	}
	
	public String getExcelSheetName()
	{
		String sheetname = properties.getProperty("sheetname");
		if (sheetname == null)
		{
			sheetname="Test";
			//throw new RuntimeException("sheetname not specified in the Configuration.properties file.");
		}
		return sheetname;
	}
	
	public DriverType getBrowser()
	{
		String browser = properties.getProperty("browser");
		if (browser == null || browser.equalsIgnoreCase("chrome"))
		{
			return DriverType.CHROME;
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			return DriverType.FIREFOX;
		}
		else if (browser.equalsIgnoreCase("ie"))
		{
			return DriverType.INTERNETEXPLORER;
		}
		else 
			throw new RuntimeException("Browser Type Key value in Configuration.properties is not matched : " + browser);
	}
	
}
