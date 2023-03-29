package com.automation.utils;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class CaptureScreenShot {
	
	public static void takeScreenshot(WebDriver driver, String name) {
		
		try {
			TakesScreenshot tss = (TakesScreenshot)driver;
			File source = tss.getScreenshotAs(OutputType.FILE);
			String imgPath = "./Screenshots" + "/" + name + ".png";
			File imgFile = new File(imgPath);
			FileHandler.copy(source, imgFile);
		}catch(Exception e) {
			System.out.println("Exception while taking screenshot"+ e.getMessage());
		}
	}
	public static void attachScreenShot (String filePath) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			//lấy ra file theo đường dẫn
			File file = new File (filePath);
			Reporter.log("<br> <a title= \"Screenshots\" herf =\""+file.getAbsolutePath()+"\">");
			Reporter.log("<img alt='"+file.getName()+"'scr'"+file + "'height='240' width='418'/><br>");
			
		} catch (Exception E) {}
	}
}
