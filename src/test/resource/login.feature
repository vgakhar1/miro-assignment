Feature: Miro login Page

  Scenario: E2E Miro Test
    Given I have login page opened
    When I enter valid login "vrngakhar1@gmail.com" and password "Varun@89"
    Then I see board select menu
    Then I create new board
    Then I create new sticker
    Then I share board with "Shreya.april@gmail.com"
    Then I open login page
    Then I Login with user2 "Shreya.april@gmail.com" and password "Shreya@89"
    Then I verify sticker on shared board
    Then I close driver
    
    