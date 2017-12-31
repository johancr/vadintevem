Feature: Reagera på meddelande
    Som en användare som läser ett meddelande
    Vill jag kunna reagera på meddelandet genom att skriva ett nytt meddelande
    Så att det meddelandet kan tävla om att vara ett mer intressant


Scenario: The user reacts to a message
Given a message is published
And a message is fetched
When reacting to that message
Then the reaction is published
