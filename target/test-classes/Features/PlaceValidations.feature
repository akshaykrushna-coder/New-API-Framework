Feature: Validating place API's

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>""<langauge>""<address>"
    When User call "AddPlaceAPI" API using "POST" http request
    Then The API call get success
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place id creating map to "<name>" using "GetPlaceAPI"

    Examples: 
      | name           | langauge | address |
      | Akshay Dhewale | Python   | Virul   |
	@DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User call "DeletePlaceAPI" API using "DELETE" http request
    Then The API call get success
    And "status" in response is "OK"
