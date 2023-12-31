import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AddToCart {
    static WebDriver driver;
    static WebDriverWait wait;

    @Parameters({"url"})
    @BeforeMethod
    public void init(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(url);
        driver.manage().window().maximize();
    }
    //Validate adding the product to Cart from 'Products' Page
    @Test(priority = 1)
    public static void testcase1() throws InterruptedException {


        try {

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-search']")));
            searchInput.sendKeys("TCL 43\" Full HD Google TV - TCL43S5400");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-form\"]/div[2]")));
            searchButton.click();

            Thread.sleep(3000);

            WebElement productImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"popularity\"]/div/div/div[3]/a/img")));
            productImage.click();

            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-id\"]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            WebElement closePopupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[9]/div/div[2]/div/div/a")));
            closePopupButton.click();

            Thread.sleep(3000);

            String expectedUrl = "https://www.singersl.com/cart";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl);

        } finally {
            driver.quit();
        }
    }

    //Validate adding the product to Cart from 'Wish List' Page
    @Test(priority = 2)
    public static void testcase2() throws InterruptedException {

        try {

            driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

            driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
            WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
            passwordInput.sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-search']")));
            searchInput.sendKeys("TCL 43\" Full HD Google TV - TCL43S5400");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-form\"]/div[2]")));
            searchButton.click();

            Thread.sleep(3000);

            WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[2]/a"));
            WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[2]/a/i"));

            String classAttributeValue = checkbox.getAttribute("class");

            if (!classAttributeValue.contains("is-active")) {
                checkbox1.click();
            }
            driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"account-profile\"]/div[1]/div[2]/ul/li[3]")).click();
            driver.findElement(By.xpath("//*[@id=\"edit-items-12054-actions-add-to-cart\"]")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"product_add_to_catrted\"]/a")).click();

            Thread.sleep(3000);

            String expectedUrl = "https://www.singersl.com/cart";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl);

        } finally {
            driver.quit();
        }
    }

    //Validate adding the product to Cart from Search Results Page
    @Test(priority = 3)
    public static void testcase3() throws InterruptedException {

        try {

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-search']")));
            searchInput.sendKeys("TCL 43\" Full HD Google TV - TCL43S5400");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-form\"]/div[2]")));
            searchButton.click();

            Thread.sleep(3000);

            driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[4]/div[2]/div[2]/a")).click();

            WebElement closePopupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/a")));
            closePopupButton.click();

            Thread.sleep(3000);

            String expectedUrl = "https://www.singersl.com/cart";
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
