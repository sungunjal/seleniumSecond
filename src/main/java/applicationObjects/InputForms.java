package applicationObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class InputForms 
{
	ExtentTest extentTest;
	public InputForms(WebDriver driver, ExtentTest extentTest)
	{
		PageFactory.initElements(driver, this);
		this.extentTest = extentTest;
	}

	// First Name
	@FindBy(name = "first_name")
	private WebElement input_userFirstName;

	// Last Name
	@FindBy(name = "last_name")
	private WebElement input_userLastName;

	// Email
	@FindBy(name = "email")
	private WebElement input_userEmail;

	// Phone
	@FindBy(name = "phone")
	private WebElement input_userPhone;

	// Address
	@FindBy(name = "address")
	private WebElement input_userAddress;

	// City
	@FindBy(name = "city")
	private WebElement input_userCity;

	// State
	@FindBy(name = "state")
	private WebElement dropdown_userState;

	// Zip Code
	@FindBy(name = "zip")
	private WebElement input_userZipCode;

	// Website
	@FindBy(name = "website")
	private WebElement input_userWebsite;

	// Hosting Yes
	@FindBy(xpath = "//input[@value='yes' and @type='radio']")
	private WebElement radio_userHostingYes;

	// Hosting No
	@FindBy(xpath = "//input[@value='no' and @type='radio']")
	private WebElement radio_userHostingNo;

	// description
	@FindBy(name = "comment")
	private WebElement textarea_userDescription;

	// Send Button
	@FindBy(xpath = "//button[@type='submit' and contains(text(),'Send      ')]")
	private WebElement button_userSend;

	// First Name Vaidator
	@FindBy(xpath = "//small[@data-bv-for='first_name' and @data-bv-validator='notEmpty']")
	private WebElement text_Validator;

	// Creating Method for Sendkeys Operation
	// SendKeys
	public void putValueInFirtsName(String value) {
		input_userFirstName.sendKeys(value);
	}

	public void putValueInLastName(String value) {
		input_userLastName.sendKeys(value);
	}

	public void putValueInEmail(String value) {
		input_userEmail.sendKeys(value);
	}

	public void putValueInAddress(String value) {
		input_userAddress.sendKeys(value);
	}

	public void putValueInCity(String value) {
		input_userCity.sendKeys(value);
	}

	public void putValueInZipCode(String value) {
		input_userZipCode.sendKeys(value);
	}

	public void putValueInPhone(String value) {
		input_userPhone.sendKeys(value);
	}

	public void putValueInWebsite(String value) {
		input_userWebsite.sendKeys(value);
	}

	public void putValueInDescription(String value) {
		textarea_userDescription.sendKeys(value);
	}

	// Radio Button Operations
	public void clickRadioButtonHosting(boolean yes) {
		if (yes) {
			radio_userHostingYes.click();
		} else {
			radio_userHostingNo.click();
		}
	}

	// Dropdown value select operation
	public void selectValueFromStateDropdown(String value) {
		dropdown_userState.click();
		Select select = new Select(dropdown_userState);
		select.selectByValue(value);
	}

	// Button Click Operations
	public void clickOnSendButton() {
		button_userSend.click();
	}

	// Verify Validation message
	public boolean verifyValidationMessage(String expected) {
		String actual = text_Validator.getText();
		if (!actual.equalsIgnoreCase(expected)) {
			System.out.println("Failed to verify");
			extentTest.log(LogStatus.FAIL, "Failed to verify");
			return false;
		}
		System.out.println("Succesfully verified");
		extentTest.log(LogStatus.PASS, "Succesfully verified");
		return true;
	}
}
