package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.LoginFunction;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil  {

	String inputPath = "./FileInput/TestData.xlsx";

	String outputPath = "./FileOutput/ResultDDF.xlsx";

	@Test
	public void startTest() throws Throwable
	{

		//create object for excelfile util class
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		int row = xl.rowCount("Login");

		Reporter.log("Number of rows are::" +row, true);

		int cellCount = xl.cellCount("Login");

		Reporter.log("Number of cell  are::" +cellCount, true);
		// iterate all rows in the login sheet

		for(int i= 1; i<=row;i++)
		{
			// read username and password

			String user = xl.getCellData("Login", i, 0);
			 String pass = xl.getCellData("Login", i, 1);
			

			// call login method
			boolean result = LoginFunction.check_Login(user , pass);

			if(result)
			{
				// write success login result into results cell
				xl.setCellData("Login", i, 2, "Login Success", outputPath );
				xl.setCellData("Login", i, 3, "Pass", outputPath);

			}
			else
			{
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshots/Iteration" +i+"LoginPage.png"));
				// if result s true capture and take screesnshot
				String error_message = driver.findElement(By.xpath(prop.getProperty("objErrorMsg"))).getText();

				xl.setCellData("Login", i, 2, error_message, outputPath);
				xl.setCellData("Login", i, 3, "Fail", outputPath );

			}


		}




	}	

}
