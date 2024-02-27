Feature: Get existing recipe with details
As a user, I want to view details of specific recipes, so I can buy things for the recipe.

  Background:
    Given the backend API is running.

  Scenario Outline: User get exiting recipe
    Given I want to see a exxisting recipe
    And the recipe is stored
    |recipe_name |recipe_id|
    |Ceaser_Salad    |RS000001|
    |Hash_Brown     |RB000234|
    |PB&J    |RB000235|
    When I enter the <name> of teh recipe
      | recipe name| 
      | PB&J      |
    
    Then the recipe with name <name> should be shown with its  <List of food used>, <List of Amount>, and <List of unit> .
      | recipe name  | List of food used | List of Amount| unit  | 
      | PB&J      |Toast,Penut butter, Jame   | 2,10,10     | PIECE,GRAM,GRAM |
