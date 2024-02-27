Feature: Create Records of Multiple Foods in the Fridge with Details
  As a user, I want to create records of information of food I put into the fridge, so I can check the information later when needed.

  Background:
    Given the backend API is running.

  Scenario Outline: User adds new food items to the fridge with detailed information
    Given I have a fridge
    When I enter the name "<name>", dateOfPurchase "<dateOfPurchase>", amount <amount>, and unit "<unit>" for the food item.
    Then the food item "<name>" with a date of purchase "<dateOfPurchase>", amount <amount>, and unit "<unit>" should be added to the fridge's inventory

    Examples:
      | name        | dateOfPurchase | amount | unit       |
      | Apples      | 2024-02-01     | 10     | PIECE      |
      | Milk        | 2024-02-02     | 3870   | MILLILITER |
      | Chicken     | 2024-02-03     | 200    | GRAM       |

  # Need to wait for frontend logic
  #Scenario Outline: Add food items to the fridge with predefined templates (Alternate Flow)
  #  Given I have a fridge
  #  When I choose to add a food item using the "<Template>" template
  #  Then the app automatically sets the name to "<Name>" and the unit to "<Unit>"
  #  And I enter the date of purchase "<DateOfPurchase>" and the amount "<Amount>"
  #  Then the food item "<Name>" with a date of purchase "<DateOfPurchase>", amount "<Amount>", and unit "<Unit>" should be added to the fridge's inventory

  #Examples:
  #  | Template     | Name         | Unit  | DateOfPurchase | Amount |
  #  | Orange Juice | Orange Juice | MILLILITER | 2024-02-05     | 2000   |
   # | Milk         | Milk         | MILLILITER | 2024-02-06     | 3870   |
   # | Mixed Nuts   | Mixed Nuts   | KG         | 2024-02-07     | 1      |


