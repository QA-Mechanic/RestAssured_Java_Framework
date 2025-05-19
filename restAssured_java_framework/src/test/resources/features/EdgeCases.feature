@Test
Feature: Edge Cases for RESTful API Testing
  As an API tester
  I want to verify the API handles edge cases properly
  So that I can ensure robustness of the API


  Scenario: Handling invalid item creation
    When I send an invalid JSON payload
    Then a 400 response code is returned


  Scenario: Get non-existent item
    When I request to get the item by ID "nonexistent123"
    Then a 404 response code is returned

  Scenario Outline: Create various types of items
    Given a "<name>" item is created
    And has a price of "<price>"
    When the request to add the item is made
    Then a 200 response code is returned
    And the item details are stored correctly

    Examples:
      | name               | price    |
      | iPhone 14          | 999.99   |
      | Samsung Galaxy S23 | 899.99   |
      | Google Pixel 7     | 599.99   |
