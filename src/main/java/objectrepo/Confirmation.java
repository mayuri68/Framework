package objectrepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation{
	public WebDriver driver;
	@FindBy(xpath="/html/body/div[2]/div/h1")
	WebElement title;
	@FindBy(xpath="/html/body/div[2]/div/table/tbody/tr[1]/td[2]")
	WebElement confirmationID;

	
	public Confirmation(WebDriver driver)
	{
		this.driver=driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public String getHeading()
	{
		return this.title.getText();
	}
	
	public String getConfirmationID()
	{
		return this.confirmationID.getText();
	}
	

	
}
