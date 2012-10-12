#only this feature should be run (when tags property in CucumberAcceptanceIT is uncommented)
#@currentlyInDevelopment
Feature: Skill name suggestion

When user types skill name, he sees a suggestions based on existing skills.

  Scenario Outline: Display matching skills while typing skill description
    Given users have already learned '<existing skills>'
    When I am typing following skill details '<skill typed>'
    Then I should see following skills suggestions '<suggestions>'

  Examples:
    | existing skills | skill typed | suggestions |
    | jacoco          | ja          | jacoco      |
    | jacoco;java     | jac         | jacoco      |
    | jacoco;java     | ja          | jacoco;java |
    | jacoco;java     | foo         |             |

  @pending
  Scenario: Don't display matching skills for less than two letters is given
    Given users have already learned 'Jacoco'
    When I am typing following skill details 'j'
    Then I should see no suggestions

  @pending
  Scenario: Don't display matching skills when not skills were lerned by users
    Given users have already learned nothing
    When I am typing following skill details 'ja'
    Then I should see no suggestions

  Scenario Outline: Display matching skills ignoring case
    Given users have already learned '<existing skills>'
    When I am typing following skill details '<skill typed>'
    Then I should see following skills suggestions '<suggestions>'

  Examples:
    | existing skills | skill typed | suggestions |
    | Jacoco          | ja          | Jacoco      |
    | Jacoco;JAVA     | JA          | Jacoco;JAVA |

  @pending
  Scenario: Display matching skills in alphabetical order
    Given users have already learned
      | Java;Jacoco;Jabber |
      | Java2;Java1        |
    When I am typing following skill details 'ja'
    Then I should see
      | Jabber;Jacoco;Java |
      | Java1;Java2        |

  @pending
  Scenario: Display up to 5 skill suggestions
    Given users have already learned 'Jacoco;Java;JavaScript;Jabber;Jamaica;Jango'
    When I am typing following skill details 'ja'
    Then I should see 'Jacoco;Java;JavaScript;Jabber;Jamaica'

  @pending
  Scenario: Display distinct skill names
    Given users have already learned
      | Java;Jacoco;Java |
    When I am typing following skill details 'ja'
    Then I should see
      | Jacoco;Java |
