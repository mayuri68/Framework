package objectrepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCart{
	public WebDriver driver;
	public WebDriverWait wd;
	
	@FindBy(id="product")
	WebElement addedProduct;
	@FindBy(name="quantity")
	WebElement quantity;
	@FindBy(name="price")
	WebElement price;
	@FindBy(name="name")
	WebElement name;
	@FindBy(name="mobileNo")
	WebElement mobileNo;
	@FindBy(name="Address")
	WebElement address;
	@FindBy(name="submit")
	WebElement submit;
	@FindBy(name="otp")
	WebElement otp;
	@FindBy(name="pay")
	WebElement pay;
	
	
	public CheckoutCart(WebDriver driver)
	{
		this.driver=driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public String productDetail()
	{
		return this.addedProduct.getText();
	}
	
	public String productQuantity()
	{
		return this.quantity.getText();
	}
	
	public String productPrice()
	{
		return this.price.getText();
	}
	
	public void provideBuyerDetails()
	{
		this.name.sendKeys("test");
		this.mobileNo.sendKeys("112234567");
		this.address.sendKeys("testAddress");
		this.submit.click();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void sendOTP()
	{
		this.otp.sendKeys(getOTP());
	}
	
	public void clickPay()
	{
		this.pay.click();
	}
	public String getOTP()
	{
	//Call dev API to get the OTP and assign it to string.
		return "xxxx";
	}

	
}
