package pro1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Dashboard extends loginset {
	@Test
	void mini() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement quicklaunch = wait.until(
		 ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title=\'Assign Leave\']")));
		 quicklaunch.click();
	//the dashboard is  failed test case without opening assign leave page but the test script passed with opening the page 
	}
}

