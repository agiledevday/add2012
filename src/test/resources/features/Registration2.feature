Feature: 
	As a User 
	I want to register
	So that i can use application
	
	Scenario: User registers in application
		Given I enter registration page 
		When I submit registration data with login Bob
		Then I am logged in as Bob

   
   