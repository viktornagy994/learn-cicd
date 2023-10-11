package selenium.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RegistrationTest {
    WebDriver driver;
    String URL = "https://kingfishair.progmasters.hu/";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void close() {
        driver.close();
    }
    @Test
    public void registerIncorrectPassword() throws InterruptedException {
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".mat-mdc-card-content > .mat-mdc-card-content")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("registration_email_input")).sendKeys("tesztkingfishair@gmail.com");
        driver.findElement(By.id("registration_name_input")).sendKeys("Teszt Béla");
        driver.findElement(By.id("registration_phonenumber_input")).sendKeys("+36301234567");
        driver.findElement(By.id("registration_password_input")).sendKeys("test");
        driver.findElement(By.id("registration_re_password_input")).sendKeys("test");
        Thread.sleep(1000);
       assertThat(driver.findElement(By.xpath("//div[5]/mat-form-field/div/div[2]/div/div/small")).getText(),is
               ("Password should be 8 to 20 characters with at least one special character(!@#&()–:;',?~$^=<>), one numeric, one small case and one upper case letter!(i.e Abcd@123)!"));
    }
    @Test
    public void registerIncorrectPhoneNumber() throws InterruptedException {
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".mat-mdc-card-content > .mat-mdc-card-content")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("registration_email_input")).sendKeys("tesztkingfishair@gmail.com");
        driver.findElement(By.id("registration_name_input")).sendKeys("Teszt Béla");
        driver.findElement(By.id("registration_phonenumber_input")).sendKeys("abcdefgh");
        driver.findElement(By.id("registration_password_input")).sendKeys("test");
        Thread.sleep(1000);
        assertThat(driver.findElement(By.xpath("//div[4]/mat-form-field/div/div[2]/div/div/small")).getText(),is
                ("Your phone number can only contain numeric values and + sign!"));
    }
    @Test
    public void registerIncorrectEmail() throws InterruptedException {
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
        driver.findElement(By.cssSelector(".mat-mdc-card-content > .mat-mdc-card-content")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("registration_email_input")).sendKeys("tesztkingfishair");
        driver.findElement(By.id("registration_name_input")).sendKeys("Teszt Béla");
        Thread.sleep(1000);
        assertThat(driver.findElement(By.xpath("//small")).getText(), is
                ("Your email must contain @ and . characters!"));
    }
}
