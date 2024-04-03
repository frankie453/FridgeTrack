Feature: View the food list in order of the closest expiration date.
  As a user, I want to view the food list in order of the closest expiration date so that I can easily see which foods are going to expire soon.

  Background:
    Given the following food items exist:
      | name         | expirationDate |
      | Milk         | 2020-12-01     |
      | Yogurt       | 2020-12-20     |
      | Canned Corn  | 2020-12-15     |
      | Bread        | 2020-12-10     |
      | Apples       | 2020-12-05     |
      | Chicken      | 2020-12-03     |
      | Fish         | 2020-12-04     |
      | Eggs         | 2020-12-08     |
      | Cheese       | 2020-12-06     |
      | Butter       | 2020-12-07     |
      | Carrots      | 2020-12-09     |
      | Tomatoes     | 2020-12-11     |
      | Lettuce      | 2020-12-12     |
      | Grapes       | 2020-12-13     |
      | Bananas      | 2020-12-14     |
      | Peaches      | 2020-12-16     |
      | Plums        | 2020-12-17     |
      | Cereal       | 2020-12-18     |
      | Rice         | 2020-12-19     |
      | Pasta        | 2020-12-21     |
      | Baked Beans  | 2020-12-22     |
      | Chocolate    | 2020-12-23     |
      | Beer         | 2020-12-24     |
      | Soda         | 2020-12-25     |

    Scenario: View the food list in order of the closest expiration date.
      When I view the food list
      Then I should see the following food items with order of the closest expirationDate
        | name         | expirationDate  |
        | Milk         | 2020-12-01      |
        | Chicken      | 2020-12-03      |
        | Fish         | 2020-12-04      |
        | Apples       | 2020-12-05      |
        | Cheese       | 2020-12-06      |
        | Butter       | 2020-12-07      |
        | Eggs         | 2020-12-08      |
        | Carrots      | 2020-12-09      |
        | Bread        | 2020-12-10      |
        | Tomatoes     | 2020-12-11      |
        | Lettuce      | 2020-12-12      |
        | Grapes       | 2020-12-13      |
        | Bananas      | 2020-12-14      |
        | Canned Corn  | 2020-12-15      |
        | Peaches      | 2020-12-16      |
        | Plums        | 2020-12-17      |
        | Cereal       | 2020-12-18      |
        | Rice         | 2020-12-19      |
        | Yogurt       | 2020-12-20      |
        | Pasta        | 2020-12-21      |
        | Baked Beans  | 2020-12-22      |
        | Chocolate    | 2020-12-23      |
        | Beer         | 2020-12-24      |
        | Soda         | 2020-12-25      |