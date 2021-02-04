package de.mide.lernkarten.activities;

import androidx.appcompat.app.AppCompatActivity;

import de.mide.lernkarten.R;
import de.mide.lernkarten.db.LernkartenDao;
import de.mide.lernkarten.db.MeineDatenbank;
import de.mide.lernkarten.helpers.MeinCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Activity zur Darstellung aller Lernkarten als Liste.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class ListenActivity extends AppCompatActivity {

    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        setTitle("Liste aller Lernkarten");

        MeineDatenbank datenbank = MeineDatenbank.getSingletonInstance(this);
        LernkartenDao lernkartenDao = datenbank.lernkartenDao();
        Cursor cursor = lernkartenDao.getCursorFuerListe();

        MeinCursorAdapter cursorAdapter = new MeinCursorAdapter( this, cursor, 0); // flags=0

        ListView listView = findViewById(R.id.lernkartenListView);
        listView.setAdapter(cursorAdapter);
    }

    /**
     * Event-Handler für Button "Abbrechen", kehrt zum MainActivity zurück.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonZurueckMainActivity(View view) {

        finish();
    }

}