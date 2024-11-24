Feature: Draw in Saucelabs

  Scenario: User want to draw in Saucelabs
    Given User use Device "emulator" and Open the Apps Saucelabs
    And User input username "standard_user", password "secret_sauce" and Click Login
    When User Click Menu
    And User Click Drawing
    Then User can Draw