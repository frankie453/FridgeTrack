package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.ItemCategory;
import ECSE428.Group6.FridgeTrack.model.Recipe;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ViewRecipeSteps {
    Fridge fridge = new Fridge("Default");
    ItemCategory defaultCategory = new ItemCategory("default");
    // Recipe recipeToView = null;

    @Test
    void contextLoads() {
    }

    @Given("^the following recipes exist in the fridge:$")
    public void the_following_recipes_exist_in_the_fridge(DataTable dataTable) throws Throwable {
        List<Map<String,String>> recipeList = dataTable.asMaps(String.class,String.class);
        for(Map<String,String> row: recipeList) {
            String name = row.get("name");
            String description = row.get("description");
            String ingredients = row.get("ingredients");
            String instruction = row.get("instruction");
            Recipe recipe = new Recipe(name,fridge,description,instruction);

            for(String itemName : ingredients.split(",")) {
                recipe.addItem(new Item(itemName, Item.Unit.GRAM, recipe, defaultCategory, fridge));
            }
        }
    }

    @When("^the user enters the recipe name \"([^\"]*)\"$")
    public void the_user_enters_the_recipe_name(String name) throws Throwable {
        Recipe recipeToView = null;
        for(Recipe r: fridge.getRecipes()) {
            if(r.getName().equals(name)) {
                recipeToView = r;
            }
        }
        if (recipeToView == null) {
            throw new Exception("Recipe not found:" + name);
        }
    }

    @Then("^the recipe with name \"([^\"]*)\" should be shown with the \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_recipe_with_name_should_be_shown_with_the_and(String name, String description, String ingredients, String instruction) throws Throwable {
        Recipe recipeToView = null;
        for(Recipe r: fridge.getRecipes()) {
            if(r.getName().equals(name)) {
                recipeToView = r;
            }
        }
        assertEquals(name, recipeToView.getName());
        assertEquals(description, recipeToView.getDescription());
        assertEquals(instruction, recipeToView.getInstruction());

        for (String itemName : ingredients.split(",")) {
            boolean found = false;
            for (Item i : recipeToView.getItems()) {
                if (i.getName().equals(itemName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Ingredient not found:" + itemName);
            }
        }

    }

}
