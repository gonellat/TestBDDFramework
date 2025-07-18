Feature: XML Feature
  Description: PoC on Validating an XML against an XSD

  @XMLValidation
  Scenario Outline:  Validate an XML against an XSD
    When I validate the <xml> against xsd <xsd>
    Then I get a valid response
  Examples:
  | xml                         | xsd                           |
  | "WebFeatureScenario2a.xml"  | "scenariodatavalidation.xsd"  |
  | "WebFeatureScenario2d.xml"  | "scenariodatavalidation.xsd"  |