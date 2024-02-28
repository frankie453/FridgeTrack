Feature: Generate Recipe Based on Existing Food
  As a user, I want to generate a recipe based on the existing food in the fridge, so I don't need to figure it out by myself.

  Background:
    Given the backend API is running.

  Scenario: User generates a recipe from the fridge's inventory based on available ingredients
    Given the following food items are recorded in the fridge's inventory
      | name        |
      | Apples      | 
      | Milk        |
      | Chicken     |
      | Bread       |
    And there exists a recipe that uses the following ingredients
      | Ingredient  |
      | Apples      |
      | Bread       |
      | Milk        |
    When I request to generate a recipe using available food items
    Then the system should suggest a recipe that includes
      | Ingredient  |
      | Apples      |
      | Bread       |
      | Milk        |
    And the suggested recipe should not include ingredients not listed in the fridge's inventory
