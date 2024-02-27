Feature: Modify existing recipe to make changes to the recipe resource
  As a user, I want to modify an existing recipe so that I can make changes to the recipe resource

  Background:
    Given the following recipes exist:
      | name            | description                      | ingredients                   | directions |
      | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta | Boil pasta, cook chicken, mix together |
      | Spaghetti       | A classic pasta dish             | Spaghetti, Sauce              | Boil pasta, heat sauce, mix together |

    Scenario: Modify an existing recipe
      When I modify the "Chicken Alfredo" recipe to:
        | name            | description                      | ingredients                             | directions |
        | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta, Broccoli | Boil pasta, cook chicken, mix together, add broccoli |
      Then the "Chicken Alfredo" recipe should have the following attributes:
        | name            | description                      | ingredients                             | directions |
        | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta, Broccoli | Boil pasta, cook chicken, mix together, add broccoli |
      And the "Spaghetti" recipe should have the following attributes:
        | name            | description                      | ingredients                             | directions |
        | Spaghetti       | A classic pasta dish             | Spaghetti, Sauce                        | Boil pasta, heat sauce, mix together |

    Scenario Outline: Modify an existing recipe with a new name
        When I modify the "Chicken Alfredo" recipe to:
            | name                          | description                      | ingredients                             | directions |
            | Chicken Alfredo with Broccoli | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta, Broccoli | Boil pasta, cook chicken, mix together, add broccoli |
        Then the "Chicken Alfredo with Broccoli" recipe should have the following attributes:
            | name                          | description                      | ingredients                             | directions |
            | Chicken Alfredo with Broccoli | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta, Broccoli | Boil pasta, cook chicken, mix together, add broccoli |
        And the "Spaghetti" recipe should have the following attributes:
            | name                          | description                      | ingredients                             | directions |
            | Spaghetti                     | A classic pasta dish             | Spaghetti, Sauce                        | Boil pasta, heat sauce, mix together |

      Examples:
        | name            | description                      | ingredients                             | directions |
        | Chicken Alfredo | A creamy pasta dish with chicken | Chicken, Alfredo Sauce, Pasta, Broccoli | Boil pasta, cook chicken, mix together, add broccoli |
        | Spaghetti       | A classic pasta dish             | Spaghetti, Sauce                        | Boil pasta, heat sauce, mix together |