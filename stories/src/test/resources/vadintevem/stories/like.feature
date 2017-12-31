Feature: Gilla ett meddelande
    Som en användare som läser ett meddelande och gillar det
    Vill jag att det ska räknas som att jag gillar meddelandet om jag inte skriver en reaktion
    Så att meddelanden som inte kan bli bättre, rankas högt


Scenario: The user likes a message
    Given a message is published
    And a message is fetched
    When requesting next message
    Then the rank of the message is increased

