Feature: User is able to login with credentials to application

Scenario: Successfull login
	Given I enter login screen
	When I enter valid login and password
	Then I should see my user skills page

Scenario: Login with no password
	Given I enter login screen
	When I enter valid login and no password
	Then I should see error with no password

Scenario: Login with no login
	Given I enter login screen
	When I enter no login and password
	Then I should see error with no login