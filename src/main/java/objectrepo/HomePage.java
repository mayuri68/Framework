package objectrepo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage{
	public WebDriver driver;
	public WebDriverWait wd;
	
	@FindBy(css="div[qa='product_name']>a")
	List<WebElement> products;
	@FindBy(css="input[placeholder='Search for products, brands and more']")
	WebElement searchBox;
	@FindBy(id="checkout")
	WebElement checkoutButton;

	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		wd=new WebDriverWait(driver,30);
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public void AddProduct(String productName,int quantity)
	{
		wd.until(ExpectedConditions.visibilityOfAllElements(this.products));
		   for(int i=0;i<this.products.size();i++)
		        {
		            String name = this.products.get(i).getText();

		            if(name.contains(productName))
		            {   for(int j=0;j<quantity;j++) {        
		                driver.findElement(By.xpath("//div[@qa='product_name']//a[text()='" + name + "']/following::button[1]")).click();   
		            }
		             }
		         }
	}
	
	public void Search(String productName)
	{
	   this.searchBox.sendKeys(productName);
	   this.searchBox.click();
	}
	
	public void Checkout() {
		this.checkoutButton.click();
	}

	
}
