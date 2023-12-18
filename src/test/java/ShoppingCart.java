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
import java.time.Instant;


public class ShoppingCart {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
    }

//    Validate navigating to 'Shopping Cart' page from the Success message
    @Test(priority = 1)
    public static void testcase1() throws InterruptedException {

        try {
            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("oven");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//*[@id='popularity']/div/div[1]/div[3]/a/img")).click();
            Thread.sleep(5000);

            WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='add-to-cart-id']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
            Thread.sleep(5000);

            driver.findElement(By.xpath("/html/body/div[6]/div[9]/div/div[2]/div/div/a")).click();
            Thread.sleep(5000);

            String expectedUrl = "https://www.singersl.com/cart";
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed! Expected: " + expectedUrl + ", Actual: " + actualUrl);
            }
        } finally {
            driver.quit();
        }
    }


//    Validate navigating to 'Shopping Cart' page from the 'Shopping Cart' header-middle clearfix section
    @Test(priority = 2)
    public static void testcase2() throws InterruptedException {

        try {
            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("oven");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//*[@id='popularity']/div/div[1]/div[3]/a/img")).click();
            Thread.sleep(5000);

            WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='add-to-cart-id']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
            Thread.sleep(5000);

            driver.findElement(By.xpath("/html/body/div[6]/div[9]/div/div[3]")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"block-singer-shopping-cart\"]")).click();

            String expectedUrl = "https://www.singersl.com/cart";
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed! Expected: " + expectedUrl + ", Actual: " + actualUrl);
            }
        } finally {
            driver.quit();
        }
    }

//    Validate navigating to 'Shopping Cart' page from the 'Shopping Cart' header-middle clearfix section
    @Test(priority = 3)
    public static void testcase3() throws InterruptedException {

        try {
            driver.findElement(By.xpath("//*[@id=\"block-singer-shopping-cart\"]")).click();

            String expectedUrl = "https://www.singersl.com/cart";
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed! Expected: " + expectedUrl + ", Actual: " + actualUrl);
            }
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
