Feature: Login Flow

  @login
  Scenario: User launched the app
    Given User is on login screen
    When I should verify "Welcome !" text is displayed on the screen
