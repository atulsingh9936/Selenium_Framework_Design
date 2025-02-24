package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_page extends Abstract_Component {
    WebDriver driver;
    public Checkout_page(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css =  "[placeholder='Select Country']" )
    WebElement country;

    @FindBy(css =  ".action__submit" )
    WebElement submit;

     //button[contains(@class,'ta-item')])[2]

    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

  By results =By.cssSelector(".ta-results");


    public void selectCountry(String Countryname){

        Actions a = new Actions(driver);
        a.sendKeys(country,Countryname).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();

    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}
