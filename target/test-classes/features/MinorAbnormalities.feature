Feature: Annomalies mineures

  Background:
    Given user is on home page

  Scenario: Bug 663 Empty menu displayed
    And go to ForumBankOrange
    And click on first question and choose read more
    When user click on the dropdown
    Then a content should be displayed in