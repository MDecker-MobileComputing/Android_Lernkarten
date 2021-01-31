package de.mide.lernkarten.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Interface aus dem das DAO erzeugt wird (DAO: Data Access Object), siehe auch die Doku
 * <a href="https://developer.android.com/training/data-storage/room/accessing-data">hier</a>.
 * DAO enthält Methoden für CRUD-Operationen auf (einer) DB-Tabelle(n).
 * <br><br>
 *
 * Mehrere DAOs können in einem Repository-Objekt zusammengefasst werden (Best Practice, es gibt
 * keinen Library-Support dafür).
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
     * Records in Tabelle {@code LernkarteEntity} zählen.
     *
     * @return  Anzahl in DB gespeicherte Datensätze.
     */
    @Query("SELECT COUNT(*) FROM LernkarteEntity")
    public int getAnzahlDatensaetze();

}
