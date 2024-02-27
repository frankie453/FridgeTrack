Feature: Create Records of recipe in the Fridge with Details
As a user, I want to create custom recipes, so I can be notified of custom meals I can make.

  Background:
    Given the backend API is running.

  Scenario Outline: User adds new recipe with details
    Given I want to store a new recipe
    And the following food items is in fridge
    |item_name |item_id|item_type|expiry_date|
    |Toast    |T000001|Deli    |2024-2-9   |
    |Penut butter     |P000001|Paste     |2025-3-1   |
    |Jam   |P000002|Paste    |2024-6-30  |
    When I enter the <name>, <List of food used>, <List of Amount>, and <List of unit> for the recipe.
      | recipe name        | List of food used | List of Amount| unit  | 
      | PB&J      |Toast,Penut butter, Jame   | 2,10,10     | PIECE,GRAM,GRAM |
    And I save the recipe
    Then the recipe with name <name> should be added to the fridge's inventory with its  <List of food used>, <List of Amount>, and <List of unit> .
      | recipe name        | List of food used | List of Amount| unit  | 
      | PB&J      |Toast,Penut butter, Jame   | 2,10,10     | PIECE,GRAM,GRAM |


