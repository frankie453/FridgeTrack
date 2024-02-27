Feature: View the food list in order of the closest expiration date.
  As a user, I want to view the food list in order of the closest expiration date so that I can easily see which foods are going to expire soon.

  Background:
    Given the following food items exist:
      | name       | expirationDate |
      | Milk       | 2020-12-01      |
      | Yogurt     | 2020-12-20      |
      | Canned Corn| 2020-12-15      |
      | Bread      | 2020-12-10      |

    Scenario Outline: View the food list in order of the closest expiration date.
      When I view the food list
      Then I should see the following food items with their <name> in order of the closest <expirationDate>:
      Examples:
        | name       | expirationDate  |
        | Milk       | 2020-12-01      |
        | Yogurt     | 2020-12-20      |
        | Canned Corn| 2020-12-15      |
        | Bread      | 2020-12-10      |