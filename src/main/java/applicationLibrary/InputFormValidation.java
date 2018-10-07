package applicationLibrary;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import ApplicationConstant.CommonConstant;
import applicationObjects.HomePage;
import applicationObjects.InputForms;
import valueObject.InputFormVO;

public class InputFormValidation 
{
	WebDriver driver;
	ExtentTest extentTest;
	public InputFormValidation(WebDriver driver, ExtentTest extentTest)
	{
		this.driver = driver;
		this.extentTest = extentTest;
	}
	
	public boolean fillInputValidationForm(InputFormVO inputFormVO)
	{
		HomePage homePage = new HomePage(driver, extentTest);
		InputForms inputForm = new InputForms(driver,extentTest);
		
		// verify home page
		homePage.verifyHomePgaeOpen();
		homePage.clickOnInputForm();
		homePage.clickOnInputFormValidator();
		
		if (inputFormVO.getFirstname()!= "")
		{
			inputForm.putValueInFirtsName(inputFormVO.getFirstname());
		}
		
		if (inputFormVO.getLastname()!= "")
		{
			inputForm.putValueInLastName(inputFormVO.getLastname());
		}
		if (inputFormVO.getEmail()!= "")
		{
			inputForm.putValueInEmail(inputFormVO.getEmail());
		}
		
		if (inputFormVO.getPhone()!= "")
		{
			inputForm.putValueInPhone(inputFormVO.getPhone());
		}
		
		if (inputFormVO.getAddress()!= "")
		{
			inputForm.putValueInAddress(inputFormVO.getAddress());
		}
		
		if (inputFormVO.getCity()!= "")
		{
			inputForm.putValueInCity(inputFormVO.getCity());
		}
		
		/*if (inputFormVO.getState()!= "")
		{
			inputForm.selectValueFromStateDropdown(inputFormVO.getState());
		}*/
		
		if (inputFormVO.getZipcode()!= "")
		{
			inputForm.putValueInZipCode(inputFormVO.getZipcode());
		}
		
		if (inputFormVO.getWebsite()!= "")
		{
			inputForm.putValueInWebsite(inputFormVO.getWebsite());
		}
		
		if (inputFormVO.isHosting())
		{
			inputForm.clickRadioButtonHosting(true);
		}
		else
		{
			inputForm.clickRadioButtonHosting(false);
		}
		
		if (inputFormVO.getDescription()!= "")
		{
			inputForm.putValueInDescription(inputFormVO.getDescription());
		}
		
		if (inputFormVO.isSend())
		{
			inputForm.clickOnSendButton();
			if(inputForm.verifyValidationMessage(CommonConstant.PLEASE_SUPPLY_YOUR_FIRSTNAME))
			{
				System.out.println("Error Message Verify Successfully");
				extentTest.log(LogStatus.PASS, "Error Message Verify Successfully");
			}
			else
			{
				System.out.println("Error Message not Verify");
				extentTest.log(LogStatus.FAIL, "Error Message not Verify");
				return false;
			}
		}
		return true;
	}
}
