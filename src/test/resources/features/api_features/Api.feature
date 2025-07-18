Feature: Product API 
  Description: As a user I wish to test various Product API endpoints

  @API
  Scenario: Test that Blue Tops are part of the product list
  When I get a list of all products
  Then it will contain a blue top
  