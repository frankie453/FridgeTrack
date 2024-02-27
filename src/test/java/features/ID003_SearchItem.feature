Feature: Search item in the Fridge with Details
  As a user, I want to search a specific food item based on the information so that I can find out if it is in my fridge.

  Background:
    Given the backend API is running.

  Scenario Outline: User adds new recipe with details
    Given I want to store a new recipe
    And the following food items is in fridge
      |item_name |item_id|item_type|expiry_date|
      |Toast    |T000001|Deli    |2024-2-9   |
      |Penut butter     |P000001|Paste     |2025-3-1   |
      |Jam   |P000002|Paste    |2024-6-30  |
    When I enter the <name>, for the food item to search
      | food item name  |
      | Toast      |
    Then the food item with name <name> should be returned
      |item_name |item_id|item_type|expiry_date|
      |Toast    |T000001|Deli    |2024-2-9   |
