Feature: check missing part of recipe
As a user, I want to view what existing food I already have for a recipe, so I know what Im lacking for the recipe.

  Background:
    Given the backend API is running.

  Scenario Outline: User check lacking part of recipe
    Given I want to see a existing recipe
    And the recipe is stored：
    |recipe_name |recipe_id|
    |Ceaser_Salad    |RS000001|
    |Hash_Brown     |RB000234|
    |PB&J    |RB000235|
#NormalFlow
    And food items stored：
    |item_name |item_id|item_type|expiry_date|amount|Unit|
    |Toast   |A000001|Fruit    |2024-2-9   | 1 | PIECE
    |Penut butter     |S000234|Meat     |2024-3-1   | 50|GRAM|
    |Hummus    |24K1451|Deli     |2024-3-30  |10|GRAM|

    When I enter the <name> of teh recipe
      | recipe name| 
      | PB&J      |
    
    Then the recipe with name <name> should be shown with its  <List of food used>, <List of Amount>, and <List of unit> .
      | recipe name  | List of food used | List of Amount| unit  | 
      | PB&J      |Toast,Penut butter, Jam   | 2,10,10     | PIECE,GRAM,GRAM |
   And the lacking part with <item_name>, <|item_id>, <item_type>,<expiry_date> ,  <amount>,<Unit>.
    |item_name |item_id|item_type|expiry_date|amount|Unit|
    |Toast   |A000001|Fruit    |2024-2-9   | 1 | PIECE
    |Penut butter     |S000234|Meat     |2024-3-1   | 0|GRAM|
    |Hummus    |24K1451|Deli     |2024-3-30  |0|GRAM|


#Alternative flow
Senario:    
    And food items stored：
    |item_name |item_id|item_type|expiry_date|amount|Unit|
    |Toast   |A000001|Fruit    |2024-2-9   | 1 | PIECE
    |Penut butter     |S000234|Meat     |2024-3-1   | 50|GRAM|
    |Hummus    |24K1451|Deli     |2024-3-30  |10|GRAM|
    When I enter the <name> of teh recipe
      | recipe name| 
      | PB&J      |
    
   Then the recipe with name <name> should be shown with its  <List of food used>, <List of Amount>, and <List of unit> .
      | recipe name  | List of food used | List of Amount| unit  | 
      | PB&J      |Toast,Penut butter, Jam   | 2,10,10     | PIECE,GRAM,GRAM |
   And the lacking part with <item_name>, <|item_id>, <item_type>,<expiry_date> ,  <amount>,<Unit>.
    |item_name |item_id|item_type|expiry_date|amount|Unit|
    |Toast   |A000001|Fruit    |2024-2-9   | 2 | PIECE
    |Penut butter     |S000234|Meat     |2024-3-1   | 0|GRAM|
    |Hummus    |24K1451|Deli     |2024-3-30  |0|GRAM|
