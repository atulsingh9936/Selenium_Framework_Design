package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderpage extends Abstract_Component {
    WebDriver driver;
    public Orderpage(WebDriver driver){
        // intialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

  //WebElement userEmail= driver.findElement(By.id("userEmail"));
    // page factory

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css="tr td:nth-child(3)")
  private  List<WebElement> ProductNames;


public  Boolean VerifyOrderdisplay(String productName){
    Boolean match= ProductNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
    return match;
}





}
