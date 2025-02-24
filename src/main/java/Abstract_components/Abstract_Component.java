package Abstract_components;

import Page_objects.Cartpage;
import Page_objects.Orderpage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Abstract_Component {
    WebDriver driver;

    public Abstract_Component(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = "[routerlink*='cart']")
    WebElement  cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement  orderHaeder;


    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public Cartpage goToCartPage(){
        cartHeader.click();
        Cartpage cartpage = new Cartpage(driver);
        return cartpage;

    }
    public Orderpage goToOrdersPage(){
        orderHaeder.click();
        Orderpage orderpage = new Orderpage(driver);
        return orderpage;

    }

    public  void  waitForElementToDisappear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(ExpectedConditions.invisibilityOf(ele));

    }

}
