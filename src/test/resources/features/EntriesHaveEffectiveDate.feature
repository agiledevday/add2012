Feature: Each entry should have effective date

    Scenario: Validation of entry date
        When I enter following entry details
            | effectiveDate |
            | 2020-01-01    |
        And I try to submit my skill
        Then new skill form should contain error 'Effective date can not be in the future!'

    Scenario: Display effective date in timeline
        Given current date and time is '2010-01-01 12:00' 
        When I enter following entry details
            | effectiveDate     |
            | <effectiveDate>   |
        And I try to submit my skill
        Then I should see entry with effective date displayed as '<display>'
       
        Examples:
            | effectiveDate     | display           |
            | 2012-01-10        | 2012-01-10        |
            | 2012-01-02        | 2012-01-02        |

