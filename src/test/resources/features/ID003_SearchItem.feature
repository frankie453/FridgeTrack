Feature: Search item in the Fridge with Details
  As a user, I want to search a specific food item based on the information so that I can find out if it is in my fridge.

  Background:
    Given I have a fridge with the following items:
      | name    | dateOfPurchase | amount | unit       |
      | Apples  | 2024-02-01     | 10     | PIECE      |
      | Milk    | 2024-02-02     | 3870   | MILLILITER |
      | Chicken | 2024-02-03     | 200    | GRAM       |

  Scenario Outline:
    When I enter the "<name>" for the item to search
    Then the item with name "<name>" should be returned

    Examples:
      | name    | dateOfPurchase | amount | unit       |
      | Apples  | 2024-02-01     | 10     | PIECE      |