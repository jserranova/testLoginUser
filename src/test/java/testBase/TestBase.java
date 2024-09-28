package testBase;

import configDriver.ConfigDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.login.Login;

import java.time.Duration;

public class TestBase  {


  WebDriver driver = ConfigDriver.setupFirefoxDriver();
  private Login login;


  @BeforeClass
  public void init() {
   login = PageFactory.initElements(driver, Login.class);

  }

  @Test
  public void test() {
  login.userLogin();

  }

  @AfterTest
  public void close() {
    driver.quit();
  }

}
