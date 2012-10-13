Feature: Enrty field time has to be numeric field that indicate hours
   
    Scenario Outline: Validate time field in entrys
    When I enter following skill details
        | what      | difficult     | time   | 
        | GEB   | EASY  | <time> | 
    And I try to submit my skill
    Then new skill form should contain error 'Please enter correct time in hours!'
    And skill should not be recorded
            
            Examples:
            | time          |
            | 10 hours      |
            | BLABLABLA     |     