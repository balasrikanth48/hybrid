package driverScript;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunLib.FunctionLibrary;
import utils.ExcelUtils;

public class DriverScript {
 static WebDriver driver=null;
 static ExtentReports report;
 static ExtentTest  test;
	public static void main(String[] args) throws Exception
 {
	ExcelUtils el=new ExcelUtils();
	
	for (int i = 1; i <=el.getrowcount("MasterTestCases"); i++) 
	{
		String ModuleStatus="";
		
	   if (el.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
	   {
		String TCModlue=el.getData("MasterTestCases", i, 1);
		report=new ExtentReports("E:\\Srikanth_82\\HybridFrameWork\\Reports\\"+TCModlue+FunctionLibrary.getdate()+".html");
		test=report.startTest(TCModlue);
		 for (int j = 1; j <=el.getrowcount(TCModlue); j++)
		 {
			 String Description=el.getData(TCModlue, j, 0);
			 String Function_Name=el.getData(TCModlue, j, 1);
			 String Locator_Type=el.getData(TCModlue, j, 2);
			 String Locator_Value=el.getData(TCModlue, j, 3);
			 String Test_Data=el.getData(TCModlue, j, 4);
	try{		 
		if (Function_Name.equalsIgnoreCase("startbrowser")) 
		{
		     driver=FunctionLibrary.startBrowser();
		     test.log(LogStatus.INFO, "browser Started");
		}else if(Function_Name.equalsIgnoreCase("openApplication")) 
		{
			FunctionLibrary.openApp(driver);
			test.log(LogStatus.INFO, "Application lAunched");
		}else if(Function_Name.equalsIgnoreCase("waitForElement")){
			FunctionLibrary.waitforElement(driver, Locator_Type, Locator_Value,Test_Data);
			test.log(LogStatus.INFO, "Waiting for ");
		}else if(Function_Name.equalsIgnoreCase("typeAction"))
		{
		 FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
		 test.log(LogStatus.INFO, "Entering the values");
		}else if(Function_Name.equalsIgnoreCase("clickAction"))
		{
			FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
			test.log(LogStatus.INFO, "Clicking the buttons");
		}else if(Function_Name.equalsIgnoreCase("closeBrowser"))
		{
		  FunctionLibrary.closeApp(driver, Locator_Type, Locator_Value);
		  test.log(LogStatus.INFO, "Closing Application");
		}else if(Function_Name.equalsIgnoreCase("Capute ")) 
		{
			FunctionLibrary.CapturingData(driver, Locator_Type, Locator_Value);
			test.log(LogStatus.INFO, "Caputring SupplierNumber");
		}else if(Function_Name.equals("tableValidation")){
		FunctionLibrary.tableValidation(driver, Test_Data);
			test.log(LogStatus.INFO, "Table Validation is done");
		}
		el.SetCellData(TCModlue, j, 5, "Pass");
		ModuleStatus="Pass";
		
	}catch(Exception e)
	{
		
		
		 e.printStackTrace();
		 System.out.println("the exceptions is "+ e);
		el.SetCellData(TCModlue, j, 5,"Fail");
		ModuleStatus="Fail";
		String reqdate=FunctionLibrary.getdate();
		File sf= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sf,new File("E:\\Srikanth_82\\HybridFrameWork\\ScreenShots\\"+Description+reqdate+".png"));
		test.log(LogStatus.FAIL, Description);
		Thread.sleep(5000);
		test.log(LogStatus.INFO, test.addScreenCapture("E:\\Srikanth_82\\HybridFrameWork\\ScreenShots\\"+Description+reqdate+".png"));
		
		break;
	      }
		 }
		 if (ModuleStatus.equalsIgnoreCase("Pass")) {
			el.SetCellData("MasterTestCases", i, 3, "Pass");
		}else
		{
			el.SetCellData("MasterTestCases", i, 3, "Fail");
		}
		report.endTest(test);
		report.flush();
		 
		 
	   }else
	   {
		   el.SetCellData("MasterTestCases", i,3, "NotExcuted");
	   }
	 
	 }

   }

}
