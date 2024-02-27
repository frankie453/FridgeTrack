package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.It;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class RetrieveItemSteps {

    Fridge defaultFridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");
    Item item = null;
    List<Item> itemList = new ArrayList<>();

    @Test
    void contextLoads() {
    }

    @Given("^I have a fridge with the following food items:$")
    public void I_have_a_fridge_with_the_following_food_items(DataTable dataTable) throws Throwable{
        defaultFridge = new Fridge("default");
        assertNotNull(defaultFridge);

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (var row : rows) {
            String name = row.get("name");
            Date dateOfPurchase = Date.valueOf(row.get("dateOfPurchase"));
            Item.Unit unit = Item.Unit.valueOf(row.get("unit"));
            int amount = Integer.parseInt(row.get("amount"));
            Item item = new Item(name, unit, null, defaultCategory, defaultFridge);
            itemList.add(item);
        }

    }

    @When("^I request the record of the food item with name \"([^\"]*)\"$")
    public void I_request_the_record_of_the_food_item_with_name(String nameInput) throws Throwable{
        for(Item i: defaultFridge.getItems()) {
            if(i.getName().equals(nameInput)) {
               item = i;
            }
        }
    }

    @Then("^the food item \"([^\"]*)\" should be seen$")
    public void I_should_see_the_following_details(String nameInput) throws Throwable{
        Item itemNeeded = null;
        for(Item i : itemList) {
            if(i.getName().equals(nameInput)){
                itemNeeded = i;
            }
        }
        Assertions.assertEquals(item.getName(),itemNeeded.getName());
    }

}

