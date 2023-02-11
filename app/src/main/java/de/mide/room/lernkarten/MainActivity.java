package de.mide.room.lernkarten;

import static de.mide.room.lernkarten.helferlein.DialogHelper.zeigeDialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.mide.room.lernkarten.activities.LernMenueActivity;
import de.mide.room.lernkarten.activities.ListenActivity;
import de.mide.room.lernkarten.activities.NeueLernkarteActivity;
import de.mide.room.lernkarten.db.LernkartenDao;
import de.mide.room.lernkarten.db.MeineDatenbank;


/**
 * Haupt-Activity der Lernkarten-App, zeigt Hauptmenü an.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Referenz auf Instanz von Unterklasse von <code>RoomDatabase</code>;
     * wird nicht nur holen von DAO-Referenz benötigt, sondern auch für
     * Aufruf Methode <code>clearAllTables()</code> um alle Tabelleneinträge
     * zu löschen.
     */
    private MeineDatenbank _datenbank = null;

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

        _datenbank = MeineDatenbank.getSingletonInstance(this);
        _dao = _datenbank.lernkartenDao();
    }

    /**
     * Lifecycle-Methode zur Aktualisierung der Anzeige mit der Anzahl der derzeit
     * in der DB gespeicherten Lernkarten. Diese Lifecycle-Methode wird aufgerufen,
     * wenn die Activity vom Zustand "unsichtbar" in den Zustand "sichtbar" wechselt.
     */
    @Override
    public void onStart() {

        super.onStart();

        int anzahl = _dao.getAnzahlDatensaetze();
        _anzahlTextView.setText("Anzahl Lernkarten: " + anzahl);
    }

    /**
     * Button-Event-Handler für Anlegen neuer Lernkarte, also Navigation zu anderer
     * Activity.
     *
     * @param view  Button, der das Event auslöst hat.
     */
    public void onButtonNeueLernkarte(View view) {

        Intent intent = new Intent(this, NeueLernkarteActivity.class);
        startActivity(intent);
    }

    /**
     * Button-Event-Handler für Nativation zur Activity, auf der alle Lernkarten in Form
     * einer Liste angezeigt werden.
     *
     * @param view  Button, der das Event auslöst hat.
     */
    public void onButtonListe(View view) {

        Intent intent = new Intent(this, ListenActivity.class);
        startActivity(intent);
    }

    /**
     * Button-Event-Handler für Anlegen neuer Lernkarte, also Navigation zu anderer Activity;
     * die Navigation wird aber nur durchgeführt, wenn es
     *
     * @param view  Button, der das Event auslöst hat.
     */
    public void onButtonLernen(View view) {

        int anzahlDatensaetze = _dao.getAnzahlDatensaetze();
        if (anzahlDatensaetze == 0) {

            zeigeDialog(this, "Fehler",
                    "Lernen ist noch nicht möglich, weil keine Lernkarten definiert sind.");
            return;
        }
        Intent intent = new Intent(this, LernMenueActivity.class);
        startActivity(intent);
    }

    /**
     * Button-Event-Handler für Löschen aller Lernkarten (nach Sicherheitsabfrage).
     *
     * @param view  Button, der das Event auslöst hat.
     */
    public void onButtonAlleLoeschen(View view) {

        int anzahlDatensaetze = _dao.getAnzahlDatensaetze();
        if (anzahlDatensaetze == 0) {

            zeigeDialog(this, "Fehler",
                    "Datenbank ist leer, es gibt nichts zu löschen.");
            return;
        }

        OnClickListener onJaButtonListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                _datenbank.clearAllTables();

                _anzahlTextView.setText("Anzahl Lernkarten: 0");
            }
        };

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Sicherheitsabfrage");
        dialogBuilder.setMessage("Wollen Sie wirklich alle gespeicherten Lernkarten löschen?");

        dialogBuilder.setPositiveButton("Ja, alle löschen", onJaButtonListener);
        dialogBuilder.setNegativeButton("Abbrechen", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}