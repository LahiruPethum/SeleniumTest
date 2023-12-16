import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class ShoppingCart {
    @Test
    public static void testcase1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();


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



    @Test
    public static void testcase2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

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


    @Test
    public static void testcase3() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

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
}
