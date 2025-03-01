package selenium;

import Page_objects.Cartpage;
import Page_objects.Checkout_page;
import Page_objects.ConfirmationPage;
import Page_objects.ProductCatalogue;
import Test_components.Base_Test;
import Test_components.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.util.List;

public class Error_validation_Test extends Base_Test {
    public  ExtentReports extent;
   @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
           public void Loginerrorvalidation() throws IOException, InterruptedException {
        String ProductName = "ZARA COAT 3";


        page.loginapplication("atul2591997@gmail.com", "Atul9936@");
        //page.GetErrorMessage();
        Assert.assertEquals("Incorrect email or password.",page.GetErrorMessage());
   }

    @Test
    public void Producterrorvalidation() throws IOException, InterruptedException {
        String ProductName = "ZARA COAT 3";
        ProductCatalogue catalogue = page.loginapplication("atul25091997@gmail.com", "Atul9936@");
        List<WebElement> products = catalogue.getProductlist();
        catalogue.addProductToCart(ProductName);
        Cartpage cartpage = catalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductdisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }

    }

