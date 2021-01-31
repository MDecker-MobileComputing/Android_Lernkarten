package de.mide.lernkarten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.mide.lernkarten.db.LernkartenDao;
import de.mide.lernkarten.db.MeineDatenbank;


/**
 * Haupt-Activity der Lernkarten-App, zeigt Hauptmenü an.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {

    /** DAO für Zugriff auf Tabelle mit Lernkarten. */
    private LernkartenDao _dao = null;

    /** UI-Element zur Anzeige der aktuellen Anzahl der Lernkarten. */
    private TextView _anzahlTextView = null;

    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _anzahlTextView = findViewById(R.id.anzahlTextView);

        MeineDatenbank db = MeineDatenbank.getSingletonInstance(this);
        _dao = db.lernkartenDao();

        int anzahl = _dao.getAnzahlDatensaetze();
        _anzahlTextView.setText("Anzahl Lernkarten: " + anzahl);
    }

    /**
     * Button-Event-Handler für Anlegen neuer Lernkarte.
     *
     * @param view  UI-Element (Button), welches das Event auslöst hat.
     */
    public void onButtonNeueLernkarte(View view) {

        Intent intent = new Intent(this, NeueLernkarteActivity.class);
        startActivity(intent);
    }

}