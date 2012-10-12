Feature: Each entry should have creation date

    @pending
    Scenario Outline: Pretty print effective date for last 24 hours
        Given current date and time is '2010-01-12 12:00' 
        When I enter following entry details
            | creationDate     |
            | <creationDate>   |
        And I try to submit my skill
        Then I should see entry with creation date displayed as '<display>'

        Examples:
            | creationDate      | display           |
            | 2012-01-12 12:00  | now               |
            | 2012-01-12 11:59  | 1 minute ago      |
            | 2012-01-12 11:42  | 18 minutes ago    |
            | 2012-01-12 11:01  | 59 minutes ago    |
            | 2012-01-12 08:31  | 1 hour ago        |
            | 2012-01-11 12:01  | 24 hours ago      |

