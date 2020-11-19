package objectrepo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class LoginPage{
	public WebDriver driver;
	public WebDriverWait wd;
	@FindBy(name="username")
	WebElement username;
	@FindBy(name="password")
	WebElement password;
	@FindBy(xpath="//*[@id=\\\"ru_captcha\\\"]/div/img")
	WebElement captchaImg;
	@FindBy(name="captcha")
	WebElement captchaTextBox;
	@FindBy(id="btn_login")
	WebElement login;

	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		wd=new WebDriverWait(driver,30);
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public void login(String username,String password) throws IOException, TesseractException
	{
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.username.sendKeys(username);
		
		this.password.sendKeys(password);
		
		wd.until(ExpectedConditions.visibilityOf(this.captchaImg));
		
		File src=this.captchaImg.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/captcha.png";
		FileHandler.copy(src, new File(path));
		
		ITesseract image = new Tesseract();
		String imageText = image.doOCR(new File(path));
		this.captchaTextBox.sendKeys(imageText);
		
		this.login.click();
		
	}

	
}
