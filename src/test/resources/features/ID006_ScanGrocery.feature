Feature: Scan Food's Information
  As a user, I want to scan the food's information, so I don't have to manually put in every information of food I'm putting into the fridge

  Background:
    Given the backend API is running.

  Scenario: User scans a food item to add to the fridge
    Given I have a food item with a scannable expiry date
    When I scan the food item's expiry date
    Then the system should automatically fetch the expiry
    And user should be prompted to add the rest of the infomation for the item with expiry already filled out

  Scenario: User scans a food item with unfetchable expiry (Alternate Flow)
    # Note this is a Alternate Flow rather then Error Flow as the application will recover from the unreadable exp date.
    Given I have a food item with a scannable expiry date
    When I scan the food item's UNFETCHABLE expiry date
    Then the system should return an error indicating the expiry date is unreadable
    And user should be prompted to manually add the food item
    