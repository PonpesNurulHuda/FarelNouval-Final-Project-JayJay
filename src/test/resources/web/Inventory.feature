@web
  Feature: Inventory functionality

    Background:
      Given User is on login page
      When User enters valid username and password
      And User clicks login button
      Then User should be redirected to homepage

    Scenario: Add backpack to cart
      When User adds backpack to cart
      Then Cart badge should show 1 item

    Scenario: Filter product by price low to high
      When User selects filter "Price (low to high)"
      Then Products should be sorted by lowest price first

    Scenario: Filter product by name Z to A
      When User selects filter "Name (Z to A)"
      Then Products should be sorted in descending order

    Scenario: User Logout
      When User logs out from application
      Then User should be redirected to login page