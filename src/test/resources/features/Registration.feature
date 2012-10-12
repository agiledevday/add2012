Feature: User is able to register new account in application

    @pending
    Scenario Outline: Validate registration form against required data
        Given I enter registration page
        When I try to submit registration form with following data
            | login     | password      | email     |
            | <login>   | <password>    | <email>   |
        Then I should see message <errorMessage>
        And new user should not be registered
   
        Examples:
            | login     | password  | email     | errorMessage                  |
            |           | notEmpty  | notEmpty  | Please provide your login!    |
            | notEmpty  |           | notEmpty  | Please provide your password! |
            | notEmpty  | notEmpty  |           | Please provide your email!    |

    @pending
    Scenario Outline: Validate registration form against matching passwords
        Given I enter registration page
        When I try to submit registration form with following data
            | password      | passwordConfirmation      |
            | <password>    | <passwordConfirmation>    |
        Then I should see message <errorMessage>
        And new user should not be registered

        Examples:
            | password      | passwordConfirmation  | errorMessage              |
            | somePassword  | someOtherPassword     | Password does not match!  |

    @pending
    Scenario Outline: Validate registration form against password strength
        Given I enter registration page
        When I try to submit registration form with following data
            | password      | passwordConfirmation  |
            | <password>    | <password>            |
        Then I should see message <errorMessage>
        And new user should not be registered
   
        Examples:
            | password         | errorMessage                                       |
            | short            | Password has to have at least 6 letters!           |
            | lowercaseonly    | Password has to have both upper and lower letters! |
            | UPPERCASEONLY    | Password has to have both upper and lower letters! |
            | noNumbers        | Password has to contain numbers!                   |
            | 0SpecialChars    | Password has to contain at least one of !@#$%^&*   |

    @pending
    Scenario Outline: Validate email in registration form
        Given I enter registration page
        When I try to submit registration form with following data
            | email             |
            | <invalidEmail>    |
        Then I should see message 'Please provide valid email!'
        And new user should not be registered

        Examples:
            | invalidEmail          |
            | invalidEmail          |
            | invalid@email         |
            | invalid@ email.com    |
            | invalid@email..com    |

    @pending
    Scenario: Prevent registering user with same login
        Given I enter registration page
        And there is already user with login 'alreadyTakenLogin'
        When I try to submit registration form with following data
            | login             |
            | alreadyTakenLogin |
        Then I should see message 'This login is already taken. Please choose different one!'

    @pending
    Scenario: Successfull registration
        Given I enter registration page
        When I try to submit registration form with following data
            | login     | password  | passwordConfirmation  | email             |
            | newUser   | Str0ng!   | Str0ng!               | valid@email.com   |
        Then there should be user with following data
            | login     | password  | email             |
            | newUser   | Str0ng!   | valid@email.com   |
        And I should see message 'User has been successfully created! You can now login!'
