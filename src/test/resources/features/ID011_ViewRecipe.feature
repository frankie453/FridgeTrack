Feature: Get existing recipe with details
  As a user, I want to view details of specific recipes, so I can buy things for the recipe.

  Background:
    Given the following recipes exist in the fridge:
      | name            | description                                                 | ingredients                                  | instruction                   |
      | Caesar Salad    | Salad with lettuce, croutons, Parmesan and Caesar dressing  | Lettuce, Croutons, Parmesan, Caesar dressing | Chop lettuce, Mix together    |
      | Hamburger       | Beef patty in a bun with toppings like lettuce and cheese   | Beef patty, Bread, Lettuce, Cheese           | Gril patty, Assemble together |


  Scenario Outline: User get existing recipe
    When the user enters the recipe name "<name>"
    Then the recipe with name "<name>" should be shown with the "<description>", "<ingredients>" and "<instruction>"

    Examples:
      | name            | description                                                 | ingredients                                  | instruction                   |
      | Caesar Salad    | Salad with lettuce, croutons, Parmesan and Caesar dressing  | Lettuce, Croutons, Parmesan, Caesar dressing | Chop lettuce, Mix together    |
      | Hamburger       | Beef patty in a bun with toppings like lettuce and cheese   | Beef patty, Bread, Lettuce, Cheese           | Gril patty, Assemble together |