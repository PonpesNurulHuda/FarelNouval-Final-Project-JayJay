@web @e2e
Feature: Checkout Process

  Scenario: Complete checkout successfully
    Given User is on login page
    When User enters valid username and password
    And User clicks login button
    Then User should be redirected to homepage

    When User adds backpack to cart
    And User clicks cart icon
    And User clicks checkout button
    And User fills checkout information
    And User clicks continue
    And User clicks finish
    Then Order should be completed successfully