@US.12
Feature: Begränsad längd på meddelande
    Som en användare
    Vill jag att ett meddelandes läng ska vara begränsat
    Så att jag inte behöver spendera mycket tid för att läsa det

Scenario: The published message longer than 140 characters
    When a too long message is published
    Then an error message is returned saying that the message is too long
