package com.lambdatest.Tests;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

import org.openqa.selenium.Keys;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class SingleTest {

	public static WebDriver driver;
	public static String status = "failed";

	@Before
	public void setUp() throws Exception {

		String browser = Configuration.readConfig("browser");
		String version = Configuration.readConfig("version");
		String os = Configuration.readConfig("os");
		String res = Configuration.readConfig("resolution");

		String username = System.getenv("LT_USERNAME") != null ? System.getenv("LT_USERNAME") : Configuration.readConfig("LambdaTest_UserName");
		String accesskey = System.getenv("LT_ACCESS_KEY") != null ? System.getenv("LT_ACCESS_KEY") : Configuration.readConfig("LambdaTest_AppKey");

		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
		capability.setCapability(CapabilityType.VERSION, version);
		capability.setCapability(CapabilityType.PLATFORM, os);
		capability.setCapability("build", "Junit Single Test");
		capability.setCapability("name", "JUnit Single");
		capability.setCapability("screen_resolution", res);

		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";

		driver = new RemoteWebDriver(new URL(gridURL), capability);
	}

	@Test
	public void test() throws InterruptedException {

		// Launch the apppp
		driver.get("https://www.lambdatest.com/automation-demos/");

		// Enter the username
		driver.findElement(By.id("username")).sendKeys("lambda");

		// Enter the password
        driver.findElement(By.id("password")).sendKeys("lambda123");

        // Click on Login button
        WebElement lgnbtn=driver.findElement(By.xpath("//button[@class='applynow']"));
        lgnbtn.sendKeys(Keys.ENTER);   
    

        // Enter email after getting logged in
        driver.findElement(By.xpath("//input[@id='developer-name']")).sendKeys("qanikhil1993@gmail.com");

        //Click on the Populate button
        driver.findElement(By.xpath("//input[@id='populate']")).click();
                
        //Engage with the Popup
        driver.switchTo().alert().accept();

        //Click on the feedback#1
        driver.findElement(By.id("month")).click();

        //Click on the feedback#2
        driver.findElement(By.id("customer-service")).click();

        //Select the value from dropdown for the Payment mode
        Select payment=new Select(driver.findElement(By.name("preferred-payment")));
        payment.selectByIndex(2);

        //Click on the I have made Checkbox option
        driver.findElement(By.xpath("//input[@type='checkbox'][@id='tried-ecom']")).click();

        // //Find the scale and click on the Rating==9
        // JavascriptExecutor js1= (JavascriptExecutor) driver;
        // WebElement slider= driver.findElement(By.xpath("//div[@id='slider']/span"));       
        // js1.executeScript("argument[0].setAttribute('style', 'left: 88.8889%;')", slider);

        //Open link in a new browser tab
        ((JavascriptExecutor) driver).executeScript("window.open()");
ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
driver.switchTo().window(tabs.get(1));
driver.get("https://www.lambdatest.com/selenium-automation");


        


		// // Add new item is list
		// driver.findElement(By.id("sampletodotext")).clear();
		// driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
		// driver.findElement(By.id("addbutton")).click();

		// // Verify Added item
		// String item = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
		// Assert.assertTrue(item.contains("Yey, Let's add it to list"));
		// status = "passed";

	}

	@After
	public void afterTest() {
		((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
		driver.quit();
	}

}
