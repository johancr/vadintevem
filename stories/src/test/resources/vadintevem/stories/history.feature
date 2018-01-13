@US.7
Feature: Visa historik
  Som en användare som direkt kommer in på sajten
  Vill jag kunna se historiken av de meddelanden som jag tycker är bra
  Så att man ska komma ihåg bra insiktsfulla formuleringar som gör att man blir smartare,
  och som gör andra smartare genom att förmedla de meddelandena till andra

Scenario: When a message has been fetched, it can be found in history
  Given a message is published
  And a message is fetched by user A
  When user A looks at his history
  Then the history contains the fetched message

