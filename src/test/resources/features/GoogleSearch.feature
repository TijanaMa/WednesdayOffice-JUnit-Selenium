Feature: Google search functionality
  Agile story: As a user, when I am on the google search page
  I should be able to search anything and see relevant results

  Scenario: Search page default title verification
    When user is on the Google search page
    Then user should see title is Google

  @wip
  Scenario: Search result title verification
    When user is on the Google search page
    And user search for apple
    Then title should be able to see apple in the title