# AutocompleteAPI

Case Study Autocomplete Api Bahnhöfe<br>
Über diesen Webservice lässt sich eine Liste aller deutschen Bahnhöfe durchsuchen. Über die URL lässt sich eine Zeichenfolge von mindestens 3 übermitteln, welche der Webservice mit den Bahnhofnamen abgleicht. 
Der Webservice gibt folgendes zurück:

+ station_list: Eine Liste aller Bahnhöfe, die die Usereingabe in ihrem Namen beinhalten
+ time_taken: Dauer zwischen der Http-Anfrage und der Serverantwort
+ number_of_stations_found: Anzahl der gefundenen Übereinstimmungen

Die Anwendung ist mit Java und Spring Boot entwickelt. Der Service ist nach Codeausführung unter folgender URL erreichbar:<br>
http://localhost:8080/api/v1/auto-complete/{userinput}<br>
An der Stelle "userinput" ist es dem Nutzer freigestellt, jede beliebige Zeichenfolge mit mindestlänge 3 einzutragen. Zahlen sind nicht zulässig. 

Alternativ lässt sich der Service über eine Azurebereitstellung ohne Codeausführung testen:<br>
https://spring-app-autocomplete-api.delightfulplant-6edece55.westeurope.azurecontainerapps.io/api/v1/auto-complete/fra

