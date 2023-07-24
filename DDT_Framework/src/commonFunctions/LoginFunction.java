package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import config.AppUtil;

public class LoginFunction extends AppUtil {
	
	public static boolean check_Login(String username , String password)
	{
		driver.get(prop.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("objUser"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("objPass"))).sendKeys(password);
		
		driver.findElement(By.xpath(prop.getProperty("objSubmit"))).click();
		
		String expected = "dashboard";
		
		String actual = driver.getCurrentUrl();
		
		if(actual.contains(expected))
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}

	
}
