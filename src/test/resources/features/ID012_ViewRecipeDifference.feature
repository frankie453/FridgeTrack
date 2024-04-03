Feature: View Recipe Difference
  As a user, I want to check the ingredients needed for a recipe based on what's currently available in my fridge, so I can understand what I'm missing for the recipe.

  Background: User prepares to check ingredients for a recipe
    Given my fridge contains the following food items:
      | name          | amount | unit       |
      | Eggs          | 6      | PIECE      |
      | Milk          | 500    | MILLILITER |
      | Flour         | 1000   | GRAM       |

  Scenario Outline: Checking missing ingredients for a recipe
    When I choose to prepare the recipe "<recipe_name>"
    Then I should see the missing ingredients for "<recipe_name>"

    Examples:
      | recipe_name     | missing_ingredients         |
      | Pancakes        | Butter, Maple Syrup         |
      | Vegetable Stir Fry | Bell Peppers, Soy Sauce |
