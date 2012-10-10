Feature: Add comment to skill

Scenario: Add a not empty comment to skill
	Given skill "scala" 
	When user adds comment "What a faboulous skill you learned today!" to skill "scala" 
	Then the comment "What a faboulous skill you learned today!" is displayed associated to the skill "scala"
	
Scenario: Add a not empty comment to skill
	Given skill "scala" 
	And comment "What a faboulous skill you learned today!"	
	When user adds comment "An other comment!" to skill "scala" 
	Then the comment "An other comment!" is displayed associated to the skill "scala"
	And below the comment "What a faboulous skill you learned today!"
	
Scenario: Add an empty comment to skill
	Given skill "scala" 
	When user adds an empty comment to skill "scala" 
	Then the comment is not displayed	 
