package pages;


import io.qameta.allure.Step;
import org.testng.Assert;


public class CompletedItemsPage extends AbstractItemPage {

    @Step ("click Active Filter Button")
    public ActiveItemsPage clickActiveButton() {
        activeButton.click();
        return new ActiveItemsPage();
    }

    @Step("displayed completed items is list of items that contains {0}0 to {0}{1} elements")
    public CompletedItemsPage itemsCompletedAreEqual(String body, int quantity) {
        elementsCompletedAreExpected(body,quantity);
        return this;
    }

    @Step("completed items list size is {0}")
    public CompletedItemsPage itemListSize(int expectedListSize){
        Assert.assertEquals(getAllCompletedItemList().size(),expectedListSize);
        return this;
    }
    @Step("mark item {0} is uncompleted")
    public CompletedItemsPage markAsUncompletedByOrder(int orderNumber){
        getAllCompletedItemList().get(orderNumber).clickTheToggle();
        return this;
    }


}
