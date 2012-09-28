Feature: Display what have I learnt

Scenario: Encourage user to record what has he learnt
	Given I haven't learnt anything
	When I enter my home page
	Then I should see encouragement message to record my skills

Scenario: Encouragement message should disappear after recording first skill
	Given I have learnt new skill lately
	When I enter my home page
	Then I should no longer see encouragement message to record my skills

Scenario: Display what have I learnt
	Given I have learnt new skill lately
	When I enter my home page
	Then I should see skill I have learnt