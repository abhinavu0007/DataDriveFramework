package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static Properties prop;
	public static WebDriver driver;

	@BeforeTest
	public static void setUp() throws Throwable
	{
		prop = new Properties();



		prop.load(new FileInputStream("./PropertyFile/Login.properties"));

		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}

		else if (prop.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value is not missing" , true);
		}

	}

	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}

}
