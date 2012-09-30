Feature: Display points for a learnt skill

Scenario: Display points for a learnt skill
	Given I have learnt following skills
		| what 							| difficult 	| time | 
		| writing cucumber scenarios	|  EASY         | 10   | 
	When I enter my home page
	Then I should see points for each skill
		| 10 |
