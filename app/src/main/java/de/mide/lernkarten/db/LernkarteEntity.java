package de.mide.lernkarten.db;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

/**
 * Entity für Lernkarten-Datensätze/Objekte.
 */
@Entity
public class LernkarteEntity {

    /** Eindeutiger Schlüssel (Unique ID) der Lernkarte. */
    @PrimaryKey
    @NonNull
    public int uid;

    /** Text auf Vorderseite der Lernkarte, z.B. mit Frage. */
    public String textVorne;

    /** Text auf Rückseite der Lernkarte, z.B. mit Antwort. */
    public String textHinten;

}
