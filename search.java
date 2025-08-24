package pro1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class search extends  loginset {
	@Test
	void find() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement searchBox = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Search']")));
		searchBox.sendKeys("Admin");
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		WebElement searchBox2 = wait.until(
		ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Search']")));
		searchBox2.sendKeys("PIM");
		 driver.findElement(By.xpath("//span[text()='PIM']")).click();
	}

}
