Feature: Record what have I learnt

Scenario: Add simple skill
	Given I have learnt following skills
		| what 							| difficult 	| time 	 | 
		| writing cucumber scenarios	|  difficult 	| 2 days | 
	When I enter my home page
	Then I should see following skills
		| A User has learnt writing cucumber scenarios, which was pretty difficult, and it took him 2 days. 	|

Scenario Outline: Prevent user from sumbiting incomplete skill description
	When I enter following skill details
		| what 		| difficult 	| time 	 | 
		| <skill>	| <difficulty> 	| <time> | 
	And I try to submit my skill
	Then new skill form should contain error '<message>'
	And skill should not be recorded

	Examples:
		| skill 	| difficulty	| time 	 	| message 											| 
		| 			| not empty	 	| not empty	| You have to provide skill description!			|
		| not empty |				| not empty	| You have to say how difficult it was!				|
		| not empty	| not empty		|			| You have provide info about how difficult it was! |
