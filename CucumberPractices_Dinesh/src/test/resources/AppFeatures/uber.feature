Feature: Uber Booking Feature
Scenario: Booking cab 
Given user wants to select car type "sedan" from uber app
When user selects car "sedan" and pick up point "Bangalore" and drop location "Pune"
Then Driver starts the journey
And Driver ends the journey
Then User pay 1000 USD