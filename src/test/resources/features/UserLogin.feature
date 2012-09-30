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
	
Scenario: Display logged user
	Given I have been logged in successfully
	When I enter any page
	Then I should see that I am logged in
	
Scenario: Redirect anonymous user to login page
	Given I am not logged in
	When I enter any page
	Then I should see login page