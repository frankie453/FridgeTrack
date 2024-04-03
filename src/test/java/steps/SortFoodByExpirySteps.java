package steps;

import static org.junit.Assert.assertNotNull;

import ECSE428.Group6.FridgeTrack.model.Fridge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortFoodByExpirySteps {
    
    private Fridge fridge;

    @Given("I have a fridge")
    public void i_have_a_fridge() throws Throwable {
        fridge = new Fridge("default");
        assertNotNull(fridge);
    }

    @And("I have a grocery inventory with the following items and expiry dates:")
    public void i_have_a_grocery_inventory_with_the_following_items_and_expiry_dates(io.cucumber.datatable.DataTable dataTable) {
        // TODO

        throw new io.cucumber.java.PendingException();
    }

    @When("I sort the food by expiry date")
    public void i_sort_the_food_by_expiry_date() {
        // TODO
        throw new io.cucumber.java.PendingException();
    }

    @Then("the inventory should be sorted in the following order:")
    public void the_inventory_should_be_sorted_in_the_following_order(io.cucumber.datatable.DataTable dataTable) {
        // TODO
        throw new io.cucumber.java.PendingException();
    }

    @And("a recipe including the ingredient closest to expiry should be generated")
    public void a_recipe_including_the_ingredient_closest_to_expiry_should_be_generated() {
        // TODO
        throw new io.cucumber.java.PendingException();
    }

}
