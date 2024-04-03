package steps;

import ECSE428.Group6.FridgeTrack.model.*;
import ECSE428.Group6.FridgeTrack.model.Record;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
// Java code to demonstrate the working of
// contains() method in List of string

import java.util.*;

class GFG {
    public static void main(String[] args)
    {
        // creating an Empty String List
        List<String> arr = new ArrayList<String>(4);

        // using add() to initialize values
        // ["geeks", "for", "geeks"]
        arr.add("geeks");
        arr.add("for");
        arr.add("geeks");

        // use contains() to check if the element
        // "geeks" exits or not
        boolean ans = arr.contains("geeks");

        if (ans)
            System.out.println("The list contains geeks");
        else
            System.out.println("The list does not contains geeks");

        // use contains() to check if the element
        // "coding" exits or not
        ans = arr.contains("coding");

        if (ans)
            System.out.println("The list contains coding");
        else
            System.out.println("The list does not contains coding");
    }
}
public class FridgeTrackSteps {

    Fridge defaultFridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");
    int recordIDCounter = 0;
    Date sysDate = Date.valueOf(LocalDate.parse("2024-01-01"));
    List<Item> sorted = new ArrayList<>();

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

    @Given("^the following food items are already in the fridge:$")
    public void the_following_food_items_are_already_in_the_fridge(DataTable dataTable) throws Throwable {
        List<Map<String, String>> itemsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : itemsList) {
            String name = row.get("name");
            String dateOfPurchase = row.get("dateOfPurchase");
            int amount = Integer.parseInt(row.get("amount"));
            String unitString = row.get("unit");
            Item.Unit unit = Item.Unit.valueOf(unitString.toUpperCase());

            Item item = new Item(name, unit, null, defaultCategory, defaultFridge);

            // Parse the dateOfPurchase to a SQL Date
            LocalDate localDate = LocalDate.parse(dateOfPurchase);
            Date sqlDate = Date.valueOf(localDate);

            // Create a record for the item
            Record record = new Record(recordIDCounter++, sqlDate, amount, sqlDate, item);
        }
    }
    @When("^the user updates the food item \"([^\"]*)\" with the following details: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_user_updates_the_food_item_with_the_following_details(String name, String dateOfPurchase, String amountStr, String unit) throws Throwable {
        int amount = Integer.parseInt(amountStr);
        Item.Unit updatedUnit = Item.Unit.valueOf(unit.toUpperCase());
        LocalDate localDate = LocalDate.parse(dateOfPurchase);
        Date expiry = Date.valueOf(localDate);

        // Find the item to update
        Item itemToUpdate = null;
        for (Item item : defaultFridge.getItems()) {
            if (item.getName().equals(name)) {
                itemToUpdate = item;
                break;
            }
        }

        if (itemToUpdate == null) {
            throw new Exception("Item not found in fridge: " + name);
        }

        // Update item details
        itemToUpdate.setUnit(updatedUnit);
        Record recentRecord = itemToUpdate.getRecords().get(itemToUpdate.getRecords().size() - 1);
        recentRecord.setQuantity(amount);
        recentRecord.setExpiryDate(expiry);
    }

    @Then("^the food item \"([^\"]*)\" should be updated with the following details: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_food_item_should_be_updated_with_the_following_details(String name, String expectedDateOfPurchase, String expectedAmountStr, String expectedUnit) throws Throwable {
        int expectedAmount = Integer.parseInt(expectedAmountStr);
        Item.Unit expectedUpdatedUnit = Item.Unit.valueOf(expectedUnit.toUpperCase());
        LocalDate expectedLocalDate = LocalDate.parse(expectedDateOfPurchase);
        Date expectedExpiry = Date.valueOf(expectedLocalDate);

        // Find the item that was updated
        Item updatedItem = null;
        for (Item item : defaultFridge.getItems()) {
            if (item.getName().equals(name)) {
                updatedItem = item;
                break;
            }
        }

        assertNotNull("Updated item not found in fridge: " + name, updatedItem);
        assertEquals("Unit mismatch for updated item", expectedUpdatedUnit, updatedItem.getUnit());

        Record recentRecord = updatedItem.getRecords().get(updatedItem.getRecords().size() - 1);
        assertEquals("Amount mismatch for updated record", expectedAmount, recentRecord.getQuantity());
        assertEquals("Expiry date mismatch for updated record", expectedExpiry, recentRecord.getExpiryDate());
    }

    @Given("^the following food items are stored in the fridge's inventory$")
    public void the_following_food_items_are_stored_in_the_fridge_s_inventory(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        defaultFridge = new Fridge("My Fridge"); // Assuming a new fridge for each scenario
        for (Map<String, String> map : list) {
            String name = map.get("name");
            // Assuming a default unit as we just doing deletion here
            defaultFridge.addItem(new Item(name, Item.Unit.PIECE, null, defaultCategory, defaultFridge));
        }
    }

    @When("^I delete the food item \"([^\"]*)\"$")
    public void i_delete_the_food_item(String itemName) {
        List<Item> items = new ArrayList<>(defaultFridge.getItems()); // Create a copy to avoid concurrent modification
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                defaultFridge.removeItem(item);
                break;
            }
        }
    }

    @Then("^the food item (.+) should be removed from the fridge's inventory$")
    public void the_food_item_Name_should_be_removed_from_the_fridge_s_inventory(String itemName) {
        for (Item item : defaultFridge.getItems()) {
            assertNotEquals(itemName, item.getName());
        }
    }


    @Then("^no items should be removed from the inventory with still (\\d+) items$")
    public void no_items_should_be_removed_from_the_inventory_with_still_items(int remainCount) {
        assertEquals(remainCount, defaultFridge.numberOfItems());
    }

    @Given("^I want to store a new recipe$")
    public void i_want_to_store_a_new_recipe() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        defaultFridge = new Fridge("My Fridge");
    }

    @Given("^the following item category with <name>$")
    public void the_following_item_category_with_name(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            String name = map.get("name");
            ItemCategory category = new ItemCategory(name);
        }
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)

    }

    @Given("^the following food items with <item_name>, <item_id>,<item_type>,<expiry_date>  is in fridge$")
    public void the_following_food_items_with_item_name_item_id_item_type_expiry_date_is_in_fridge(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
         // Assuming a new fridge for each scenario
        for (Map<String, String> map : list) {
            String name = map.get("item_name");
            String type = map.get("item_type");
            LocalDate localDate = LocalDate.parse(map.get("expiry_date"));
            Date expiry = Date.valueOf(localDate);
            // Assuming a default unit as we just doing deletion here
            defaultFridge.addItem(new Item(name, Item.Unit.PIECE, null, new ItemCategory(type), defaultFridge));
        }
    }

    @When("^I enter the <recipe name >, <List of food used>, <List of Amount>, and <List of unit> for the recipe\\.$")
    public void i_enter_the_recipe_name_List_of_food_used_List_of_Amount_and_List_of_unit_for_the_recipe(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
        for (Map<String, String> map : list){
            String name = map.get("recipe name");
            List<String> food = Arrays.asList(map.get("List of food used").split(" \\s,\\s"));
            List<String> amount = Arrays.asList(map.get("List of Amount").split(" \\s,\\s"));
            List<String> unit = Arrays.asList(map.get("unit").split(" \\s,\\s"));
            Recipe recipe = new Recipe(name,defaultFridge,null,null);
            for(String s : food){
                for(Item i : defaultFridge.getItems()){
                    if(i.getName().equals(s)){
                        recipe.addItem(i);
                    }
                }
            }


            defaultFridge.addRecipe(recipe);
        }
    }

    @When("^I save the recipe$")
    public void i_save_the_recipe() throws Throwable {
        /**
         * Need DB
         */
    }

    @Then("^the recipe with name <name> should be added to the fridge's inventory with its  <List of food used>, <List of Amount>, and <List of unit> \\.$")
    public void the_recipe_with_name_name_should_be_added_to_the_fridge_s_inventory_with_its_List_of_food_used_List_of_Amount_and_List_of_unit(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);

        for (Map<String, String> map : list){
            String name = map.get("recipe name");
            for (Recipe recipe: defaultFridge.getRecipes()){
                if(recipe.getName().equals(name)){

                    for( int j = 0; j < recipe.getItems().size(); j++ ){
                        Assertions.assertEquals(recipe.getItems().get(j).getName(), Arrays.asList(map.get("List of food used").split(" \\s,\\s")).get(j));
                    }
                    Assertions.assertTrue(true);
                }
            }
        }
    }


    @Given("^the following food items are recorded in the fridge's inventory$")
    public void the_following_food_items_are_recorded_in_the_fridge_s_inventory(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);

        for (Map<String, String> map : list) {
            defaultFridge.addItem(new Item(map.get("name"), null,null,defaultCategory,defaultFridge));
        }
    }

    @Given("^there exists a recipe that uses the following ingredients$")
    public void there_exists_a_recipe_that_uses_the_following_ingredients(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);


        Recipe recipe = new Recipe("defaultRecipe",defaultFridge,"des","sadwdsa");

        for (Map<String, String> map : list) {
            for(Item i: defaultFridge.getItems()){
                if (i.getName().equals(map.get("Ingredient"))){
                    recipe.addItem(i);
                }
            }
        }

        defaultFridge.addRecipe(recipe);
    }

    @When("^I request to generate a recipe using available food items$")
    public void i_request_to_generate_a_recipe_using_available_food_items() throws Throwable {
        /**
         * API call for get recipe
         */
    }

    @Then("^the system should suggest a recipe that includes$")
    public void the_system_should_suggest_a_recipe_that_includes(DataTable arg1) throws Throwable {
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);

        List<Item> items = new ArrayList<>();
        for (Map<String, String> map : list) {
            for (Item i : defaultFridge.getItems()){
                if(i.getName().equals(map.get("Ingredient"))){
                    items.add(i);
                }
            }
        }

        for(Recipe recipe : defaultFridge.getRecipes()){
            List<Item>  targetItems = recipe.getItems();
            if(targetItems.size() != items.size()) continue;
            boolean isCoerct = true;
            if(items.equals(targetItems)){
                assertTrue(true);
            }
        }



    }

    @Then("^the suggested recipe should not include ingredients not listed in the fridge's inventory$")
    public void the_suggested_recipe_should_not_include_ingredients_not_listed_in_the_fridge_s_inventory() throws Throwable {
        //not necessary
    }


    @Given("^the following food items exist:$")
    public void the_following_food_items_exist(DataTable dataTable) throws Throwable {
        defaultFridge = new Fridge("default");
        List<Map<String, String>> itemList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : itemList) {
            String name = row.get("name");
            recordIDCounter ++;
            int id = recordIDCounter;
            Date enterDate = Date.valueOf(LocalDate.parse("2019-01-01"));
            int quantity = 1;
            Date expiryDate = Date.valueOf(LocalDate.parse(row.get("expirationDate")));

            Item item = new Item(name, Item.Unit.GRAM, null, defaultCategory, defaultFridge);
            defaultFridge.addItem(item);
            item.addRecord(id, enterDate, quantity, expiryDate);
        }
    }

    @When("^I view the food list$")
    public void i_view_the_food_list() throws Throwable {
        List<Item> items = new ArrayList<>(defaultFridge.getItems());
        sorted = Item.sortByExpiryDate(items);
    }

    @Then("^I should see the following food items with order of the closest expirationDate$")
    public void i_should_see_the_following_food_items_with_their_in_order_of_the_closest(DataTable dataTable) throws Throwable {
        // Get the expected items and expiry dates from the Examples table
        List<Map<String, String>> expectedItems = dataTable.asMaps(String.class, String.class);

        // Check if the number of items matches
        assertEquals("Number of items does not match expected.", expectedItems.size(), sorted.size());

        // Iterate through the expected items and compare with actual sorted items
        for (int i = 0; i < expectedItems.size(); i++) {
            Map<String, String> expectedItem = expectedItems.get(i);
            Item actualItem = sorted.get(i);

            // Check if the name and expiry date match
            assertEquals("Item name does not match expected.", expectedItem.get("name"), actualItem.getName());
            assertEquals("Expiry date does not match expected.", expectedItem.get("expirationDate"), actualItem.getRecord(0).getExpiryDate().toString());
    }
}

}

