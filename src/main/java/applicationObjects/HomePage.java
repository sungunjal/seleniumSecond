package applicationObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage
{
	ExtentTest extentTest;
	public HomePage(WebDriver driver, ExtentTest extentTest)
	{
		PageFactory.initElements(driver, this);
		this.extentTest=extentTest;
	}
	
	// Link Demo Page
	@FindBy(linkText="Demo Home")
	private WebElement link_demoPage;
	
	// Input Form Dropdown Link
	@FindBy(xpath="//li[@class='dropdown']//a[contains(text(),'Input Forms')]")
	private WebElement link_inputFormDropdown;
	
	// Table Link
	@FindBy(xpath="//li[@class='dropdown']//a[contains(text(),'Table')]")
	private WebElement link_TableDropdown;
	
	// Alert and Model Dropdown Link
	@FindBy(xpath="//li[@class='dropdown']//a[contains(text(),'Alerts & Modal')]")
	private WebElement link_AlertAndModelDropdown;
	
	// File Download Link
	@FindBy(linkText="File Download")
	private WebElement link_FileDownload;
	
	// WindowPopupModal Link 
	@FindBy(linkText="Window Popup Modal")
	private WebElement link_WindowPopupModal;
	
	// Input For Validation Link 
	@FindBy(linkText="Input Form Submit")
	private WebElement link_inputFormValidation;
	
	// Table Data Download
	@FindBy(linkText="Table Data Download")
	private WebElement link_TableDataDownLoad;
	
	//// Verify Home Page dispalyed
	public boolean verifyHomePgaeOpen()
	{
		if (!link_demoPage.isDisplayed())
		{
			System.out.println("Failed to verify Home Page Displayed");
			extentTest.log(LogStatus.FAIL, "Failed to verify Home Page Displayed");
			return false;
		}
		System.out.println("Successfully verify Home Page Displayed");
		extentTest.log(LogStatus.PASS, "Successfully verify Home Page Displayed");
		return true;
	}
	
	// Click on Links
	public void clickOnInputForm()
	{
		link_inputFormDropdown.click();
	}
	
	// Click on input form validator
	public void clickOnInputFormValidator()
	{
		link_inputFormValidation.click();
	}
	
	public void clickOnAlertAndModelLink()
	{
		link_AlertAndModelDropdown.click();
	}
	public void clickOnFileDownloadLink()
	{
		link_FileDownload.click();
	}
	public void clickOnWindowPopupModal()
	{
		link_WindowPopupModal.click();
	}
	
	// Click on table 
	public void clickOnTableLink()
	{
		link_TableDropdown.click();
	}
	// Click on Table Data download
	public void clickOnTableDataDownload()
	{
		link_TableDataDownLoad.click();
	}
	
}
