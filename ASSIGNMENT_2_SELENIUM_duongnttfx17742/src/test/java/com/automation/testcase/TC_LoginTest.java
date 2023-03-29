package com.automation.testcase;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenShot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {
	private WebDriverWait wait;
	PropertiesFileUtils pro = new PropertiesFileUtils();
	Actions act;
	@BeforeMethod
	public void setPage() {
		driver.get(pro.getProperty("base_url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws Exception {
        //Lấy URL từ properties file và tải trang.
		LoginPage lg = new LoginPage(driver);
		lg.icon_signin();
		lg.enterEmail(email);
		lg.enterPassword(password);
		lg.clickSignIn();
		WebElement iconSignout = driver.findElement(By.xpath(pro.getProperty("icon_signout_")));
		iconSignout.click();
		//act.click(iconSignout).build().perform();
    	Thread.sleep(2000);
	}
    @DataProvider(name="Excel")
    public Object[][] testDataGenerator() throws IOException {
    	FileInputStream file = new FileInputStream("./data\\assignment2_data_test.xlsx");
    	XSSFWorkbook workbook = new XSSFWorkbook(file);
    	XSSFSheet loginSheet = workbook.getSheet("Login");
    	int numberOfRowData = loginSheet.getPhysicalNumberOfRows();
    	Object[][] data = new Object[numberOfRowData][2]; //có 2 cột 
    	for (int i= 0; i< numberOfRowData; i++) {
    		XSSFRow row = loginSheet.getRow(i);
    		XSSFCell email = row.getCell(0);
    		XSSFCell password = row.getCell(1);
    		data[i][0] = email.getStringCellValue();
    		data[i][1] = password.getStringCellValue();
    	}
      	 return data;
    }
    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        //ITestResult để lấy trạng thái và tên và tham số của từng Test Case
        // phương thức này sẽ được gọi mỗi khi @Test thực thi xong,
        // ta có thể kiểm tra kết quả testcase taị đây.
    	if (ITestResult.FAILURE == result.getStatus()) {
    		try {
                //1. lây tham số (parameters) đầu vào của TC vừa chạy
                //email:0, password:1
    			String email = (String)result.getParameters()[0];
    	         //2. Lấy ra phần tên trong email để làm tên của screenshot
    	         // Tìm vij trí(int index) của kí tự ‘@’ và lấy ra chuỗi con
    	         // đứng trước @  qua thư viện của String là: indexOf() và subString()
    			int index = email.indexOf("@");
    			String accountName = email.substring(0,index);
    			CaptureScreenShot.takeScreenshot(driver, accountName);
    			CaptureScreenShot.attachScreenShot(accountName);
    		} catch (Exception e) {
                System.out.println("Lỗi xảy ra screenshot " + e.getMessage());
            }

    	}

    }
    

}
