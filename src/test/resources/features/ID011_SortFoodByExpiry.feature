Feature: Sort the existing grocery in the order of expiry date

As a user, I would like so sort all the food in my inventory by the date of expiry
so I can first eat the food that's near the expiry

Scenario Outline: Sort the food by expiry date (Normal Flow)
  Given I have a fridge
  And I have a grocery inventory with the following items and expiry dates:
    | Item          | Expiry Date |
    | Bread         | 2024-02-18  |
    | Eggs          | 2024-02-20  |
    | Milk          | 2024-02-15  |
    | Cheese        | 2024-02-22  |
    | Yogurt        | 2024-02-25  |
  When I sort the inventory by expiry date
  Then the inventory should be sorted in the following order: 
    | Item          | Expiry Date |
    | Milk          | 2024-02-15  |
    | Bread         | 2024-02-18  |
    | Eggs          | 2024-02-20  |
    | Cheese        | 2024-02-22  |
    | Yogurt        | 2024-02-25  |
  And a recipe including the ingredient closest to expiry should be generated