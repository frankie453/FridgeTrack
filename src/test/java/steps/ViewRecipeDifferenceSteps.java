package steps;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import ECSE428.Group6.FridgeTrack.model.Item;
import ECSE428.Group6.FridgeTrack.model.Recipe;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewRecipeDifferenceSteps {
    Fridge defaultFridge = new Fridge("default");
    List<Recipe> availableRecipes = new ArrayList<>();
    Recipe currentRecipe;
    List<Item> missingIngredients = new ArrayList<>();

    @Given("^my fridge contains the following food items:$")
    public void my_fridge_contains_the_following_food_items(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> column : rows) {
            String name = column.get("name");
            int amount = Integer.parseInt(column.get("amount"));
            Item.Unit unit = Item.Unit.valueOf(column.get("unit").toUpperCase());
            Item item = new Item(name, amount, unit); // Adjust based on your actual constructor
            defaultFridge.addItem(item);
        }
    }

    @When("^I choose to prepare the recipe \"([^\"]*)\"$")
    public void i_choose_to_prepare_the_recipe(String recipeName) {
        // Attempt to find the recipe. If not found, currentRecipe remains null.
        currentRecipe = availableRecipes.stream()
                .filter(recipe -> recipe.getName().equals(recipeName))
                .findFirst()
                .orElse(null);

        if (currentRecipe != null) {
            // Assuming compareIngredients is a method that checks for missing ingredients
            //missingIngredients = currentRecipe.compareIngredients(defaultFridge.getItems());
            //to do:
        }
    }


    @Then("^I should see the missing ingredients for \"([^\"]*)\"$")
    public void i_should_see_the_missing_ingredients_for(String recipeName) {
        // This step would assert that the missing ingredients match the expected missing ingredients for the scenario.
        // For simplicity, let's just print them out for now. You would replace this with actual assertion logic.
        System.out.println("Missing ingredients for " + recipeName + ":");
        for (Item missingIngredient : missingIngredients) {
            System.out.println("- " + missingIngredient.getName());
        }
        // Here, add assertions to validate the missing ingredients against the expected missing ingredients in the scenario.
    }
}
