package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.Recipe;
import ECSE428.Group6.FridgeTrack.model.Record;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class SortFoodByExpirySteps {

    Fridge fridge = new Fridge("default");
    ItemCategory defaultCategory = new ItemCategory("default");

    @Test
    void contextLoads() {
    }

    @Given("^the following items exist in the fridge:$")
    public void the_following_items_exist_in_the_fridge(DataTable dataTable) throws Throwable {
        List<Map<String,String>> itemList = dataTable.asMaps(String.class,String.class);
        for(Map<String,String> row: itemList) {
            String name = row.get("name");
            int id = Integer.parseInt(row.get("record_id"));
            Date enterDate = Date.valueOf(LocalDate.parse(row.get("enter_date")));
            int quantity = Integer.parseInt(row.get("quantity"));
            Date expiryDate = Date.valueOf(LocalDate.parse(row.get("expiry_date")));

            Item item = new Item(name, Item.Unit.GRAM, null, defaultCategory, fridge);
            fridge.addItem(item);
            item.addRecord(id, enterDate, quantity, expiryDate);
        }
    }

    @Given("^the following recipes exist in the fridge as well:$")
    public void the_following_recipes_exist_in_the_fridge_as_well(DataTable dataTable) throws Throwable {
        List<Map<String,String>> recipeList = dataTable.asMaps(String.class,String.class);
        for(Map<String,String> row: recipeList) {
            String name = row.get("name");
            String ingredients = row.get("ingredients");
            Recipe recipe = new Recipe(name,fridge,"", "");

            for(String itemName : ingredients.split(",")) {
                boolean found = false;
                for (Item i : fridge.getItems()) {
                    if (i.getName().equals(itemName)) {
                        found = true;
                        recipe.addItem(i);
                        break;
                    }
                }
                if (!found) {
                    Item i = new Item(itemName, Item.Unit.GRAM, recipe, defaultCategory, fridge);
                    fridge.addItem(i);
                    recipe.addItem(i);
                }
            }
        }
    }
    

    @When("^the user sorts the inventory by expiry date$")
    public void the_user_sorts_the_inventory_by_expiry_date() throws Throwable {
        // TODO implement in backend
        // throw new io.cucumber.java.PendingException();
    }

    @Then("^the inventory should output the item \"([^\"]*)\" with the closest expiry date \"([^\"]*)\"$")
    public void the_inventory_should_output_the_item_with_the_closest_expiry_date(String itemName, String expiryDate) throws Throwable {
        Item item = null;
        for (Item i : fridge.getItems()) {
            if (i.getName().equals(itemName)) {
                item = i;
                break;
            }
        }
        assertNotNull(item);
        assertEquals(expiryDate, item.getRecord(0).getExpiryDate().toString());
    }

    @Then("^a recipe \"([^\"]*)\" including the item \"([^\"]*)\" closest to expiry should be shown$")
    public void a_recipe_including_the_item_closest_to_expiry_should_be_shown(String recipeName, String itemName) throws Throwable {
        Recipe recipe = null;
        for (Recipe r : fridge.getRecipes()) {
            if (r.getName().equals(recipeName)) {
                recipe = r;
                break;
            }
        }
        assertNotNull(recipe);
        boolean found = false;
        for (Item i : recipe.getItems()) {
            if (i.getName().equals(itemName)) {
                found = true;
                break;
            }
        }
        assertEquals(true, found);
    }

}
