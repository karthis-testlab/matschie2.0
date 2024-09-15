Feature: Office Custmore able to use incident service api to raise

  Background: 
    Given Set a base URI and the base path
    And Set authentication for the create opration

  @smoke
  Scenario: Validate the user able to create the new incidet using incident service
    When user hit the post method with the request payload
    Then new incident record should be created

  @regression
  Scenario Outline: Validate the user able to create the more the one incidets using incident service
    When user hit the post method with the following request <short_description>, <description>, <callerId> payload data
    Then new incident record should have the following <short_description>, <description>, <callerId> data got created

    Examples: 
      | short_description | description                   | callerId                         |
      | cucumber1         | description for the cucumber1 | 413a4d35eb32010045e1a5115206fe6b |
      | cucumber2         | description for the cucumber2 | 413a4d35eb32010045e1a5115206fe6b |
