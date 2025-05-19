@Test
Feature: Object Management in RESTful API
  As an API user
  I want to perform CRUD operations on objects
  So that I can manage data effectively


  Scenario: Verify an item can be created
    Given a "Apple MacBook Pro 16" item is created
    And is a "Intel Core i9" CPU model
    And has a price of "1849.99"
    When the request to add the item is made
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created

@New
  Scenario: Verify an ability to return an item
    Given an item exists in the system
    When I request to get the item by its ID
    Then a 200 response code is returned
    And the item details match the expected values


  Scenario: Ability to list multiple items
    Given multiple items exist in the system
    When I request to list all objects by ids
    Then a 200 response code is returned
    And the response contains all created items


  Scenario: Verify an ability to delete an item
    Given an item exists in the system
    When I request to delete the item by its ID
    Then a 200 response code is returned
    And the item is successfully deleted


  Scenario: Update an existing item
    Given an item exists in the system
    When I update the item name to "MacBook Pro 16 Updated"
    Then a 200 response code is returned
    And the item name is updated successfully
