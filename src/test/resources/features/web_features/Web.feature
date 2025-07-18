Feature: Web Test
  Description: This feature is used to open and test the Test Automation Example website

  Background:
    Given I've successfully opened the "Automation Exercise" website

  @Web @CSV
    Scenario Outline: I`m able to register a new user using csv details
      And I have read in the csv test data <dataFile> for the scenario
      When I register a new user using csv details
      Then a new user is created
    Examples: adminUser
      | dataFile                   | 
      | "WebFeatureScenario3a.csv" | 
      | "WebFeatureScenario3b.csv" |
      
  @Web @XMLDemo 
  Scenario Outline: I`m able to start the registration process for a new user xml details
    And I have read in the test data <dataFile> for the scenario
    When I register a new user
    Then a new user is created
  Examples: adminUser
  | dataFile                   |
  | "WebFeatureScenario1a.xml" | 

  @Web @XML
  Scenario Outline: I`m able to register a new user
    And I have read in the test data <dataFile> for the scenario
    When I register a new user
    Then a new user is created
  Examples: adminUser
  | dataFile                   | Data                                                       | 
  | "WebFeatureScenario2a.xml" | supplied mandatory fields entered only ie no Company Field |
  | "WebFeatureScenario2b.xml" | plus company optional field is generated                   |
  | "WebFeatureScenario2c.xml" | Mandatory fields generated                                 |
  | "WebFeatureScenario2d.xml" | Invalid XML                                                |
