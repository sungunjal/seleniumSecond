package workflow;

import java.util.Set;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import applicationObjects.AleartAndModels;
import applicationObjects.HomePage;
import manager.WebDriverManager;

public class STOR_1002_PrintDataFromTable extends WebDriverManager
{
	@BeforeClass
	public void init()
	{
		driver = getDriver();
	}
	
	@Test(alwaysRun=true)
	public void STOR_1002_PrintDataFromTable() throws Exception
	{
		HomePage homepage = new HomePage(driver, extentTest);
		AleartAndModels alertandmodel = new AleartAndModels(driver, extentTest);
		
		homepage.verifyHomePgaeOpen();
		homepage.clickOnAlertAndModelLink();
		homepage.clickOnWindowPopupModal();
		String before = driver.getWindowHandle();
		
		alertandmodel.clickOnFollowOnTwitterLink();
		Thread.sleep(5000);
		if (driver.getWindowHandles().size()==2)
		{
			extentTest.log(LogStatus.PASS, "Successfully verified two windows are displayed");
		}
		
		Set<String> handle = driver.getWindowHandles();
		System.out.println(handle);
		for(String hand : driver.getWindowHandles())
		{
			System.out.println(hand);
			if (!hand.equals(before))
			{
				driver.switchTo().window(hand);
				break;
			}
		}
		driver.manage().window().maximize();
		alertandmodel.verifyFollowOnWindowPopupOpen();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
