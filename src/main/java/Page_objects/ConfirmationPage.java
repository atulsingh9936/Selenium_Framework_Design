package Page_objects;

import Abstract_components.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends Abstract_Component {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    WebElement ConfirmationPage;

    public String getConfirmationMessage(){
      return  ConfirmationPage.getText();

    }


}
