Feature: Validating Place API's

Scenario Outline: Verify if Place is being Successfully added usign AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_Id created maps to "<name>" using "getPlaceAPI"


Examples:
	|name 		| language | address  		   		  |
	|A1 House	| English  | Beyond this world 		  |
#	|A2 House	| Arabic   | Beyond this world stage 2|
	
Scenario: Verify if Delete Place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"

