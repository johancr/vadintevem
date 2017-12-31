@US.16
Feature: Visa bara olästa meddelanden
    Som en användare
    Vill jag att när jag läst ett meddelande i flödet så kommer det inte visas igen i flödet
    Så att jag alltid läser meddelanden jag inte läst tidigare och inte blir uttråkad

Scenario: When a message has been read, it won't be shown again
    Given a message is published
    And a message is fetched
    When requesting next message
    Then no next message was fetched
