package TodosSpecTest;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AllItemsPage;

import static com.codeborne.selenide.Selenide.open;


public class CheckTheEditItemFunctionality extends TestBase {
    @BeforeMethod
    public void beforeMethod() {
        open("http://todomvc.com/examples/react/#/", AllItemsPage.class)
                .addItemAllItemsPage("@#$%^")
                .addItemAllItemPageComposite("a a", 30)
                .itemTextLengthIsExpected(1, 30)
                .addItemAllItemPageComposite("a @$%", 70)
                .itemTextLengthIsExpected(2, 70);
    }
    @Description("check the Edit and remove by editing functionality")
    @Test
    public void checkTheEditFunctionality() {
        new AllItemsPage()
                .deleteItemByEditing(0)
                .itemIsDisplayedInTheList("@#$%^",0)
                .addItemAllItemPageComposite("a a", 30)
                .addItemAllItemPageComposite("a a", 30)
                .itemIsDisplayedInTheList("a aa aa aa aa aa aa aa aa aa a", 3)
                .editItem(1, 300)
                .itemTextLengthIsExpected(1, 300)
                .clickActiveButton();
    }

}
