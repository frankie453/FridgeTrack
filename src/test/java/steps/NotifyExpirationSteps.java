package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.Record;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.WarningNotification;
import java.sql.Date;
import java.time.LocalDate;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

public class NotifyExpirationSteps {

    Fridge defaultFridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");
    Date expiryDate1 = Date.valueOf(LocalDate.parse("2024-04-02"));
    Date purchaseDate = Date.valueOf(LocalDate.parse("2024-01-02"));
    int idCounter = 0;
    Item i ;
    Record r;
    @Test
    void contextLoads() {
    }

    @Given("^I have a fridge with items$")
    public void i_have_a_fridge() throws Throwable {
        assertNotNull(defaultFridge);
    }

    @Given("^I have an expiring food item in my fridge:$")
    public void i_have_an_expiring_food_item_in_my_fridge(DataTable dataTable) throws Throwable {
        List<Map<String, String>> itemsList = dataTable.asMaps(String.class, String.class);
        for (Map<String,String> row:itemsList) {
            String name = row.get("name");
            String expiry = row.get("expiry");
            LocalDate localDate = LocalDate.parse(expiry);
            Date expiryDate = Date.valueOf(localDate);
            i = new Item(name, Item.Unit.GRAM,null,defaultCategory,defaultFridge);
            r = new Record(0,purchaseDate,100,expiryDate,i);
            assertNotNull(r);
            Assertions.assertEquals(r.getExpiryDate(),expiryDate1);
        }
    }
    @When("^I search the item apple$")
    public void i_search_the_item_apple() throws Throwable {
        List<Item> itemList = defaultFridge.getItems();
        boolean hasItem = false;
        for(Item i:itemList) {
            if(i.getName().equals("apple")) {
                hasItem = true;
            }
        }
        assertTrue(hasItem);
    }
    @Then("^I should see a notification$")
    public void i_should_see_a_notification() throws Throwable {
        WarningNotification w = new WarningNotification("123", WarningNotification.Warning.Medium,r);
        Assertions.assertEquals(w.getRecord().getExpiryDate(),expiryDate1);
    }
}
