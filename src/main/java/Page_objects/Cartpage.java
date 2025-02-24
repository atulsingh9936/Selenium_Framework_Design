package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cartpage extends Abstract_Component {
    WebDriver driver;
    public Cartpage(WebDriver driver){
        // intialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

  //WebElement userEmail= driver.findElement(By.id("userEmail"));
    // page factory

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css=".cartSection h3")
  private  List<WebElement> cartProducts;


public  Boolean VerifyProductdisplay(String productName){
    Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
    return match;
}

public Checkout_page goToCheckout(){
    checkoutEle.click();
    return new Checkout_page(driver);


}



}
