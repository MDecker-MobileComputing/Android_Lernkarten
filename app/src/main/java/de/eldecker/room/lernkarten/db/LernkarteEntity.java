package de.eldecker.room.lernkarten.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import java.util.Date;


/**
 * Entity für Lernkarten-Datensätze/Objekte.
 * <br><br>
 *
 * Die einzelnen Member-Variablen werden ausnahmsweise nicht mit einem Unterstrich
 * am Anfang versehen, weil die Entity-Klasse keine Getter/Setter-Methoden hat, und
 * eine Befüllung der Membervariablen in der Form <code>lokaleVariable._memberVariable</code>
 * seltsam aussehen würde.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
@Entity
public class LernkarteEntity {

    /**
     * Eindeutiger Schlüssel der Lernkarte, wird automatisch befüllt; Spaltenname "_id"
     * gewählt, weil die für die Listendarstellung verwendete Klasse <code>CursorAdapter</code>
     * dies so erwartet.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NonNull
    public int id;

    /** Text auf Vorderseite der Lernkarte, z.B. mit Frage. */
    @ColumnInfo(name = "text_vorne")
    public String textVorne;

    /** Text auf Rückseite der Lernkarte, z.B. mit Antwort. */
    @ColumnInfo(name = "text_hinten")
    public String textHinten;

    /** Anzahl, wie oft die Lernkarte richtig beantwortet wurde. */
    @ColumnInfo(name = "anzahl_richtig")
    public int anzahlRichtig;

    /** Anzahl, wie oft die Lernkarte falsch beantwortet wurde. */
    @ColumnInfo(name = "anzahl_falsch")
    public int anzahlFalsch;

    /**
     * Zeitstempel für letzte richtige Beantwortung der Karte, siehe auch
     * Klasse {@link MeineTypeConverter}; der Initialwert ist <code>null</code>.
     */
    @ColumnInfo(name = "datetime_richtig")
    public Date dateTimeLetztesMalRichtigeAntwort;

    /**
     * Zeitstempel für letzte falsche Beantwortung der Karte, siehe auch
     * Klasse {@link MeineTypeConverter}; der Initialwert ist <code>null</code>.
     */
    @ColumnInfo(name = "datetime_falsch")
    public Date dateTimeLetztesMalFalscheAntwort;

    /**
     * Zeitstempel für Erzeugung der Lernkarte; Wert wird bei Erzeugung erstellt
     * und ändert sich nie.
     */
    @ColumnInfo(name = "datetime_erzeugung")
    public Date dateTimeErzeugung;

}
