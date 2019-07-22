package pages;

import io.qameta.allure.Step;

public class AllItemsPage extends AbstractItemPage {


    @Step("add items from {0}0 to {0}{1}")
    public AllItemsPage addItems(String body, int quantity) {
        addItemsByQuantity(body, quantity);
        return this;
    }

    @Step("add item {0}")
    public AllItemsPage addItemAllItemsPage(String itemText) {
        addItem(itemText);
        return this;
    }

    @Step("add item that consists from {0} that concatenated to have size {1}")
    public AllItemsPage addItemAllItemPageComposite(String part, int maxSize) {
        addItemAllItemPageFromParts(part, maxSize);
        return this;
    }

    @Step("mark first {0} items as completed")
    public AllItemsPage markAsCompleted(int quantity) {
        markItemAsCompleted(quantity);
        return this;
    }

    @Step("displayed items list contains {0}0 to {0}{1} elements")
    public AllItemsPage elementsAreEqual(String body, int quantity) {
        elementsAreExpected(body, quantity);
        return this;
    }

    @Step("click toggle all on the All Items Page")
    public AllItemsPage clickToggleAllItemsPage() {
        clickToggleAll();
        return this;
    }

    @Step("the label Items Left is equal to {0}")
    public AllItemsPage getItemsLeftIsEqual(int expected) {
        getItemsLeftLabelIsExpected(expected);
        return this;
    }

    @Step("Clear Completed Button is displayed")
    public AllItemsPage checkClearCompletedButtonIsDisplayed() {
        clearCompletedButtonIsDisplayed();
        return this;
    }

    @Step("delete first {0} items")
    public AllItemsPage deleteItems(int quantity) {
        deleteItemsByQuantity(quantity);
        return this;
    }

    @Step("delete Item number {0} by it editind")
    public AllItemsPage deleteItemByEditing(int itemNumber) {
        deleteItemByEdit(itemNumber);
        return this;
    }

    @Step("item with the text {0} is displayed in the list {1} times")
    public AllItemsPage itemIsDisplayedInTheList(String item, int times) {
        ItemIsDisplayedCount(item, times);
        return this;
    }

    @Step("edit item by increasing item {0} text size to {1}")
    public AllItemsPage editItem(int ItemNumber, int ExpectedLength) {
        editItemToExpectedLength(ItemNumber, ExpectedLength);
        return this;
    }

    @Step("item {0} text length is {1}")
    public AllItemsPage itemTextLengthIsExpected(int itemNumber, int expectedLength) {
        itemTextLengthAsExpected(itemNumber, expectedLength);
        return this;
    }

    @Step("click the Active filter button")
    public ActiveItemsPage clickActiveButton() {
        activeButton.click();
        return new ActiveItemsPage();
    }

    @Step("click the Completed filter button")
    public CompletedItemsPage clickCompletedButton() {
        completedButton.click();
        return new CompletedItemsPage();
    }
}
