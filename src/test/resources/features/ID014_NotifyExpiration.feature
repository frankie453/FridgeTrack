Feature: prompt a notification when a food is about to expire
  As a user, I want to be notified when a food is about to expire, so that I can use it before it goes bad.

  Background:
    Given I have a fridge with items

  Scenario Outline:
    Given I have an expiring food item in my fridge:
      | name      | expiry          |
      | apple     | 2024-04-02      |
    When I search the item <name>
    Then I should see a notification
    Examples:
      | name      |
      | apple     |
