package tests;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;

import objectrepo.CheckoutCart;
import objectrepo.Confirmation;
import objectrepo.HomePage;
import objectrepo.LoginPage;
import operations.BrowserInitialization;
import operations.ScreenShots;


public class ProductOrdering {
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	CheckoutCart checkout;
	Confirmation confirm;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	int i = 0;
	
	@Parameters({"URL"})
	@BeforeTest
	 public void browserlaunch(String URL)
     {
                    driver = BrowserInitialization.StartBrowser(URL);
                    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
                    lp = new LoginPage(driver);
                    hp = new HomePage(driver);
                    checkout = new CheckoutCart(driver);
                    confirm =new Confirmation(driver);
     }
                    
	//Login using credentials and captcha
	@Test(priority=1,dataProvider="getUserData")
	public void loginApplication(String username,String password,String product,int quantity) {
		
		try {
		lp.login(username, password);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Search and add product
	@Test(priority=2,dataProvider="getUserData")
	public void prepareCart(String username,String password,String product,int quantity) {
		
		//Verify if we landed on right page
		Assert.assertEquals(driver.getCurrentUrl(), "Page Url");
		
		try {
		hp.Search(product);
		hp.AddProduct(product,quantity);
		hp.Checkout();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	//Place Order by putting delivery details and make payment
	@Test(priority=3,dataProvider="getUserData")
	public void placeOrder(String username,String password,String product,int quantity) {
		
		//Verify if we landed on right page
		Assert.assertEquals(driver.getCurrentUrl(), "Page Url");
		
		//Verify if right product added
		Assert.assertEquals(checkout.productDetail(),product);
		
		//Verify if right quantity added
		Assert.assertEquals(checkout.productQuantity(),quantity);
		
				
		try{
			checkout.provideBuyerDetails();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://blazedemo.com/confirmation.php");
		
	}
	
	//Check if Flight is booked and confirmation id received
	@Test(priority=4)
	public void orderConfirmation() {
		//Verify if flight gets confirmed
		Assert.assertEquals(confirm.getHeading(), "Thank you for your purchase today!");
		Assert.assertNotNull(confirm.getConfirmationID());
	}
	
	@DataProvider
	public Object[][] getUserData() throws Exception
	{
		
		File src=new File("src/main/java/resources/testData.xlsx");
		FileInputStream fis=new FileInputStream(src);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheetAt(0);
		
		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();
		
		Object[][] userData = new Object[rowCount][colCount]; 
		for(int j=1;j<=sheet.getLastRowNum();j++)
		{
			userData[j-1][0] = sheet.getRow(j).getCell(0).getStringCellValue();
			userData[j-1][1] = sheet.getRow(j).getCell(1).getStringCellValue(); 
			userData[j-1][2] = sheet.getRow(j).getCell(2).getStringCellValue();
			userData[j-1][3] = sheet.getRow(j).getCell(3).getStringCellValue();
		}
		return userData;
		
	}

	
	// Taking Screen shot on test fail
    @AfterMethod
    public void screenshot(ITestResult result)
    {
               i = i+1;
               String name = "ScreenShot";
               String x = name+String.valueOf(i);
              if(ITestResult.FAILURE == result.getStatus())
                {
                               ScreenShots.captureScreenShot(driver, x);
                 }
}
	
	@AfterTest
	public void closeBrowser() 
	{
        driver.quit();
	}

}
