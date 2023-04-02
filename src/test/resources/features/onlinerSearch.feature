Feature: Onliner smartphone search

  Scenario: Search for "iphone 14 pro max 128GB (космический черный)" in the Onliner Catalog
    Given User is on "https://www.onliner.by" page
    And The browser is maximized
    When User searches for "iphone 14 pro max 128GB (космический черный)"
    Then "Смартфон Apple iPhone 14 Pro Max 128GB (космический черный)" is the first search result
