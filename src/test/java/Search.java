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

public class Search {

    //    Validate searching with a non existing Product Name
    @Test
    public static void testcase1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("BLACK+DECKER 19 Litre Double Glass Multifunction Toaster Oven With Rotisserie (TRO19RDG-B5)");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            String expectedItemName = "BLACK+DECKER 19 Litre Double Glass Multifunction Toaster Oven With Rotisserie (TRO19RDG-B5)";
            String actualItemName = driver.findElement(By.xpath("//*[@id=\"popularity\"]/div/div/div[4]/div[1]/h2/a")).getText();

            Assert.assertEquals(actualItemName,expectedItemName);
        } finally {
            driver.quit();
        }
    }

    //    Validate searching with an existing Product Name
    @Test
    public static void testcase2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("fgfg");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            String expectedmsg = "Sorry, no products available!";
            String actualmsg = driver.findElement(By.xpath("//*[@id=\"block-mainpagecontent\"]/div/div/div[3]/div/strong")).getText();

            Assert.assertEquals(actualmsg,expectedmsg);
        } finally {
            driver.quit();
        }
    }

//    Validate searching without providing any Product Name
@Test
public static void testcase3() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    try {
        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();

        WebElement searchInput = driver.findElement(By.xpath("//*[@id='edit-search']"));
        searchInput.sendKeys("");

        WebElement searchButton = driver.findElement(By.xpath("//*[@id='search-form']/div[2]"));
        searchButton.click();

        Thread.sleep(5000);
        Assert.assertFalse(searchButton.isEnabled(), "Search button should be disabled");

    } finally {
        driver.quit();
    }
}

//Validate Search by category of product
    @Test
    public static void testcase4() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("Home & Kitchen Appliances");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            String expectedSearchHeader = "Home & Kitchen Appliances";
            String actualSearchHeader = driver.findElement(By.xpath("/html/body/div[3]/main/div/div[4]/div[2]/div/div/div[4]/div[1]/h1")).getText();

            Assert.assertEquals(actualSearchHeader,expectedSearchHeader);
        } finally {
            driver.quit();
        }
    }

    //Validate Search sub-category of the product

    @Test
    public static void testcase5() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.singersl.com/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//*[@id='edit-search']")).sendKeys("Laptops / Notebooks");
            driver.findElement(By.xpath("//*[@id='search-form']/div[2]")).click();
            Thread.sleep(5000);

            String expectedSearchHeader = "Laptops / Notebooks";
            String actualSearchHeader = driver.findElement(By.xpath("/html/body/div[3]/main/div/div[4]/div[2]/div/div/div[4]/div[1]/h1")).getText();

            Assert.assertEquals(actualSearchHeader,expectedSearchHeader);
        } finally {
            driver.quit();
        }
    }

}
