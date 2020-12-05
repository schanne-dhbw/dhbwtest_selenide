Java-NetBeans-Projekt für Selenide Tests der DHBW Webseite
==========================================================

Das Projekt basiert auf Maven und erlaubt UI-Tests mit Selenide in Java.

Test auf PC (mit lokal laufender Web-App: ng serve)
---------------------------------------------------

mvn -DDHBW_URL=https://www.karlsruhe.dhbw.de/ -DWEB_DRIVER_PROVIDER=dhbw.test.util.FirefoxWebDriverProvider test

mvn -DDHBW_URL=https://www.karlsruhe.dhbw.de/ -DWEB_DRIVER_PROVIDER=Firefox -Dtest=dhbw.test.DhbwTests#testOffline test

mvn -DDHBW_URL=https://www.karlsruhe.dhbw.de/ -DWEB_DRIVER_PROVIDER=Chrome -Dtest=dhbw.test.DhbwTests#testWirtschaftsinformatik test

Supported provider:
- Firefox
- Chrome (default in NetBeans actions)
- ChromeHeadless
- Chromium
- ChromiumHeadless (useful for Linux Docker Container)

Tests:
- testOffline
- testWirtschaftsinformatik
