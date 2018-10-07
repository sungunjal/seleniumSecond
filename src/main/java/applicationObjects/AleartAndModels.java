package applicationObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AleartAndModels 
{
	ExtentTest extentTest;
	public AleartAndModels(WebDriver driver,ExtentTest extentTest)
	{
		PageFactory.initElements(driver, this);
		this.extentTest=extentTest;
	}
	
	// text area 
	@FindBy(id="textbox")
	private WebElement textarea_Description;
	
	// Button Generate File 
	@FindBy(id="create")
	private WebElement button_GenerateFile;
	
	// Download Link 
	@FindBy(id="link-to-download")
	private WebElement link_Download;
	
	// Follow  on Twitter link
	@FindBy(xpath="//a[contains(text(),'Follow On Twitter  ')]")
	private WebElement link_FollowOnTwitter;
	
	// Follow  on Twitter textBox for verification
	@FindBy(name="commit  ")
	private WebElement legend_FollowOnTwitter;
	
	// Senkeys Function
	public void putValueInDescription(String value)
	{
		textarea_Description.sendKeys(value);
	}
	
	// click operation
	public void clickOnGenerateFileButton()
	{
		button_GenerateFile.click();
	}
	public void clickOnDownloadLink()
	{
		link_Download.click();
	}
	public void clickOnFollowOnTwitterLink()
	{
		link_FollowOnTwitter.click();
	}
	
	// verification of download link
	public boolean verifyDownloadLinkPresent()
	{
		if (!link_Download.isDisplayed())
		{
			extentTest.log(LogStatus.FAIL, "Failed to verify download link is displayed");
			return false;
		}
		extentTest.log(LogStatus.PASS, "Successfully verified download link is displayed");
		return true;
	}
	
	// verification of download link
	public boolean verifyFollowOnWindowPopupOpen()
	{
		if (!legend_FollowOnTwitter.isDisplayed())
		{
			extentTest.log(LogStatus.FAIL, "Failed to verify follow on twitter is displayed");
			return false;
		}
		extentTest.log(LogStatus.PASS, "Successfully verified follow on twitter is displayed");
		return true;
	}
}
