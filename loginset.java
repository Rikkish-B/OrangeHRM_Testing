package pro1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@Test
public class loginset {
	protected WebDriver driver;

    @BeforeTest
    public void testAdminLogin() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/auth/login");
        Thread.sleep(5000);
    

  
    	 driver.findElement(By.name("username")).sendKeys("Admin");
         driver.findElement(By.name("password")).sendKeys("admin123");
         driver.findElement(By.xpath("//button[@type='submit']")).click();
      
}   
         @AfterTest
         public void teardown() throws InterruptedException {
        	Thread.sleep(4000);
       	driver.quit();
    }
}
