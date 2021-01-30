package de.mide.lernkarten.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Die Datenbank-Klasse enthält eine Getter-Methode für die DAOs.
 */
public abstract class MeineDatenbank extends RoomDatabase {

    /**
     * Name der Datenbank-Datei, die von SQLite3 angelegt wird.
     * DB-Datei wird im Ordner {@code /data/data/de.mide.lernkarten/databases}
     * gespeichert.
     */
    private static final String DB_DATEI_NAME = "lernkarten.db";

    /** Referenz auf Singleton-Instanz, wird lazy erzeugt. */
    private static MeineDatenbank SINGLETON_INSTANCE = null;

    /**
     * Getter für Singleton-Instanz der vorliegenden Klasse; bei Bedarf wird diese Instanz
     * erzeugt.
     *
     * @param context  Application Context, wird für evtl. Erzeugung der Singleton-Instanz
     *                 benötigt.
     *
     * @return  Singleton-Instanz
     */
    public static MeineDatenbank getSingletonInstance(Context context) {

        if (SINGLETON_INSTANCE == null) {

            SINGLETON_INSTANCE =
                    Room.databaseBuilder(

                            context.getApplicationContext(),
                            MeineDatenbank.class,
                            DB_DATEI_NAME

                    ).allowMainThreadQueries().build();
        }

        return SINGLETON_INSTANCE;
    }

}
