Feature: Application login

Scenario: Login with valid credentials
Given Open any browser
And Navigate to Login page
When User enters username as "ahmad@gmail.com" and password as "123456" into the fields
And User Clicks on Login button
Then Verify user is able to successfully login