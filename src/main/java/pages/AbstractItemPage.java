package pages;

import com.codeborne.selenide.SelenideElement;
import elements.ItemElement;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class AbstractItemPage {

    private SelenideElement newToDo = $(".new-todo");
    SelenideElement activeButton = $(By.xpath(".//a[text()='Active']"));
    SelenideElement allButton = $(By.xpath(".//a[text()='All']"));
    SelenideElement completedButton = $(By.xpath(".//a[text()='Completed']"));
    private SelenideElement clearCompletedButton = $(By.xpath(".//button[@class='clear-completed']"));
    private SelenideElement elementsLeftLabel = $(By.xpath(".//span[@class='todo-count']"));
    private SelenideElement toggleAll = $(By.xpath(" .//label[@for='toggle-all']"));


    //clickButtonMethods
    @Step
    @Description ("")
    void clickToggleAll() {
        toggleAll.click();
    }

    @Step
    void clickClearCompletedButton() {
        clearCompletedButton.click();
    }

    @Step
    void markItemAsCompleted(int quantity) {

        for (int i = 0; i < quantity; i++) {
            getAllItemList().get(i).clickTheToggle();
        }
    }


    //addItemMethods
    @Step
    void addItem(String itemName) {
        newToDo.setValue(itemName).pressEnter();
    }

    @Step
    void addItemsByQuantity(String body, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addItem(body + i);
        }
    }

    @Step
    void addItemAllItemPageFromParts(String part, int maxSize) {
        String currentItem = "";
        while (currentItem.length() < maxSize) {
            currentItem += part;
        }
        addItem(currentItem);

    }

    //ElementIsDisplayedMethods

    void getItemsLeftLabelIsExpected(int expected) {
        int value = Integer.parseInt(elementsLeftLabel.getText().split(" ")[0]);
        Assert.assertEquals(value, expected);
    }

    @Step
    void clearCompletedButtonIsDisplayed() {
        boolean isDisplayed = false;
        if (clearCompletedButton.isDisplayed()) {
            isDisplayed = true;
        }
        Assert.assertTrue(isDisplayed);
    }

    void elementsAreExpected(String body, int quantity) {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
    }

    void elementsAreExpected(String body, int numberFrom, int NumberTo) {
        List<String> expectedList = new ArrayList<>();
        for (int i = numberFrom; i < NumberTo; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
    }

    void elementsAreExpected(List<String> expectedList) {
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }

    }

    void ItemIsDisplayedCount(String item, int times) {
        int count = 0;
        for (int i = 0; i < getAllItemList().size(); i++) {
            if (getAllItemList().get(i).getItemText().equals(item)) {
                count++;
            }
        }
        Assert.assertEquals(times, count);
    }

    void elementsCompletedAreExpected(String body, int quantity) {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllCompletedItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
    }

    void editItemToExpectedLength(int ItemNumber, int ExpectedLength) {
        int itemActualLength = getAllItemList().get(ItemNumber).getItemText().length();
        String expectedAddingToItem = "";

        while (expectedAddingToItem.length() < ExpectedLength - itemActualLength) {
            expectedAddingToItem += "a @$%";
        }
        getAllItemList().get(ItemNumber).editItemText(expectedAddingToItem);
    }

    void itemTextLengthAsExpected(int itemNumber, int expectedLength) {
        Assert.assertEquals(getAllItemList().get(itemNumber).getItenTextLength(), expectedLength);

    }

    //deleteMethods
    void deleteItemsByQuantity(int quantity) {
        for (int i = 0; i < quantity; i++) {
            getAllItemList().get(0).removeItem();
        }
    }

    void deleteItemByEdit(int itemNumber) {
        getAllItemList().get(itemNumber).deleteItemByEdit();
    }


    //staticMethodsWithLists
    static List<ItemElement> getAllItemList() {
        List<ItemElement> itemElementList = new ArrayList<>();
        List<SelenideElement> selenideElementList = $$(By.xpath(".//div[@class='view']/parent::li"));

        for (int i = 0; i < selenideElementList.size(); i++) {
            itemElementList.add(new ItemElement(selenideElementList.get(i)));
        }
        return itemElementList;
    }

    static List<ItemElement> getAllCompletedItemList() {
        List<ItemElement> itemElementList = new ArrayList<>();
        List<SelenideElement> selenideElementList = $$(By.xpath(".//li[@class='completed']/div"));

        for (int i = 0; i < selenideElementList.size(); i++) {
            itemElementList.add(new ItemElement(selenideElementList.get(i)));
        }
        return itemElementList;
    }
}


