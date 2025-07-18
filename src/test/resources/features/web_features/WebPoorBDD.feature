Feature: Web Test
  Description: This feature is used to open and test the Test Automation Example website
  
  Background:
    When I open the Automation Exercise website
    Then The Home Page tab title should contain "Automation Exercise"

  ## These are anti pattern for BDD
  @Web @WebPoorBDD 
  Scenario: I`m able to open Automation Exercise and view all products
    Given I open the Automation Exercise website
    Then The Home Page tab title should contain "Automation Exercise"
    When I click the Products link
    Then The Products Page Tab title should contain "Automation Exercise - All Products"

  @WebPoorBDD
  Scenario Outline: I`m able to open Automation Exercise
    Given I open the Automation Exercise website
    Then The Home Page tab title should contain "Automation Exercise"
    When I click the SignUp Login link
    Then I should see the Login Register page
    When I enter a user name <username>
    And I enter the email address <emailAddress>
    And I click signup
    Then I will see the new user page
  Examples:
   | username         | emailAddress                 |
   | "automationTest" | "automationTest@hotmail.com" |
