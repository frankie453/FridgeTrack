package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.Recipe;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class UpdateRecipeSteps {
    Fridge fridge = new Fridge("Default");
    ItemCategory defaultCategory = new ItemCategory("default");

    @Test
    void contextLoads() {
    }
    @Given("^the following recipes exist:$")
    public void the_following_recipes_exist(DataTable dataTable) throws Throwable {
        List<Map<String,String>> recipeList  = dataTable.asMaps(String.class,String.class);
        for(Map<String,String> row: recipeList) {
            String name = row.get("name");
            String description = row.get("description");
            String instruction = row.get("instruction");
            Recipe recipe = new Recipe(name,fridge,description,instruction);
            String itemNames = row.get("ingredients");
            for(String itemName : itemNames.split(",")) {
                Item i = new Item(itemName, Item.Unit.GRAM, recipe, defaultCategory, fridge);
            }
        }

    }
    @When("^the user updates the recipe \"([^\"]*)\" with the following details: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_user_updates_the_recipe_with_the_following_datails(String name,String description,String ingredients,String instruction) throws Throwable {
        Recipe recipeToUpdate = null;
        for(Recipe r: fridge.getRecipes()) {
            if(r.getName().equals(name)) {
                recipeToUpdate = r;
                break;
            }
        }
        if(recipeToUpdate == null) {
            throw new Exception("Recipe not exist:" + name);
        }

        boolean foundInList = false;
        for(String item: ingredients.split(",")) {
            for(Item i:recipeToUpdate.getItems()) {
                if(i.getName()==item) {
                    foundInList = true;
                    break;
                }
            }
            if(!foundInList) {
                recipeToUpdate.addItem(new Item(item,Item.Unit.GRAM,recipeToUpdate,defaultCategory,fridge));
            }
        }
        // delete other elements not in the update
        boolean Ex = false;
        for(Item i:recipeToUpdate.getItems()) {
            for (String item:ingredients.split(",")) {
                if (i.getName() == item) {
                    Ex = true;
                }
            }
            if(Ex = false) {
                recipeToUpdate.removeItem(i);
            }
        }
        recipeToUpdate.setDescription(description);
        recipeToUpdate.setInstruction(instruction);
    }

    @Then("^the recipe \"([^\"]*)\" should be updated with the following details: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_recipe_should_be_updated_with_the_following_details(String name,String description,String ingredients,String instruction) {
        assertNotNull(fridge.getRecipes());
        Recipe recipeNeeded = null;
        List<String> names = new ArrayList<>();
        for(Recipe re : fridge.getRecipes()) {
            if(re.getName() == name) {
                recipeNeeded = re;
            }
        }
        for(Item i : recipeNeeded.getItems()) {
            names.add(i.getName());
        }
        assertNotNull(recipeNeeded);
        assertEquals(recipeNeeded.getInstruction(),instruction);
        assertEquals(recipeNeeded.getDescription(),description);
        for(String item:ingredients.split(",")) {
            assertTrue(names.contains(item));
        }
    }
}
