package de.mide.lernkarten.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Interface aus dem das DAO erzeugt wird (DAO: Data Access Object), siehe auch die Doku
 * <a href="https://developer.android.com/training/data-storage/room/accessing-data">hier</a>.
 * DAO enthält Methoden für CRUD-Operationen auf (einer) DB-Tabelle(n); einige Methoden
 * sind deshalb mit SQL-Statements in Form von Annotationen versehen.
 * <br><br>
 *
 * Mehrere DAOs können in einem Repository-Objekt zusammengefasst werden (Best Practice, es gibt
 * keine Unterstützung von Room dafür).
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
@Dao
public interface LernkartenDao {

    /**
     * Neue Lernkarte in DB einfügen.
     *
     * @param lernkarte  Entity/Objekt mit neuer Lernkarte.
     */
    @Insert
    public void insertLernkarte(LernkarteEntity lernkarte);

    /**
     * Anzahl der Lernkarten zählen.
     *
     * @return  Anzahl in DB-Tabelle mit Lernkarten gespeicherte Datensätze.
     */
    @Query("SELECT COUNT(*) FROM LernkarteEntity")
    public int getAnzahlDatensaetze();

    /**
     * Eine unbenutzte Lernkarte zurückgeben; wenn es mehrere gibt, dann wird die mit dem
     * ältesten Erzeugungsdatum zurückgegeben.
     *
     * @return  Array mit höchstens einer unbenutzten Lernkarte; kann evtl. auch leer sein,
     *          wenn es nämlich derzeit  keine unbenutzten Lernkarten gibt.
     */
    @Query("SELECT * FROM LernkarteEntity WHERE anzahl_falsch=0 AND anzahl_richtig=0 ORDER BY datetime_erzeugung ASC LIMIT 1")
    public LernkarteEntity[] getUnbenutzteKarte();

}
