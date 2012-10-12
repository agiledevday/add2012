Feature: Each entry should have creation date

    @pending
    Scenario: Record creation date of entry
        Given current date and time is '2010-01-12 12:00'
        When I try to submit my skill
        Then there should be entry with creation date '2010-01-12 12:00'

    @pending
    Scenario Outline: Display effective date in timeline
        Given current date and time is '2010-01-01 12:00' 
        When I enter following entry details
            | creationDate     |
            | <creationDate>   |
        And I try to submit my skill
        Then I should see entry with creation date displayed as '<display>'
       
        Examples:
            | creationeDate     | display           |
            | 2012-01-10        | 2012-01-10        |
            | 2012-01-02        | 2012-01-02        |

