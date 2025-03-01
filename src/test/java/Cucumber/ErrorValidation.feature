Feature: Error validation


@Errorvalidation
  Scenario Outline: Errorvalidationcheck
    Given I landed on Ecommerce page
  When Logged with username <name> and password <Password>
  Then "Incorrect email or password." message is displayed

Examples:
|name                 |  Password  |
|atul25091997@gmail.com|  Atul9936 |