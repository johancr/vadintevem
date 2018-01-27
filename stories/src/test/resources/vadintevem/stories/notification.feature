Feature: Bli notifierad
  Som en användare
  Vill jag bli notifierad när någon reagerar på ett meddelande
  som jag har skrivit och har möjlighet att direkt läsa reaktionen
  Så att jag får en mer interaktiv upplevelse


Scenario: User is notified when his message is liked
  Given a message is published by user A
  When a message is read by user B
  Then user A is notified that his message was liked

