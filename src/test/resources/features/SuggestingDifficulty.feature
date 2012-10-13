#only this feature should be run (when tags property in CucumberAcceptanceIT is uncommented)
#@currentlyInDevelopment
Feature: Difficulty suggestion

When user types skill, he sees a suggestion of most common difficulty for this skill.

  Scenario: Display most common difficulty after typing skill description
    Given users have already learned following skills
    	| skill	| difficulty |
    	| scala	| hard |
    	| scala	| easy |
    	| scala | hard |
    When I typed following skill name "scala"
    Then I should see following difficulty suggestion "hard"


  