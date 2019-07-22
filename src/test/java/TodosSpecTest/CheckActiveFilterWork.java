package TodosSpecTest;


import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AllItemsPage;

import static com.codeborne.selenide.Selenide.open;


public class CheckActiveFilterWork extends TestBase {


    @Description("check that added items are displayed when the Active filter is selected")
    @Test()
    public void checkFilterActiveWork() {

        open("http://todomvc.com/examples/react/#/", AllItemsPage.class).
                addItems("Item", 5)
                .clickActiveButton()
                .itemsAreEqual("Item", 5);
    }
}

