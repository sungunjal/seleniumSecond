package applicationObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class TablePages {

	ExtentTest extentTest;
	public TablePages(WebDriver driver,ExtentTest extentTest)
	{
		PageFactory.initElements(driver, this);
		this.extentTest=extentTest;
	}
	
	@FindBy(how = How.XPATH, using="//a//span[text()='Print']")
	private WebElement link_Print;
	
	// Click on print Button
	public void clickOnPrintButton()
	{
		link_Print.click();
	}
	
}
