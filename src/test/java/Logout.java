import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Logout {
    @Test
    public void testcase1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[3]/a")).click();
        Thread.sleep(5000);
        String expectedUrl = "https://www.singersl.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.close();
    }

}
