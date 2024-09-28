package pageObject.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Wrapper {

    private WebDriver driver;

    //Builder
    public Wrapper(WebDriver driver) {
        this.driver = driver;
    }

    // Locate an item
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    //Locate item list
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // Get a text
    public String getText(WebElement element) {
        return element.getText();
    }

    // text
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    //input Text
    public void insertValue(By locator, String inputText) {
        driver.findElement(locator).sendKeys(inputText);
    }

    // Click Button
    public void clickButton(By locator) {
        driver.findElement(locator).click();
    }

    public void clickButton(WebElement element) {
        element.click();
    }

    //Clear Box

    public void clearInput(WebElement element) {
        element.isDisplayed();
    }

    //whether an element is present or not
    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

    //if element exist
    public Boolean isExistElements(By locator) {

        if (driver.findElements(locator).size() != 0) {
            return driver.findElements(locator).size() != 0;
        } else {
            return false;
        }
    }

    public void returnPage(){
        driver.navigate().to("https://www.saucedemo.com/v1/index.html");
    }

    //Enter Url
    public void visitUrl(String url) {
        driver.get(url);
    }

}
