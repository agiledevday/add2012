Feature: Timelines should show limited number of entries

    @pending
    Scenario: Limit displayed entries to 5
        Given there are following entries
            | entry     |
            | Java      |
            | Scala     |
            | Cucumber  |
            | Wicket    |
            | TDD       |
            | Groovy    |
        When I enter global timeline page
        Then I should see following entries in timeline
            | entry     |
            | Java      |
            | Scala     |
            | Cucumber  |
            | Wicket    |
            | TDD       |

    @pending
    Scenario: Extend timeline if user request more results
        Given there are following entries
            | entry     |
            | Java      |
            | Scala     |
            | Cucumber  |
            | Wicket    |
            | TDD       |
            | Groovy    |
        When I enter global timeline page
        When I request more results in timeline
        Then I should see following entries in timeline
            | entry     |
            | Java      |
            | Scala     |
            | Cucumber  |
            | Wicket    |
            | TDD       |
            | Groovy    |
