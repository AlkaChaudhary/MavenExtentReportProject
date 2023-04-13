package ePack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoTest {
	WebDriver driver;
	ExtentReports extent;
	@BeforeMethod
	public void configuration() {
		//Report Location created and Report Name  and Document title
		String path=System.getProperty("user.dir")+"\\reports\\extent_report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("ATWtestReport");
		reporter.config().setDocumentTitle("ATWReport");
		//Work with the Report
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating system", "Windows11");
		extent.setSystemInfo("Java - version", "Java 11.0.17");
		extent.setSystemInfo("Selenium - version", "Selenium 4.8.1");
		extent.setSystemInfo("TestNG - version", "TestNG 7.7.1");
		extent.setSystemInfo("Tested By", "Alka");
	}

 @Test
 public void sampleTest() {
	 ExtentTest eTest= extent.createTest("Sample Test Execution started");
	 driver=new FirefoxDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 eTest.info("Chrome browser got launched and maximized");
	 driver.get("https://surendrareddy1248.github.io/WebElementsTesting/");
	 eTest.info("Navigate to the ATW Webelements practice page");
	 String actual = driver.findElement(By.id("testtext_1")).getText();
	 eTest.info("Found the element and retrieved the tesxt");
	 String expected= "Sample Text for testing.1";
	 eTest.info("Asserting actual text with the expected text");
	 eTest.fail("Sample Test got failed");
	 Assert.assertEquals(actual, expected);	 	 
 }
@AfterMethod	
 public void closure() {
		driver.quit();
		extent.flush();
	}
}
