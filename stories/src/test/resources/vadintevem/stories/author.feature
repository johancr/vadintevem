@US.22
Feature: Se vilka meddelanden som en användare läst och skrivit
    Som en användare
    Vill jag kunna identifiera mig
    Så att applikationen kan veta vilka meddelande som just jag har läst och skrivit


Scenario: Fetch messages that a user has published
    Given a message is published by user A
    When user A fetches his published messages
    Then the published message is fetched


Scenario: Show only unread messages from other users
    Given a message is published by user A
    When user A fetches a message
    Then no message was fetched
