Feature: Checkout Saucelabs E2E

  @success
  Scenario Outline: User want to Checkout Saucelabs End to End
    Given User use Device "emulator" and Open the Apps Saucelabs
    And User input username "standard_user", password "secret_sauce" and Click Login
    When User add cart product "<product>"
    When User add cart product "<product2>"
    When User add cart product "<product3>"
    When User add cart product "<product4>"
    When User add cart product "<product5>"
    When User add cart product "<product6>"
    And  User checkout the cart
    Then Checkout Complete
    Examples:
    | product                  | product2                 | product3                  | product4                          | product5              | product6                          |
    | Sauce Labs Fleece Jacket | Sauce Labs Onesie        | Sauce Labs Backpack       | Sauce Labs Bolt T-Shirt           | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |
    | Sauce Labs Onesie        | Sauce Labs Fleece Jacket | Sauce Labs Backpack       | Sauce Labs Bolt T-Shirt           | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |
    | Sauce Labs Backpack      | Sauce Labs Onesie        | Sauce Labs Fleece Jacket  | Sauce Labs Bolt T-Shirt           | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |
    | Sauce Labs Fleece Jacket | Sauce Labs Bolt T-Shirt  | Sauce Labs Backpack       | Sauce Labs Onesie                 | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |
    | Sauce Labs Fleece Jacket | Sauce Labs Onesie        | Sauce Labs Backpack       | Test.allTheThings() T-Shirt (Red) | Sauce Labs Bike Light | Sauce Labs Bolt T-Shirt           |
    | Sauce Labs Bike Light    | Sauce Labs Onesie        | Sauce Labs Fleece Jacket  | Test.allTheThings() T-Shirt (Red) | Sauce Labs Backpack   | Sauce Labs Bolt T-Shirt           |


    @problem
    Scenario Outline: User want to Checkout Saucelabs End to End
    Given User use Device "emulator" and Open the Apps Saucelabs
    And User input username "problem_user", password "secret_sauce" and Click Login
    When User add cart product "<product>"
    When User add cart product "<product2>"
    When User add cart product "<product3>"
    When User add cart product "<product4>"
    When User add cart product "<product5>"
    When User add cart product "<product6>"
    And  User checkout the cart
    Then Checkout Complete
    Examples:
      | product                  | product2                 | product3                  | product4                          | product5              | product6                          |
      | Sauce Labs Fleece Jacket | Sauce Labs Onesie        | Sauce Labs Backpack       | Sauce Labs Bolt T-Shirt           | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |


    @locked_out
    Scenario Outline: User want to Checkout Saucelabs End to End
    Given User use Device "emulator" and Open the Apps Saucelabs
    And User input username "locked_out_user", password "secret_sauce" and Click Login
    When User add cart product "<product>"
    When User add cart product "<product2>"
    When User add cart product "<product3>"
    When User add cart product "<product4>"
    When User add cart product "<product5>"
    When User add cart product "<product6>"
    And  User checkout the cart
    Then Checkout Complete
    Examples:
      | product                  | product2                 | product3                  | product4                          | product5              | product6                          |
      | Sauce Labs Fleece Jacket | Sauce Labs Onesie        | Sauce Labs Backpack       | Sauce Labs Bolt T-Shirt           | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) |
