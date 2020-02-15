package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunLib.FunctionLibrary;

public class Dummy {

	public static void main(String[] args) throws IOException, InterruptedException 
	{

		ExtentReports report=new ExtentReports("C:\\Users\\srikanthp\\Desktop\\Mine.html");
		ExtentTest writer=report.startTest("LoginTest");
		WebDriver driver1=FunctionLibrary.startBrowser();
		writer.log(LogStatus.INFO, "Chrome Opened");
		FunctionLibrary.openApp(driver1);
		writer.log(LogStatus.INFO, "Application Launched");
		FunctionLibrary.waitforElement(driver1, "id", "username", "10");
		writer.log(LogStatus.INFO, "Waiting for username");
		FunctionLibrary.typeAction(driver1, "id", "username", "admin");
		writer.log(LogStatus.INFO, "Entering username");
		FunctionLibrary.waitforElement(driver1, "id", "password", "10");
		writer.log(LogStatus.INFO, "Waiting to enter password");
		FunctionLibrary.typeAction(driver1, "id", "password", "master");
		writer.log(LogStatus.INFO, "Entering password");
		FunctionLibrary.clickAction(driver1, "xpath","//*[@id='btnsubmit']");
		writer.log(LogStatus.PASS, "Waiting to clik login button");
		Thread.sleep(2000);
		FunctionLibrary.closeApp(driver1,"id","logout");
		writer.log(LogStatus.ERROR, "Clicking Logout button");
		Thread.sleep(2000);
		FunctionLibrary.closeApp(driver1, "xpath","//button[text()='OK!']");
		report.endTest(writer);
		report.flush();
		
		
		
	}

}
