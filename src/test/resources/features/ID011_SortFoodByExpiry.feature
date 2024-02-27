Feature: Sort the existing grocery in the order of expiry date

  As a user, I would like so sort all the food in my inventory by the date of expiry so I can first eat the food that's near the expiry

  Background: 
    Given the following items exist in the fridge:
      | name     | record_id | enter_date | quantity | expiry_date |
      | Milk     | 0         | 2024-01-15 | 1        | 2024-02-15  |
      | Bread    | 1         | 2024-01-16 | 2        | 2024-02-18  |
      | Eggs     | 2         | 2024-01-17 | 3        | 2024-02-20  |
      | Cheese   | 3         | 2024-01-18 | 3        | 2024-02-22  |
      | Yogurt   | 4         | 2024-01-19 | 3        | 2024-02-25  |
    And the following recipes exist in the fridge as well:
      | name          | ingredients               |
      | Hot Chocolate | Milk, Cocoa Powder, Cream |

  Scenario Outline: Sort the food by expiry date (Normal Flow)
    When the user sorts the inventory by expiry date
    Then the inventory should output the item "<item>" with the closest expiry date "<expiry_date>"
    And a recipe "<recipe>" including the item "<item>" closest to expiry should be shown

    Examples:
      | item   | expiry_date | recipe        |
      | Milk   | 2024-02-15  | Hot Chocolate |
