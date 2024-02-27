Feature: Update Records of Multiple Foods in the Fridge with Details
  As a user, I want to update the record of information of a selected food that I put into the fridge,
  so I can check the information later when needed.

  Background:
    Given the following food items are already in the fridge:
      | name        | dateOfPurchase | amount | unit  |
      | Apples      | 2024-02-01     | 10     | PIECE |
      | Milk        | 2024-02-02     | 3      | LITER |
      | Chicken     | 2024-02-03     | 200    | GRAM  |

  Scenario Outline: User updates food items to the fridge with detailed information successfully
      When the user updates the food item "<name>" with the following details: "<dateOfPurchase>", "<amount>", "<unit>"
      Then the food item "<name>" should be updated with the following details: "<dateOfPurchase>", "<amount>", "<unit>"

    Examples:
        | name        | dateOfPurchase | amount | unit  |
        | Apples      | 2024-02-01     | 20     | PIECE |
        | Milk        | 2024-02-02     | 5      | BOTTLE|
        | Chicken     | 2024-02-03     | 300    | POUND |
