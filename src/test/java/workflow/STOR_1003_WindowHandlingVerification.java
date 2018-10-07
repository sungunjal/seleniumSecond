package workflow;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import applicationObjects.AleartAndModels;
import applicationObjects.HomePage;
import applicationObjects.TablePages;
import manager.WebDriverManager;

public class STOR_1003_WindowHandlingVerification extends WebDriverManager
{
	@BeforeClass
	public void init()
	{
		driver = getDriver();
	}
	
	@Test(alwaysRun=true)
	public void STOR_1003_WindowHandlingVerification() throws Exception
	{
		HomePage homepage = new HomePage(driver, extentTest);
		TablePages tablePages = new TablePages(driver, extentTest);
		
		homepage.verifyHomePgaeOpen();
		homepage.clickOnTableLink();
		homepage.clickOnTableDataDownload();
	
		String before = driver.getWindowHandle();
		System.out.println(before);
		tablePages.clickOnPrintButton();
		Thread.sleep(3000);
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
		
		WebElement asd = driver.findElement(By.xpath("//html[@id='print-preview']"));
		if (asd.isDisplayed())
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		Thread.sleep(5000);
		/*driver.switchTo().frame(driver.findElement(By.id("pdf-viewer")));		
		WebElement a = driver.findElement(By.xpath("//embed"));
		if (a.isDisplayed())
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}*/
		
		driver.findElement(By.xpath("//button[@class='print default']")).click();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
