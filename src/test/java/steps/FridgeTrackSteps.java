package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.Record;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class FridgeTrackSteps {

    Fridge defaultFridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");
    int recordIDCounter = 0;
    Date sysDate = Date.valueOf(LocalDate.parse("2024-01-01"));

    @Test
    void contextLoads() {
    }

    @Given("the backend API is running\\.$")
    public void the_backend_API_is_running() throws Throwable {
        // TODO: Add this when the API logic is up
        //throw new PendingException();
    }

    @Given("^I have a fridge$")
    public void i_have_a_fridge() throws Throwable {
        // Start with empty test fridge
        defaultFridge = new Fridge("default");
        assertNotNull(defaultFridge);
    }

    @When("^I enter the name \"([^\"]*)\", dateOfPurchase \"([^\"]*)\", amount (\\d+), and unit \"([^\"]*)\" for the food item\\.$")
    public void i_enter_the_name_dateOfPurchase_amount_and_unit_for_the_food_item(String name, String date, int amount, String unit) throws Throwable {
        Item i = new Item(name, Item.Unit.valueOf(unit), null, defaultCategory, defaultFridge);
        LocalDate localDate = LocalDate.parse(date);
        Date expiry = Date.valueOf(localDate);
        Record r = new Record(recordIDCounter, sysDate, amount, expiry, i);
        i.addRecord(r);
        recordIDCounter++;
    }

    @Then("^the food item \"([^\"]*)\" with a date of purchase \"([^\"]*)\", amount (\\d+), and unit \"([^\"]*)\" should be added to the fridge's inventory$")
    public void the_food_item_with_a_date_of_purchase_amount_and_unit_should_be_added_to_the_fridge_s_inventory(String name, String date, int amount, String unit) throws Throwable {
        Item.Unit u = Item.Unit.valueOf(unit);

        LocalDate localDate = LocalDate.parse(date);
        Date expiry = Date.valueOf(localDate);

        List<Item> itemList = defaultFridge.getItems();
        boolean found = false;
        for(Item item : itemList) {
            if (item.getName().equals(name)) {
                found = true;
                Record r = item.getRecord(recordIDCounter-1);
                Assertions.assertEquals(r.getExpiryDate(), expiry);
                Assertions.assertEquals(r.getQuantity(), amount);
                Assertions.assertEquals(item.getUnit(), u);
            }
        }
        Assertions.assertTrue(found);
    }
}
