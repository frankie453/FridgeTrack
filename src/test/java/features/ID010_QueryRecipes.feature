Feature:Query Recipe List

As a user, I want to view all stored recipes, so I can get an idea of what may be suggested to me in the notification.

Scenario: Authorized User Request List of recipe (Normal Flow)

Given user "Kenzo Good" is logged into the fridge tracker system
And the following items are in the fridge:
|recipe_name |recipe_id|
|Ceaser_Salad    |RS000001|
|Hash_Brown     |RB000234|
|PB&J    |RB000235|

When user "Kenzo Good" requests a list of recipes in the fridge
Then the following list of recipes is generated：
|recipe_name |recipe_id|
|Ceaser_Salad    |RS000001|
|Hash_Brown     |RB000234|
|PB&J    |RB000235|


Scenario: Confirm List of  items after deleting a item (Alternate Flow)

Given user "Kenzo Good" is logged into the fridge tracker system
And the following recipes are in the fridge:
|recipe_name |recipe_id|
|Ceaser_Salad    |RS000001|
|Hash_Brown     |RB000234|
|PB&J    |RB000235|
And item: "PB&J" is deleted
When user "Kenzo Good" requests a list of users
Then the following list of items is generated:
|recipe_name |recipe_id|
|Ceaser_Salad    |RS000001|
|Hash_Brown     |RB000234|


