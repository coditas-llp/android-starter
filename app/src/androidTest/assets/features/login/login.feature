Feature: Login Flow

  @login
  Scenario: User launched the app
    Given User is on login screen
    When I should verify "Welcome !" text is displayed on the screen

  @login
  Scenario: User enter login credentails
    Given User is on login screen
    When I should verify "Welcome !" text is displayed on the screen
    Then I enter "test.mail@email.com" in "Email Address" field
    And I enter "password@temp" in "Enter Password" field
    And I tap on the "Log In" button
    When I should verify "Please feel free to try our common utils" text is displayed on the screen
