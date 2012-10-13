Feature: User is able to see Top 10

	@currentlyInDevelopment	
	Scenario: Top TEN for empty entry list
		Given entry list is empty
		When user enters top TEN list
		Then empty list should appear
		
	Scenario: Top TEN for less than 10 skills in last week
		Given Entry list contains less than TEN unique skills during last week
		| what |
		| JAVA  |
		| JAVA  |
		| JAVA  |
		| JUNIT |
		| TDD   |
		| JUNIT | 
		When user enters top TEN list
		Then all skills should appear ordered by occurrences
		| what | count |
		| JAVA | 3 |
		| JUNIT | 2 |
		| TDD |	1 |	

	Scenario: Top TEN for more than 10 skills in last week
		Given Entry list contains more than TEN unique skills during last week
		| what   | count |
		| JAVA   | 2     |
		| JAVA2  | 2     |
		| JAVA3  | 2     |
		| JUNIT  | 2     |
		| TDD1   | 2     |
		| JUNIT2 | 2     |
		| JAVA4  | 2     |
		| JUNIT3 | 2     |
		| TDD2   | 2     |
		| JUNIT4 | 2     |
		| JUNIT5 | 1     |
		When user enters top TEN list
		Then Top TEN should not contain
		|excluded| 
		|JUNIT5(1)|
	
		

				