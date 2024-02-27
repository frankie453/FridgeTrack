Feature: Modify existing recipe to make changes to the recipe resource
  As a user, I want to modify an existing recipe,
  so I can make changes to the recipe resource.

  Background:
    Given the following recipes exist:
      | name            | description                      | ingredients                   | instruction |
      | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta | Boil pasta, cook chicken, mix together |
      | Spaghetti       | A classic pasta dish             | Spaghetti, Sauce              | Boil pasta, heat sauce, mix together |

  Scenario Outline: User updates recipe  with detailed information successfully
      When the user updates the recipe "<name>" with the following details: "<description>", "<ingredients>", "<instruction>"
      Then the recipe "<name>" should be updated with the following details: "<description>", "<ingredients>", "<instruction>"

      Examples:
        | name            | description                      | ingredients                             | instruction|
        | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta,          | Boil pasta, cook chicken, mix together |
        | Spaghetti       | A novel pasta dish               | Spaghetti, Sauce, lemon                 | Boil pasta, heat sauce, mix together |