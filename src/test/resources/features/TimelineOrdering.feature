Feature: Timelines are ordered according to creation date

    @pending
    Scenario: Display entries from most recent to most outdated
            | entry     | creationDate  |
            | Java      | 2010-01-01    |
            | Scala     | 2012-03-03    |
            | Cucumber  | 2011-11-11    |
        When I enter global timeline page
        Then I should see following entries in timeline
            | entry     |
            | Scala     |
            | Cucumber  |
            | Java      |

