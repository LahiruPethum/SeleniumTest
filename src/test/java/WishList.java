import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
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
import java.util.List;

public class WishList {

    WebDriver driver;
    WebElement checkbox;
    WebElement checkbox1;

    @BeforeMethod
    public void init(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

        driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("BLACK+DECKER 19 Litre Double Glass Multifunction Toaster Oven With Rotisserie (TRO19RDG-B5)");
        driver.findElement(By.xpath("//*[@id=\"search-form\"]/div[2]")).click();
    }
    //Validate adding a product to 'Wish List' page from the Product that is displayed in the 'Related Products' section of 'Product Display' page
    @Test(priority = 1)
    public void testcase1() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[3]/a/img")).click();
        Thread.sleep(3000);
        checkbox = driver.findElement(By.xpath("//*[@id=\"block-mainpagecontent\"]/article/div/div[1]/div[1]/a"));
        checkbox1 = driver.findElement(By.xpath("//*[@id=\"block-mainpagecontent\"]/article/div/div[1]/div[1]/a/i"));


    }

//Validate adding a product to 'Wish List' page from the Search Results page
    @Test(priority = 2)
    public void testcase2() throws InterruptedException {

        checkbox = driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[2]/a"));
        checkbox1 = driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[2]/a/i"));

    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        String classAttributeValue = checkbox.getAttribute("class");

        if (!classAttributeValue.contains("is-active")) {
            checkbox1.click();
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"account-profile\"]/div[1]/div[2]/ul/li[3]/a")).click();

        List<WebElement> wishlistItems = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("wishlist-item")));
        Thread.sleep(3000);
        String expectedText = "BLACK+DECKER 19 LITRE DOUBLE GLASS MULTIFUNCTION TOASTER OVEN WITH ROTISSERIE (TRO19RDG-B5)";
        boolean matchFound = false;

        for (WebElement wishlistItem : wishlistItems) {
            List<WebElement> h3Elements = wishlistItem.findElements(By.tagName("h3"));

            for (WebElement h3Element : h3Elements) {
                String actualText = h3Element.getText();
                System.out.println("Actual Text: " + actualText);
                if (expectedText.equals(actualText)) {
                    matchFound = true;
                    break;
                }
            }

            if (matchFound) {
                break; // Exit the outer loop if a match is found in any wishlist item
            }
        }

        Assert.assertTrue(matchFound, "Expected text not found in any h3 element.");

        driver.close();
    }
}
