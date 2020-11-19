package operations;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class HandleCaptcha {

	public static handleCaptcha() {
		// TODO Auto-generated method stub
		//driver.findElement(By.id("loginText")).click();
		File src=driver.findElement(By.xpath("//*[@id=\"ru_captcha\"]/div/img")).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/captcha.png";
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ITesseract image = new Tesseract();
		String imageText;
		try {
			imageText = image.doOCR(new File(path));
			System.out.println(imageText);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
