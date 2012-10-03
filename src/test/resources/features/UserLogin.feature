Feature: User is able to login with credentials to application

Scenario: Go to skills page after successfull login
	Given I enter login screen
	When I enter valid login and password
	Then I should see my user skills page

Scenario Outline: Validate presence of login/password
	Given I enter login screen
	When I sign in with following credentials <login>/<password>
	Then login form should contain error '<message>'

	Examples:
		| login 	| password 	| message 						|
		| 			| not empty | Please enter your login!		| 
		| not empty | 			| Please enter your password!	|  
	
Scenario: Display logged user
	Given I have been logged in successfully as 'Diligent Student'
	When I enter any page
	Then I should see that I am logged in as 'Diligent Student'
