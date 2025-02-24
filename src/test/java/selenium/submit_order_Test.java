package selenium;

import Page_objects.*;
import Test_components.Base_Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submit_order_Test extends Base_Test {
    String ProductName = "ZARA COAT 3";
   @Test(dataProvider = "getData",groups = {"Purchase"})
           public void submitorder(HashMap<String,String> input) throws IOException, InterruptedException {
     //   String ProductName = "ZARA COAT 3";
        ProductCatalogue catalogue = page.loginapplication(input.get("email"), input.get("password"));
        List<WebElement> products = catalogue.getProductlist();
        catalogue.addProductToCart(input.get("ProductName"));
        Cartpage cartpage = catalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductdisplay(input.get("ProductName"));
        Assert.assertTrue(match);
        Checkout_page checkoutPage = cartpage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();


        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

    }

    // To verify Zara coat 3 is displaying in orders page

    @Test(dependsOnMethods = {"submitorder"})
    public void OrderHistoryTest(){
        ProductCatalogue catalogue = page.loginapplication("atul25091997@gmail.com", "Atul9936@");
      Orderpage orderpage=  catalogue.goToOrdersPage();
     Assert.assertTrue(orderpage.VerifyOrderdisplay(ProductName));



    }



    @DataProvider
    public Object[][] getData() throws IOException {

      List<HashMap<String,String>> data= getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PuchaseOrder.json");

      return new Object[][]{{data.get(0)},{data.get(1)}};
    }

    }

//        HashMap<String,String> map =  new HashMap<String,String>();
//        map.put("email","atul25091997@gmail.com");
//        map.put("password","Atul9936@");
//        map.put("ProductName","ZARA COAT 3");
//
//        HashMap<String,String> map1 =  new HashMap<String,String>();
//        map1.put("email","atul.singh219@gmail.com");
//        map1.put("password","Test@123");
//        map1.put("ProductName","ADIDAS ORIGINAL");


//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//
//

