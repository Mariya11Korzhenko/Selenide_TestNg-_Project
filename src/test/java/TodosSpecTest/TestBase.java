package TodosSpecTest;

import com.codeborne.selenide.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.ActiveItemsPage;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.close;

public abstract class TestBase {

    @BeforeClass
    public static void setUpTestRunner(){

        //for one thread execution in chrome uncomment line below
//        WebDriverManager.chromedriver().version("75.0.3770.140").setup();
        //for parallel execution in chrome uncomment the line below
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
       // Configuration.browser = "chrome";
        Configuration.timeout = 5000;

    }
    @AfterMethod
    public void afterMethod(){
        new ActiveItemsPage().clearAllItems();
        clearBrowserCookies();
        close();
    }
}

