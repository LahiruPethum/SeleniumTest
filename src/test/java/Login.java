import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login {
    WebDriver driver;

    @Parameters({"url"})
    @BeforeMethod
    public void init(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();
    }

    //use invalid email and valid password
    @Test(priority = 1)
    public void testcase1() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum");
        String msg1 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please include an '@' in the email address. 'pethum' is missing an '@'.",msg1);
        Thread.sleep(5000);
        passwordInput.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("@");
        String msg2 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput2=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please enter a part following '@'. 'pethum@' is incomplete.",msg2);
        Thread.sleep(5000);
        passwordInput2.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gmail.");
        String msg3 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput3=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("'.' is used at a wrong position in 'gmail.'.",msg3);
        Thread.sleep(5000);
        passwordInput3.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(5000);
        Assert.assertEquals("Please register before login",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());

        driver.close();
    }

    //use valid email and invalid password
    @Test(priority = 2)
    public void testcase2() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("5666fgfgh");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(5000);
        Assert.assertEquals("Invalid email or password",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());
        driver.close();
    }

        //use invalid email and invalid password
    @Test(priority = 3)
    public void testcase3() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("5666fgfgh");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(5000);
        Assert.assertEquals("Please register before login",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());
        driver.close();
    }

//    use valid email and valid password
    @Test(priority = 4)
    public void testcase4() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(5000);
        String expectedUrl = "https://www.singersl.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.close();
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
