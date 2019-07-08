import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ItemRunner {
    private ItemsStartPage itemsStartPage;


    @BeforeClass
    public void setup() {
        itemsStartPage = DriverSettings.setUpDriver();
    }


    @Test()
    public void scenario1() {
        int numberOfElements = 5;
        String textOfElement = "Item";
        boolean elementsAreEqual;

        for (int i = 0; i < numberOfElements; i++) {
            itemsStartPage.addItem(textOfElement + i);
        }

        itemsStartPage.clickActiveButton();
        for (int i = 0; i < numberOfElements; i++) {
            elementsAreEqual = itemsStartPage.containsItem(i, textOfElement + i);
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");
        }
    }


    @BeforeGroups(groups = {"Test1", "Test2"})
    public void setUpForTests() {
        itemsStartPage = DriverSettings.setUpDriver();
        int numberOfElements = 3;
        String textOfElement = "Item";
        for (int i = 0; i < numberOfElements; i++) {
            itemsStartPage.addItem(textOfElement + i);
        }
    }


    @Test(groups = {"Test1"})
    public void scenario2() {
        String textOfElement = "Item";
        boolean elementsAreEqual;
        for (int i = 0; i < 2; i++) {
            itemsStartPage.clickTogle(i);
        }
        itemsStartPage.clickCompletedButton();
        for (int i = 0; i < 2; i++) {
            elementsAreEqual = itemsStartPage.containsItem(i, textOfElement + i);
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");
        }
        itemsStartPage.clickTogle(1);
        itemsStartPage.clickActiveButton();
        for (int i = 0; i < 2; i++) {
            int expected = i + 1;
            elementsAreEqual = itemsStartPage.containsItem(i, textOfElement + expected);
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");

        }
    }

    @Test(groups = {"Test2"})
    public void scenario3() {
        List<String> allElements = itemsStartPage.getAllItemsTextList();
        boolean elementsAreEqual;
        Assert.assertFalse(itemsStartPage.checkClearCompletedButtonIsDisplayed(), "Clear Completed Button is displayed");
        itemsStartPage.clickTogleAll();
        for (int i = 0; i < allElements.size(); i++) {
            elementsAreEqual = itemsStartPage.getCompletedItems(i, allElements.get(i));
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");
        }
        Assert.assertEquals(itemsStartPage.getElementsLeft(), "0 items left");
        Assert.assertTrue(itemsStartPage.checkClearCompletedButtonIsDisplayed(), "Clear Completed Button is not displayed");
        itemsStartPage.clickActiveButton();
        itemsStartPage.clickTogleAll();
        for (int i = 0; i < allElements.size(); i++) {
            elementsAreEqual = itemsStartPage.containsItem(i, allElements.get(i));
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");
        }
    }

    @BeforeGroups(groups = {"Test3"})
    public void setUpForTests100Items() {
        itemsStartPage = DriverSettings.setUpDriver();
        int numberOfElements = 100;
        String textOfElement = "Item";
        for (int i = 0; i < numberOfElements; i++) {
            itemsStartPage.addItem(textOfElement + i);
        }
    }

    @Test(groups = {"Test3"})
    public void scenario4() {

        boolean elementsAreEqual;
        itemsStartPage.removeItem(30);
        for (int i = 0; i < 50; i++) {
            itemsStartPage.clickTogle(i);
        }
        itemsStartPage.clickActiveButton();
        String textOfElement = "Item";
        for (int i = 0; i < 20; i++) {
            int expected = i + 80;
            elementsAreEqual = itemsStartPage.containsItem(i, textOfElement + expected);
            Assert.assertTrue(elementsAreEqual, "Element " + i + " is not equal to expected");
        }
        Assert.assertEquals(itemsStartPage.getElementsLeft(), "20 items left");
    }

    @BeforeGroups(groups = {"Test4"})
    public void setUpForTests3DifItems() {
        itemsStartPage = DriverSettings.setUpDriver();
        String item1 = "@#$%^";
        itemsStartPage.addItem(item1);
        String item2 = "";
        while (item2.length() < 30) {
            item2 += "a ";
        }
        itemsStartPage.addItem(item2);
        String item3 = "";
        while (item3.length() < 70) {
            item3 += "a @$%";
        }
        itemsStartPage.addItem(item3);
    }

    @Test(groups = {"Test4"})
    public void scenario5() {
        String item1Text;
        String item2Text;
        List<SelenideElement> allElements = itemsStartPage.getAllItemsList();
        List<SelenideElement> editElements = itemsStartPage.getAllEditElementList();
        item1Text = allElements.get(0).getText();
        item2Text = allElements.get(1).getText();
        int textSize = item1Text.length();
        allElements.get(0).doubleClick();
        for (int i = 0; i < textSize; i++) {
            editElements.get(0).sendKeys(Keys.BACK_SPACE);
        }
        allElements = itemsStartPage.getAllItemsList();
        for (SelenideElement element : allElements) {
            Assert.assertNotEquals(element.equals(item1Text), "The list still containsItem item1");
        }
        for (int i = 0; i < 2; i++) {
            itemsStartPage.addItem(item2Text);
        }
        allElements = itemsStartPage.getAllItemsList();
        int count = 0;
        for (int i = 0; i < allElements.size(); i++) {

            if (allElements.get(i).getText().equals(item2Text)) {
                count++;
            }
        }
        Assert.assertEquals(count, 3);

        int item2ExpectedLength = 300;
        int item2ExpectedAddedLength = item2ExpectedLength - allElements.get(1).getText().length();
        String expectedAddingToItem2 = "";

        while (expectedAddingToItem2.length() < item2ExpectedAddedLength) {
            expectedAddingToItem2 += "a @$%";
        }
        allElements.get(1).doubleClick();
        editElements.get(1).sendKeys(expectedAddingToItem2);
        editElements.get(1).sendKeys(Keys.ENTER);

        allElements = itemsStartPage.getAllItemsList();
        Assert.assertEquals(allElements.get(1).getText().length(), item2ExpectedLength);

    }

    @AfterMethod()
    public void cleanUp() {
        itemsStartPage.clickAllButton();
        itemsStartPage.clickTogleAll();
        itemsStartPage.clickClearCompletedButton();
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().quit();
    }
}



