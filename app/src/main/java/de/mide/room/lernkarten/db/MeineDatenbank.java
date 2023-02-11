package de.mide.room.lernkarten.db;

import static de.mide.room.lernkarten.helferlein.IGlobaleKonstanten.TAG4LOGGING;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Die Datenbank-Klasse enthält eine Getter-Methode für die DAOs.
 * <br><br>
 *
 * Für <code>exportSchema=false</code> siehe
 * <a href="https://stackoverflow.com/questions/44322178/">diese Antwort</a>.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
@Database(entities = {LernkarteEntity.class}, version=1, exportSchema = false)
@TypeConverters({MeineTypeConverter.class})
public abstract class MeineDatenbank extends RoomDatabase {

    /**
     * Name der Datenbank-Datei, die von SQLite3 angelegt wird.
     * DB-Datei wird im Ordner {@code /data/data/de.mide.lernkarten/databases}
     * gespeichert.
     */
    private static final String DB_DATEI_NAME = "lernkarten.db";

    /** Referenz auf Singleton-Instanz, wird erst bei Bedarf erzeugt (lazy creation). */
    private static MeineDatenbank SINGLETON_INSTANCE = null;

    /**
     * Getter für Lernkarten-DAO.
     *
     * @return  Lernkarten-DAO für Ausführung CRUD-Operation auf Tabelle mit Lernkarten.
     */
    public abstract LernkartenDao lernkartenDao();

    /**
     * Getter für Singleton-Instanz der vorliegenden Klasse; bei Bedarf wird diese Instanz
     * erzeugt.
     *
     * @param context  Application Context, wird für evtl. Erzeugung der Singleton-Instanz
     *                 benötigt.
     *
     * @return  Singleton-Instanz von Datenbank-Objekt, von dem die DAOs geholt werden können.
     */
    public static MeineDatenbank getSingletonInstance(Context context) {

        if (SINGLETON_INSTANCE == null) {

            SINGLETON_INSTANCE =
                    Room.databaseBuilder(

                            context.getApplicationContext(),
                            MeineDatenbank.class,
                            DB_DATEI_NAME

                    ).allowMainThreadQueries().build();

            Log.i(TAG4LOGGING, "Singleton-Instanz von Datenbank erzeugt.");
        }

        return SINGLETON_INSTANCE;
    }

}
