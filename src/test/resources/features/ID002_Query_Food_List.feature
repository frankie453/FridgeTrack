Feature:Query Food List

As a user of the fridge tracker system
I would like to retrieve a list of available food in the fridge
So I can manage the usage of the food

Scenario: Authorized User Request List of Food (Normal Flow)

Given user "Kenzo Good" is logged into the fridge tracker system
And the following items are in the fridge:
|item_name |item_id|item_type|expiry_date|
|Banana    |A000001|Fruit    |2024-2-9   |
|Steak     |S000234|Meat     |2024-3-1   |
|Hummus    |24K1451|Deli     |2024-3-30  |

When user "Kenzo Good" requests a list of items in the fridge
Then the following list of items is generated：
|item_name |item_id|
|Banana    |A000001|
|Steak     |S000234|
|Hummus    |24K1451|

Scenario: Confirm List of  items after deleting a item (Alternate Flow)

Given user "Kenzo Good" is logged into the fridge tracker system
And the following items are in the fridge:
|item_name |item_id|item_type|expiry_date|
|Nuggets   |A0000D1|Meat     |2024-4-1   |
|Pork      |S000234|Meat     |2024-4-20  |
|Apple     |24K1451|Fruit    |2024-2-14  |
And item: "Pork" is deleted
When user "Kenzo Good" requests a list of users
Then the following list of items is generated:
|item_name |item_id|
|Nuggets   |A0000D1|
|Apple     |24K1451|

