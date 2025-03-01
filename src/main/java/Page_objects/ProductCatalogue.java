package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends Abstract_Component {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        // intialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

  //  List<WebElement> products=   driver.findElements(By.cssSelector(".mb-3"));
    // page factory

    @FindBy(css = ".mb-3")
  List<WebElement>  products;


  @FindBy(css = "ngx-spinner-overlay.ng-animating")
   // @FindBy(css = ".ngx-spinner-overlay")
    WebElement  spinner;


    By ProductsBy= By.cssSelector(".mb-3");
    By addTocart= By.cssSelector(".card-body button:last-of-type");
   By toastMessage =By.cssSelector("#toast-container");

    public List<WebElement> getProductlist(){
       waitForElementToAppear(ProductsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod= getProductlist().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
      WebElement prod  = getProductByName(productName);
        prod.findElement(addTocart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);


    }






}
