import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;


 class DriverSettings {

    static ItemsStartPage setUpDriver(){


        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;



        open("http://todomvc.com/examples/react/#/");
        return new ItemsStartPage();
        }
    }

