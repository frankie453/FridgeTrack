Feature: Retrieve Records of Foods in the Fridge with Names
  As a user, I want to retrieve a list of available food in the fridge with specific food names, so I can manage the usage of the food.

  Background: User retrieves the information of a specific food item by name
    Given I have a fridge with the following food items:
      | name    | dateOfPurchase | amount | unit       |
      | Apples  | 2024-02-01     | 10     | PIECE      |
      | Milk    | 2024-02-02     | 3870   | MILLILITER |
      | Chicken | 2024-02-03     | 200    | GRAM       |

  Scenario Outline:
    When I request the record of the food item with name "<name>"
    Then the food item "<name>" should be seen

    Examples:
      | name   | dateOfPurchase | amount | unit  |
      | Apples | 2024-02-01     | 10     | PIECE |



