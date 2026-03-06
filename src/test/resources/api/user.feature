@api
Feature: User API

  Scenario: User lifecycle test
    When Send POST request to create user
    Then Response status code should be 200
    And Save created user id

    When Send GET request using saved user id
    Then Response status code should be 200

    When Send PUT request to update saved user
    Then Response status code should be 200

    When Send DELETE request to delete saved user
    Then Response status code should be 200

  Scenario: Get list of tags
    When Send GET request to get list of tags
    Then Response status code should be 200

  @negative
  Scenario: Get user with invalid ID
    When Send GET request with invalid user id
    Then Response status code should be 400