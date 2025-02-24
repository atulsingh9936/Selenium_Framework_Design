package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landing_page extends Abstract_Component {
    WebDriver driver;
    public Landing_page(WebDriver driver){
        // intialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

  //WebElement userEmail= driver.findElement(By.id("userEmail"));
    // page factory

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement passwordEle;

    @FindBy(id="login")
    WebElement  submit;


    @FindBy(css="[class*='flyInOut']")
    WebElement  errormessage;


    public ProductCatalogue loginapplication(String email, String password){
      userEmail.sendKeys(email);
      passwordEle.sendKeys(password);
      submit.click();
        ProductCatalogue catalogue = new ProductCatalogue(driver);
        return catalogue;


    }

    public String GetErrorMessage(){
        waitForWebElementToAppear(errormessage);
       return errormessage.getText();


    }

    public void Goto(){
        driver.get("https://rahulshettyacademy.com/client");

    }
}
