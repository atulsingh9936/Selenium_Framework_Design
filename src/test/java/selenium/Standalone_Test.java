package selenium;

import Page_objects.Landing_page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Standalone_Test {
    public static void main(String[] args) {
        String ProductName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        Landing_page page = new Landing_page(driver);


        driver.findElement(By.id("userEmail")).sendKeys("atul25091997@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Atul9936@");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
     List<WebElement> products=   driver.findElements(By.cssSelector(".mb-3"));
    WebElement prod= products.stream().filter(product->
             product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);

    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        // ng-animating
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

      List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
     Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
     String message=   driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
       driver.close();



    }
}
