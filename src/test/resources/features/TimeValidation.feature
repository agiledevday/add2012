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
            | 1.5           |
            | 1,5           |
            
    Scenario Outline: Validate range in time field entries
    When I enter following skill details
        | what      | difficult     | time   | 
        | GEB   | EASY  | <time> | 
    And I try to submit my skill
    Then new skill form should contain error 'Provided time is out of range 1-8760!'
    And skill should not be recorded
            
            Examples:
            | time          |
            | 8761          |
            | -1            |
            | 0             |