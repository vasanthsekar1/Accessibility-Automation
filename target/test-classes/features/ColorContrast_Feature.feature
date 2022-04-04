Feature: Accessibility Testing for ColorContrast

  @Colorcontrast
  Scenario Outline: Validate that the given WebElement having proper colorcontrast ratio
    Given user is on the desired webpage="<URL>" in the browser="<Browser>"
    Then check if the WebElement is having proper colorcontrast ratio="<WebElement>"

    Examples: 
      | URL                        |  | Browser |  | WebElement                                   |
      | https://www.w3schools.com/ |  | Chrome  |  | XPATH://*[@id='w3loginbtn']                  |
      | https://www.w3schools.com/ |  | Chrome  |  | XPATH://*[@id='main']/div[2]/div/div[1]/a[3] |
