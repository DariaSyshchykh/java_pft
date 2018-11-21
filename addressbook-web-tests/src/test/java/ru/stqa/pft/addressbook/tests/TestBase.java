package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();
  private WebDriver driver;


  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }
}
