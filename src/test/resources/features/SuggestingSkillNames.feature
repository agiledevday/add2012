#only this feature should be run (when tags property in CucumberAcceptanceIT is uncommented)
#@currentlyInDevelopment
Feature: Skill name suggestion

When user types skill name, he sees a suggestions based on existing skills.

Scenario Outline: Display matching skills while typing skill description
	Given users have already learned '<existing skills>'
	When I am typing following skill details '<skill typed>'
	Then I should see following skills suggestions '<suggestions>'

	Examples:
		| existing skills	| skill typed	| suggestions	|
		| Jacoco			| ja			| Jacoco		|
		| Jacoco;Java		| jac			| Jacoco		|
		| Jacoco;Java		| ja			| Jacoco;Java	|
		| Jacoco;Java		| foo			| 				|

@pending
Scenario: Don't display matching skills for less than two letters is given
	Given users have already learned 'Jacoco'
	When I am typing following skill details 'j'
	Then I should see no suggestions

#TODO: MZA: As a separate scenario or join with the first one?
@pending
Scenario: Don't display matching skills when not skills were lerned by users
	Given users have already learned nothing
	When I am typing following skill details 'ja'
	Then I should see no suggestions
