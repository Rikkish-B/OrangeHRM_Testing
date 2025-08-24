package pro1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Management extends loginset {

    WebDriverWait wait;
    List<String> createdIds = new ArrayList<>();

    @DataProvider
    public Object[][] provideEmployeeData() {
        return new Object[][] {
            {"stark", "Kent", "140cjd0"},
            {"steve", "Wayne", "08nwdub20"}
        };
    }

    @Test(priority = 1, dataProvider = "provideEmployeeData")
    public void testAddMultipleEmployees(String firstName, String lastName, String empId) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Search']")));
        searchBox2.clear();
        searchBox2.sendKeys("PIM");
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add Employee"))).click();
        WebElement FN = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
        FN.clear();
        FN.sendKeys(firstName);
        WebElement LN = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
        LN.clear();
        LN.sendKeys(lastName);
        WebElement empID = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")));
        new Actions(driver).click(empID)
                           .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                           .sendKeys(Keys.DELETE).perform();
        empID.sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        createdIds.add(empId);
        System.out.println("Employee Added: " + firstName + " " + lastName + " with ID: " + empId);
    }

    @Test(priority = 2)
    public void testSearchAndValidateEmployee() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        pimMenu.click();
        WebElement empListMenu = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Employee List")));
        empListMenu.click();
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")));
        searchBox.clear();
        searchBox.sendKeys(createdIds.get(0));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement resultCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-card']//div[@role='cell'][2]")));
        Assert.assertEquals(resultCell.getText(), createdIds.get(0), "Employee ID doesn't match");
        System.out.println("Employee found with ID: " + createdIds.get(0));
    }

    @Test(priority = 3)
    public void testEditEmployeeInformation() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        pimMenu.click();
        WebElement empListMenu = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Employee List")));
        empListMenu.click();
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")));
        searchBox.clear();
        searchBox.sendKeys(createdIds.get(0));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement employeeLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-table-card']//div[@role='cell'][3]")));
        employeeLink.click();
        WebElement middleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("middleName")));
        new Actions(driver).click(middleNameField)
                           .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                           .sendKeys(Keys.DELETE).perform();
        String updatedMiddleName = "Kenny";
        middleNameField.sendKeys(updatedMiddleName);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//form[contains(@class,'oxd-form')]//button[@type='submit'])[1]")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveButton);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", saveButton);
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));
        WebElement updatedField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("middleName")));
        Assert.assertEquals(updatedField.getAttribute("value"), updatedMiddleName, "Middle Name was not updated successfully");
        System.out.println("Middle Name updated successfully for Employee ID: " + createdIds.get(0));
   
    }
    
    @Test(priority = 4)
    public void testDeleteEmployeeRecord() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement empListMenu = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Employee List")));
        empListMenu.click();
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Employee Id']/../following-sibling::div//input")));
        searchBox.clear();
        searchBox.sendKeys("140cjd0"); 
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']")));
        searchBtn.click();
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='oxd-table-card']//button[contains(@class,'oxd-icon-button')])[2]")));
        deleteBtn.click();
        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Yes, Delete']")));
        confirmDelete.click();
        WebElement noRecords = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[normalize-space()='No Records Found']")));
        Assert.assertTrue(noRecords.isDisplayed(), "Employee record was not deleted successfully!");
    }


    
}

