Feature: Partakings
Scenario: Create a Partaking
Given url 'http://localhost:8080/api/events/20/partakings'
And request { "id" : "00000000-0000-0000-0000-000000000060", "type" : "assistant" }
When method POST
Then status 200