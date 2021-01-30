package de.mide.lernkarten.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

/**
 * Entity für Lernkarten-Datensätze/Objekte.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
@Entity
public class LernkarteEntity {

    /** Eindeutiger Schlüssel (Unique ID) der Lernkarte. */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int uid;

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

}
