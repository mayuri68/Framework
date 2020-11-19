package operations;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class ScreenShots {
 
              public static void captureScreenShot(WebDriver driver, String ScreenShotName)
              {
                            try {
                                                 File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                                                 FileUtils.copyFile(screenshot,new File("C://Selenium//"+ScreenShotName+".jpg"));
                                     } catch (Exception e)
                                       {
                                           System.out.println(e.getMessage());
                                            e.printStackTrace();
                                        }
                  }
}