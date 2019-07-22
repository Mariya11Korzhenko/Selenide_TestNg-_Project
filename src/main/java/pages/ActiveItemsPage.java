package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.util.List;

public class ActiveItemsPage extends AbstractItemPage {

    @Step("displayed items text is list of items that contains {0}0 to {0}{1} elements")
    public ActiveItemsPage itemsAreEqual(String body, int quantity) {
        elementsAreExpected(body, quantity);
        return this;
    }

    @Step("displayed items text is {0} list of items")
    public ActiveItemsPage itemsAreEqual(List<String> expectedList) {
        elementsAreExpected(expectedList);
        return this;
    }

    @Step("click toggle all on the Active Page")
    public ActiveItemsPage clickToggleAllActiveItemPage() {
        clickToggleAll();
        return this;
    }
    @Step("displayed items text is list of items that contains {0}{1} to {0}{2} elements")
    public ActiveItemsPage itemsAreEqual(String body, int numberFrom, int NumberTo) {
        elementsAreExpected(body, numberFrom, NumberTo);
        return this;
    }

    @Step("the label Items Left is equal to {0}")
    public ActiveItemsPage getItemsLeftIsEqual(int expected) {
        getItemsLeftLabelIsExpected(expected);
        return this;
    }

    @Step("clear all items from list using toggle all button")
    public ActiveItemsPage clearAllItems() {
        clickToggleAll();
        clickClearCompletedButton();
        return this;
    }
}
