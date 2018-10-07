package workflow;

//import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import ApplicationConstant.CommonConstant;
import applicationLibrary.InputFormValidation;
import manager.WebDriverManager;
import valueObject.InputFormVO;

public class STOR_1000_InputFormValidationVerification extends WebDriverManager 
{
	@BeforeClass
	public void init()
	{
		driver = getDriver();
	}
	
	@Test(alwaysRun=true)
	public void STOR_1000_InputFormValidationVerification()
	{
		InputFormVO inputFormVO = new InputFormVO();
		InputFormValidation inputFormValidation = new InputFormValidation(driver, extentTest);
		
		inputFormVO.setLastname(CommonConstant.LASTNAME);
		inputFormVO.setEmail(CommonConstant.EMAIL);
		inputFormVO.setPhone(CommonConstant.PHONE);
		inputFormVO.setCity(CommonConstant.PUNE);
		inputFormVO.setAddress(CommonConstant.BHOSARI);
		inputFormVO.setState(CommonConstant.LOWA);
		inputFormVO.setWebsite(CommonConstant.WEBSITE);
		inputFormVO.setZipcode(CommonConstant.ZIP);
		inputFormVO.setDescription(CommonConstant.DESCRIPTION);
		inputFormVO.setHosting(true);
		inputFormVO.setSend(true);
		if(inputFormValidation.fillInputValidationForm(inputFormVO))
		{
			extentTest.log(LogStatus.PASS,"Test Case Success");
		//	assertTrue(true);
		}
		else
		{
			extentTest.log(LogStatus.FAIL,"Test Case Fail");
		//	assertTrue(false);
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
