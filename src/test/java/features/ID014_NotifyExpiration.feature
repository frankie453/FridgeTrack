Feature: prompt a notification when a food is about to expire
  As a user, I want to be notified when a food is about to expire, so that I can use it before it goes bad.

  Background:
    Given I have a food item in my fridge
    And the food item is about to expire
    When I open the fridge
    Then I should see a notification that the food item is about to expire

Scenario Outline:
    Given I have a food item in my fridge and today is <today>
    And the food item is about to expire on <expiration date>
    And the <expiration date> - <today> is less than 3 days
    When I open the fridge
    Then I should see a notification that <name> is about to expire
    Examples:
        | name      | expiration date |
        | apple     | 2024-12-01      |
        | milk      | 2024-12-02      |
        | bread     | 2024-12-03      |