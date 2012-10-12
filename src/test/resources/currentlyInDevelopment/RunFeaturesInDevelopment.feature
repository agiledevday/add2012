Feature: Run only features in development

@currentlyInDevelopment
Scenario: Do not fail if no features in development
    Given there is scenario with tag @currentlyInDevelopment
    When I run test CurrentlyInDevelopmentAcceptanceIT
    Then there should be run only scenarios in development