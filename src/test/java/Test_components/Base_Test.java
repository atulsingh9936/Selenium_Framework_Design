package Test_components;

import Page_objects.Landing_page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Base_Test {
    public WebDriver driver;
    public Landing_page page;

    public WebDriver intializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\Resources\\GlobalData.properties");
        prop.load(fis);
      String browserName=  System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
     // prop.getProperty("browser");
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Atul\\OneDrive\\Documents\\chromedriver-win64\\chromedriver.exe");

     if (browserName.contains("chrome")){
         ChromeOptions options = new ChromeOptions();
         WebDriverManager.chromedriver().setup();
         if(browserName.contains("headless")){
              options.addArguments("headless");
         }

         driver = new ChromeDriver(options);
         driver.manage().window().setSize(new Dimension(1440,900));





     } else if (browserName.equalsIgnoreCase("firefox")) {
         System.setProperty("webdriver.gecko.driver", "C:\\Users\\Atul\\OneDrive\\Documents\\geckodriver.exe");
          driver = new FirefoxDriver();

         // firefox code
         
         
     } else if (browserName.equalsIgnoreCase("edge")) {
         // edge

       //  WebDriver driver = new EdgeDriver();
     }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;


    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts  = (TakesScreenshot) driver;
        File source= ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";

    }


    public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {
        // read json to string

        String jsonContent=   FileUtils.readFileToString(new File(filepath));

        //String To HashMap(Jackson databind)

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data  = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;


    }
    @BeforeMethod(alwaysRun = true)
    public Landing_page launchApplication() throws IOException {
       driver=  intializeDriver();
       page = new Landing_page(driver);
        page.Goto();
        return page;
    }

    @AfterMethod(alwaysRun = true)
    public  void teardown(){
        driver.close();
    }



}
