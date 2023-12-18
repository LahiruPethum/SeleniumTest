import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Logout {

    WebDriver driver;
    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

    }

//    validate user can log out using the logout option in header-middle clearfix which can be seen after login
    @Test(priority = 1)
    public void testcase1() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[3]/a")).click();
        Thread.sleep(5000);
        String expectedUrl = "https://www.singersl.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.close();
    }

//    validate user can log out using the logout option which is located in my account page
    @Test(priority = 2)
    public void testcase2() throws InterruptedException  {

        try {
           driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();
            Thread.sleep(5000);
            WebElement logoutLink = driver.findElement(By.xpath("//*[@id=\"account-profile\"]/div[1]/div[2]/ul/li[7]/a"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);

            Thread.sleep(5000);

            String expectedUrl = "https://www.singersl.com/";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl);

        } finally {
            driver.quit();
        }
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
