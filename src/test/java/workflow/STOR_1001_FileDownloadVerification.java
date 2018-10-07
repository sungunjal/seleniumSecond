package workflow;

import static org.testng.Assert.assertTrue;
import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import ApplicationConstant.CommonConstant;
import applicationObjects.AleartAndModels;
import applicationObjects.HomePage;
import manager.WebDriverManager;

public class STOR_1001_FileDownloadVerification extends WebDriverManager
{
	@BeforeClass
	public void init()
	{
		driver = getDriver();
	}
	
	@Test
	public void STOR_1001_FileDownloadVerification() throws Exception
	{
		HomePage homepage = new HomePage(driver, extentTest);
		AleartAndModels alertandmodel = new AleartAndModels(driver, extentTest);
		
		homepage.verifyHomePgaeOpen();
		homepage.clickOnAlertAndModelLink();
		homepage.clickOnFileDownloadLink();
		
		alertandmodel.putValueInDescription(CommonConstant.DESCRIPTION);
		alertandmodel.clickOnGenerateFileButton();
		if(alertandmodel.verifyDownloadLinkPresent())
		{
			System.out.println("Verified download link present");
			assertTrue(true);
		}
		else
		{
			System.out.println("Not Verified download link present");
			assertTrue(false);
		}
		alertandmodel.clickOnDownloadLink();
		Thread.sleep(4000);
		
		// verify file is getting downloaded or not
		String home = System.getProperty("user.home");
		String filepath = home + "\\downloads";
		String filename = "easy";
		
		File dir = new File(filepath);
		File[] files = dir.listFiles();
		
		if (files == null || files.length == 0) 
		{
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String asd = lastModifiedFile.toString();
		System.out.println(asd);
		assertTrue(asd.contains(filename), "Failed to verify file is downloaded");
		extentTest.log(LogStatus.PASS, "Successfully verify file is downloaded");
		
		// Delete the files
		int len = files.length;
		for(int i=0;i<len;i++)
		{
			if (files[i].toString().contains(filename))
			{
				files[i].delete();
			}
		}
		extentTest.log(LogStatus.PASS, "Successfully file is deleted");
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
