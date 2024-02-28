package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.Recipe;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class SearchItemSteps {
    Fridge defaultFridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");
    Item item = null;
    List<Item> itemList = new ArrayList<>();
    @Test
    void contextLoads() {
    }

    @Given("^I have a fridge with the following items:$")
    public void item_in_stock (DataTable dataTable) throws Throwable{
        defaultFridge = new Fridge("default");
        defaultCategory = new ItemCategory("default");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String name = row.get("name");
            Date dateOfPurchase = Date.valueOf(row.get("dateOfPurchase"));
            Item.Unit unit = Item.Unit.valueOf(row.get("unit"));
            int amount = Integer.parseInt(row.get("amount"));
            Item item = new Item(name, unit, null, defaultCategory, defaultFridge);
            defaultFridge.addItem(item); // Assuming addItem is the method to add items to the fridge
        }
    }
    @When("^I enter the \"([^\"]*)\" for the item to search$")
    public void search_item (String nameInput) throws Throwable{
        item = defaultFridge.getItems().stream()
                .filter(i -> i.getName().equals(nameInput))
                .findFirst()
                .orElse(null);
    }
    @Then("^the item with name \"([^\"]*)\" should be returned$")
    public void return_item (String nameInput) throws Throwable{
        Assertions.assertNotNull(item, "The food item was not found");
        Assertions.assertEquals(nameInput, item.getName(), "The found item does not match the expected item");
    }
}
