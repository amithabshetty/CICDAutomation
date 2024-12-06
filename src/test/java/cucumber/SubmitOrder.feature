@tag
Feature: Purchase order from eCommerce website
  I want to use this template for my feature file

	Background: preconditons
	Given I landed on eCommerce Page
	
  @tag2
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username<name> and password<password>
    When I add product <productName>  to cart
    And checkout product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message should be displayed on Confirmation Page

    Examples: 
      |name                  |password    |     productName     |
      |ami.selenium@abc.com|Selenium123|ZARA COAT|
      |Samy.selenium@abc.com | Password    |  ADIDAS ORIGINAL|
