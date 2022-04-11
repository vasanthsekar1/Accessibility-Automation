Feature: Accessibility Testing for Link/button Text in w3schools HomePage

  @Link/ButtonText
  Scenario Outline: Validate that the given WebElement having proper Link/button Text
    Given user is on the desired webpage="<URL>" in the browser
    Then check if the WebElement="<WebElement>" is having proper link/button text="<Text>"

    Examples: 
      | URL                        |  | WebElement                                   |  | Text           |
      | https://www.w3schools.com/ |  | XPATH://*[@id='w3loginbtn']                  |  | Log in         |
      | https://www.w3schools.com/ |  | XPATH://*[@id='main']/div[2]/div/div[1]/a[3] |  | HTML Reference |
