Feature: Feature to validate News Validation Site

  @functionaltest
  Scenario Outline: Fact Check of News on TheGuardian Site
    Given User gets news from guardianchannel website
    And User enters the news <samplenews> for correctness <website>
    Then User verifies Genuinity of News

    Examples: 
      | samplenews                      | website                |
      | samplenewsfromwebsite           | https://www.google.com |
      #|Ethiopia PM threatens restive province as crisis escalates      | http://www.google.com  |
