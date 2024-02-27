Feature: Bulk Delete Records of Food in the Fridge
  As a user, I want to delete the records of multiple foods in the fridge, so I can ensure foods removed from the fridge are no longer in the record.

  Background:
    Given the backend API is running.

  Scenario Outline: User deletes food items from the fridge
    Given the following food items are stored in the fridge's inventory
      | name        |
      | Apples      |
      | Milk        |
      | Chicken     |
    When I delete the food item <name>
    Then the food item <Name> should be removed from the fridge's inventory

    Examples:
      | name    |
      | Apples  |
      | Milk    |

  Scenario Outline: Attempt to delete a food item that does not exist in the fridge's inventory (Error Flow)
    Given the following food items are stored in the fridge's inventory
      | name    |
      | Apples  |
      | Milk    |
      | Chicken |
    When I delete the food item <name>
    Then I should receive a notification that <name> does not exist in the fridge's inventory
    And no items should be removed from the inventory

    Examples:
      | name    |
      | Potato  |
      | Tomato  |
