Feature: Purchase the order from Ecommerce Website.
  Background:
    Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the order.
    Given Logged with username <name> and password <Password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage





    Examples:
    |name                 |  Password  |productName |
    |atul25091997@gmail.com|  Atul9936@ | ZARA COAT 3|
