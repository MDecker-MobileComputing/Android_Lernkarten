package de.mide.lernkarten.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import de.mide.lernkarten.helpers.LernModusEnum;

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
     * Lernkarte aktualisieren.
     *
     * @param lernkarte  Zu aktualisierende Lernkarte.
     */
    @Update
    public void updateLernkarte(LernkarteEntity lernkarte);

    /**
     * Anzahl der Lernkarten zählen.
     *
     * @return  Anzahl in DB-Tabelle mit Lernkarten gespeicherte Datensätze.
     */
    @Query("SELECT COUNT(*) FROM LernkarteEntity")
    public int getAnzahlDatensaetze();

    /**
     * Query für {@link LernModusEnum#NOCH_NIE_VERWENDET}:
     * Eine unbenutzte Lernkarte zurückgeben; wenn es mehrere gibt, dann wird die mit dem
     * ältesten Erzeugungsdatum zurückgegeben.
     *
     * @return  Array mit höchstens einer unbenutzten Lernkarte; kann evtl. auch leer sein,
     *          wenn es nämlich derzeit  keine unbenutzten Lernkarten gibt.
     *          Für die evtl. zurückgelieferte Karlte gilt:
     *          <code>anzahl_falsch=0 AND anzahl_richtig=0</code>
     */
    @Query("SELECT * FROM LernkarteEntity WHERE anzahl_falsch=0 AND anzahl_richtig=0 ORDER BY datetime_erzeugung ASC LIMIT 1")
    public LernkarteEntity[] getUnbenutzteKarte();

    /**
     * Query für {@link LernModusEnum#NOCH_NIE_RICHTIG_BEANTWORTET}.
     * Wenn es mehrere Lernkarten gibt, für die <code>anzahl_richtig = 0</code> gilt, dann
     * wird die zurückgeliefert, bei der die letzte falsche Antwort am längsten zurückliegt.
     *
     * @return  Array mit höchstens einer Lernkarte (kann aber auch leer sein).
     *          Für diese Lernkarte gilt: <code>anzahl_richtig=0</code>
     */
    @Query("SELECT * FROM LernkarteEntity WHERE anzahl_richtig = 0 ORDER BY datetime_falsch ASC LIMIT 1")
    public LernkarteEntity[] getNochNieRichtigBeantworteteKarte();

    /**
     * Query für {@link LernModusEnum#MEHR_FALSCHE_ALS_RICHTIGE_ANTWORTEN}.
     * Wenn es mehrere Lernkarten gibt, für die die Anzahl der falschen Antworten echt-größer
     * als die der richtigen Antworten ist, dann wird die zurückgeliefert, bei der die
     * letzte falsche Antwort am längsten zurückliegt.
     *
     * @return  Array mit höchstens einer Lernkarte (kann aber auch leer sein).
     *          Für diese Lernkarte gilt: <code>anzahl_richtig &lt; anzahl_falsch</code>
     */
    @Query("SELECT * FROM LernkarteEntity WHERE anzahl_falsch > anzahl_richtig ORDER BY datetime_falsch ASC LIMIT 1")
    public LernkarteEntity[] getMehrFalscheAlsRichtigeAntwortenKarte();

    /**
     * Query für {@link LernModusEnum#SCHON_LANGE_NICHT_MEHR_RICHTIG_BEANTWORTET}.
     *
     * @return  Array mit Lernkarte, deren Zeitstempel für die letzte richtige Antwort am längsten
     *          zurückliegt.
     */
    @Query("SELECT * FROM LernkarteEntity ORDER BY datetime_richtig ASC LIMIT 1")
    public LernkarteEntity[] getKarteSchonLangeNichtMehrRichtigBeantwortet();

    /**
     * Query für {@link LernModusEnum#SCHON_LANGE_NICHT_MEHR_FALSCH_BEANTWORTET}.
     *
     * @return  Array mit Lernkarte, deren Zeitstempel für die letzte falsche Antwort am längsten
     *          zurückliegt.
     */
    @Query("SELECT * FROM LernkarteEntity ORDER BY datetime_falsch ASC LIMIT 1")
    public LernkarteEntity[] getKarteSchonLangeNichtFalschRichtigBeantwortet();

    /**
     * Query für {@link LernModusEnum#ZUFAELLIG}.
     *
     * @return  Array mit einer zufällig ausgewählten Lernkarte.
     */
    @Query("SELECT * FROM LernkarteEntity ORDER BY RANDOM() LIMIT 1")
    public LernkarteEntity[] getZufaelligeLernkarte();

    /**
     * Query für Listendarstellung; liefert ein {@link Cursor}-Objekt zurück.
     *
     * @return  Cursor-Objekt, das die Liste aller Lernkarten repräsentiert;
     *          Einträge sind aufsteigend nach Datum der Erzeugung sortiert.
     */
    @Query("SELECT * FROM LernkarteEntity ORDER BY datetime_erzeugung ASC")
    public Cursor getCursorFuerListe();

}
