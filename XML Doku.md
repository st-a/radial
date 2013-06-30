#Aufbau der XML

##Grundstruktur
Die ersten Knoten der XML bilden die beteiligten *Personen* mit ihren Namen als Attribut. Diese haben als Kinderknoten *Tage*. Ein Tag hat als einziges Attribut seinen Namen.
```
<person name="Person">
  <tag name="Montag">
      ...
  </tag>
  
  <tag name="Dienstag">
      ...
  </tag>   
</person>
```

##Aktivitäten
Jeder *Tag* hat eine beliebige Anzahl von *Aktivitäten*. Diese stehen für je eine Bewegung von A nach B. 
```
<tag name="DayName">
  	<activity>
        ...
    </activity>
    
    <activity>
        ...
    </activity>
    
    <activity>
        ...
    </activity>
</tag>
```

Eine Aktivität hat genau 8 Kinderknoten.
+ Startort
+ Startzeit
+ Zielort
+ Ankunftszeit
+ Kategorie des Ziels
+ Transportmittel
+ Dauer der Bewegung in Minuten
+ zurückgelegte Distanz in Meter

```
<activity>
		<begin>Home</begin>
		<beginTime hour="9" minutes="30"></beginTime>
		<end>Markt am Großen Garten</end>
		<endTime hour="9" minutes="38"></endTime>
		<category>Freizeit</category>
		<transport>Fahrrad</transport>
		<duration>8</duration>
	  <distance>2200</distance>
</activity>
```

Alle Knoten besitzten Textelemente und keine Attribute **außer** die Start- und Ankunftszeit.
Diese haben 2 Attribute. In ihnen befindet sich die Uhrzeit getrennt nach Stunden- bzw. Minutenangabe.
Angegeben wird die Zeit nach dem 24h Schema.

```
<beginTime 
  hour="9" 
  minutes="30"
  >
  
</beginTime>
```  
