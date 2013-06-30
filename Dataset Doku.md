#Human Klasse (Hilfsklasse)

Ein *Human-Objekt* entspricht einer Person.
Beim Initialisieren des Datasets wird für jede in der XML vorkommenden Person ein *Human-Objekt* erstellt.

###Konstruktor
Benötigt nur ein String welcher den Namen der Person beschreibt.
**Human ( _String_ name)**


###Attruibute
Bisher sind alle Atrribute außer der Name leer und werden an keiner anderen Stelle benutzt oder abgefragt.

*String*
+ name
+ gender
+ home

*Integer*  
+ age

###Funktionen
primär Abfragefunktionen
+ getName() : *String*
+ getGender() : *String*
+ getAge() : *String*

#Activity Klasse (Hilfsklasse)

Ein *Activity-Objekt* repräsentiert eine konkrete Bewegung von A nach B. 

###Konstruktor
Nur initialisieren aller Variablen. 

###Attribute

**Leere Attribute haben den Wert _0_.**


*String*
+ day - Tag der Aktivität
+ catagory - Kategorie
+ transport - benutztes Transportmittel
+ start - Startpunkt der Bewegung
+ end - Ziel der Bewegung
	
*Human*	
+ human - ausführende Person 

*Integer*
+ distance - zurückgelegte Distanz in Meter
+ duration - benötigte Zeit in Minuten

*Array*
+ bTime [2] - Uhrzeit bei Begin der Aktivität

> [0] - Stundenamgabe

> [1] - Minutenangabe

+ eTime [2] - Uhrzeit bei beendigung der Aktivität

> [0] - Stundenangabe

> [1] - Minutenangabe

###Funktionen

+ getHuman() : *Human*
+ getDay() : *String*
+ getDistance() : *Integer*
+ getBeginTime() : *Integer [ ]* 
+ getEndTime() : *Integer [ ]* 


#Dataset Klasse
Ist das Objekt, welches die XML parsed und alle Daten speichert. **Alle Anfragen sollen über dieses Objekt laufen.**
Muss am Anfang der Anwendung einmal initialisiert werden.

###Konstruktor
Benötigt wird ein String der den Pfad zur XML beinhaltet.
**Dataset (_String_ source)**

Bei der Initialisierung wird die XML "geparsed", alle Hilfsklassen generiert und alle Attribute zugewiesen.

###Attribute

*Array*
+ humans [4] - alle Beteiligten Personen
+ activities [300] - alle aufgezeichneten Bewegungen


###Funktionen
+ getPersons () : *Human [ ]*

> Ein Array aller beteiligten Personen wird übermittelt. 

+ getPerson (*String* name) : *Human*

> Eine konkrete Person wird zurückgegeben. Ausgwählt wird die Person bezüglich ihres Namen. Abfrage nach doppelten Personen/Namen ist nicht enthalten.

+ getActivities () : *Activity [ ]*

> Es werden alle aufgezeichneten Bewegungen übermittelt. Null-Pointer können auftauchen und werden nicht herrausgefiltert.

+ getPersonActivities (*String* name) : *Activity [ ]*

> Es werden alle Bewegungen einer konkreten Person zurückgegeben. Benötigt wird nur der Name der Person. Keine Abfrage nach Dopplungen enthalten. Keine Null-Pointer enthalten.

+ getAvtivitiyByDay (*String* day) : *Activity [ ]*

> Es werden alle Bewegungen die an einen Tag statt gefunden haben zurückgegeben. Benötigt wird nur der Name des Tages. Keine Abfrage nach Dopplungen enthalten. Keine Null-Pointer enthalten.

+ getAvtivitiyByDayAndPerson (*String* day, *String* name) : *Activity [ ]*

> Es werden alle Bewegungen die von einer konkreten Person an einen Tag statt gefunden haben zurückgegeben. Benötigt wird nur der Name des Tages und der Person. Keine Abfrage nach Dopplungen enthalten. Keine Null-Pointer enthalten.
