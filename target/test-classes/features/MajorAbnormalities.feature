@MajorAbnormalities
@severity=critical
Feature: Anomalies Majeures

  Background:
    Given user is on home page
@bug_669
  Scenario: Bug 669 Source code download
    And user click on notre idee de banque
    When user click on campagne de lancement
    Then No files should be downloaded


