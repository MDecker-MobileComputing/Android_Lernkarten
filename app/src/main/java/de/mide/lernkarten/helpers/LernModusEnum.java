package de.mide.lernkarten.helpers;

/**
 * Eigener Aufzählungstyp für die verschiedenen Lern-Modi. Ein Lern-Modus legt fest,
 * nach welcher Regel die nächste Lernkarte ausgewälte wird.
 * <br><br>
 *
 * Enum-Werte als Extra in einem Android-Intent übergeben, siehe
 * <a href="https://stackoverflow.com/a/9753178">diese StackOverflow-Antwort</a>.
 * Enums sind immer/automatisch <code>Serializable</code>, siehe z.B.
 * <a href="https://www.infoworld.com/article/2072870/java-enums-are-inherently-serializable.html">hier</a>.
 * <br><br>
 *
 * Für jeden Lernmodus in dieser Enum-Datei gibt es eine zugehörige Query-Methode im DAO-Interface
 * {@link de.mide.lernkarten.db.LernkartenDao}.
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public enum LernModusEnum {

    /**
     * Lernkarten auswählen, für die <code>anzahl_richtig=0</code> und
     * <code>anzahl_falsch=0</code> gilt. Aus den Lernkarten mit dieser
     * Bedingung wird zuerst die mit dem ältesten Erstellungsdatum
     * ausgewählt.
     */
    NOCH_NIE_VERWENDET,

    /**
     * Lernkarten auswählen, für die <code>anzahl_richtig=0</code> gilt.
     */
    NOCH_NIE_RICHTIG_BEANTWORTET,

    /**
     * Lernkarten auswählen, für die <code>anzahl_richtig < anzahl_falsch</code> gilt.
     */
    MEHR_FALSCHE_ALS_RICHTIGE_ANTWORTEN,

    /**
     * Lernkarte aus DB holen, deren letzte richtige Beantwortung am längsten zurückliegt.
     */
    SCHON_LANGE_NICHT_MEHR_RICHTIG_BEANTWORTET,

    /**
     * Lernkarte aus DB holen, deren letzte falsche Beantwortung am längsten zurückliegt.
     */
    SCHON_LANGE_NICHT_MEHR_FALSCH_BEANTWORTET

}
