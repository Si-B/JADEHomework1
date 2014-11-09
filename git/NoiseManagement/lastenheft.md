# Beschreibung und Hintergründe der geplanten Softwareentwicklung
Die Erfassung und Verwaltung von Lärmmessungen auf dem Firmengelände soll in Zukunft nicht mehr über eine  Microsoft Excel Tabelle erfolgen sondern über eine Softwarelösung mit zentraler Datenhaltung und mehrere gleichzeitge Benutzer erlauben. Dabei soll die zu entwickelnde Software in eine Microsoft Windows Domäne integiert und das bereits dort im Einsatz befindliche OpenResKit-Framework nutzen. Die Auslieferung und Inbetriebnahme der Software soll bis zum xx.01.2015 erfolgen.

#Produktübersicht und Einsatz
##Aktuelle Situation
Bei der Produktion der Fahrzeuge entsteht Lärm und der Gesetzgeber sieht vor, dass regelmäßig Messungen durchgeführt werden um die Lärmbelastung für die Produktionsarbeiter festzustellen. Für diese Lärmbelastungen existieren Grenzwerte deren Einhaltung vorgeschrieben ist. Um die Messungen durchzuführen muss ein Mitarbeiter die vorgegebenen Messpunkte ablaufen und jeweils eine Messung durchführen. Die Punkte an denen die Messung stattfindet sind bei jeder Begehung die gleichen, sie werden im vornherein geplant. Es existieren vereinzelt Markierungen an den Stellen, wo die Messung durchgeführt werden sollen, eine exakte Bestimmung existiert nicht. Viele der dafür zuständigen Mitarbeiter arbeiten mit Beschreibungen der Standorte. Die Ergebnisse der jeweiligen Messung werden vor Ort auf Papier erfasst und später in eine Microsoft Excel Tabelle übertragen. Neben der Möglichkeit bei der Übertragung Fehler zu machen ist es außerdem nicht möglich mit mehreren Benutzern gleichzeitig auf einer Microsoft Excel Tabelle zu arbeiten. Außerdem besteht die Gefahr fehlerhafte Daten zu erfassen weil der angegebene Messpunkt nicht eindeutig lokalisiert werden kann.
##Soll-Konzept
Mithilfe der erstellten Software können die manuell erfassten Messungen erfasst und verwaltet werden. Außerdem lässt sich die Position der Messpunkte auf Lageplänen verzeichnen. Es können Berichte unterschiedlicher Art und Umfang aus den erfassten Daten generiert werden. Weiter lässt das System mehrere gleichzeitig aktive Benutzer zu.
#Produktdetails
##Funktionale Anforderungen
+ Die Messdaten können neu erfasst werden
+ Die Messdaten können bearbeitet werden
+ Die Messdaten können entfernt werden
+ Die Messdaten werden sowohl in Tabellenform als auch in Einzelansicht präsentiert
+ Die Software kann Lagepläne in Form von Bilddateien (*.jpg, *.bmp, *.png) 
+ Auf den Lageplänen können Messpunkte markiert werden
+ Die Lagepläne können mit den Messungen in Bezug gesetzt werden
+ Die Datenhaltung erfolgt zentral
+ Die Software erlaubt mehrere parallele Benutzer

