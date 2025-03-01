package stepDefinition;

import Page_objects.*;
import Test_components.Base_Test;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class stepDefinition_Implementation extends Base_Test {
    public Landing_page landingPage;
    ProductCatalogue catalogue;
    Cartpage cartpage;
    ConfirmationPage confirmationPage;


    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        catalogue = page.loginapplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = catalogue.getProductlist();
        catalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_the_order(String productName) {

        cartpage = catalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductdisplay(productName);
        Assert.assertTrue(match);
        Checkout_page checkoutPage = cartpage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();

    }


    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmation_page(String string) {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(string));
        driver.close();

    }

    @Then("{string} message is displayed")
    public void Something_message_is_displayed(String string){
        Assert.assertEquals("Incorrect email or password.",page.GetErrorMessage());
        driver.close();
    }


    }

