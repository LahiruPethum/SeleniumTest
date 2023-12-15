import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

    //use invalid email and valid password
    @Test
    public void testcase1(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum");
        String msg1 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please include an '@' in the email address. 'pethum' is missing an '@'.",msg1);
        passwordInput.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("@");
        String msg2 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput2=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please enter a part following '@'. 'pethum@' is incomplete.",msg2);
        passwordInput2.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gmail.");
        String msg3 = driver.findElement(By.xpath("//*[@id=\"email\"]")).getAttribute("validationMessage");
        WebElement passwordInput3=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("'.' is used at a wrong position in 'gmail.'.",msg3);
        passwordInput2.clear();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please register before login",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());

        driver.close();
    }

    //use valid email and invalid password
    @Test
    public void testcase2(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("5666fgfgh");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Invalid email or password",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());
        driver.close();
    }

    //use invalid email and invalid password
    @Test
    public void testcase3(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("5666fgfgh");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Assert.assertEquals("Please register before login",driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/div/div")).getText());
        driver.close();
    }

    //use valid email and valid password
    @Test
    public void testcase4(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.singersl.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"block-singer-account-menu\"]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("pethum013@gmail.com");
        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        String expectedUrl = "https://www.singersl.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.close();
    }


}
