package de.mide.room.lernkarten.activities;

import static de.mide.room.lernkarten.helferlein.DialogHelper.zeigeDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import de.mide.room.lernkarten.R;
import de.mide.room.lernkarten.db.LernkarteEntity;
import de.mide.room.lernkarten.db.LernkartenDao;
import de.mide.room.lernkarten.db.MeineDatenbank;

/**
 * Activity, um weitere Lernkarte der Datenbank hinzuzufügen.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class NeueLernkarteActivity extends AppCompatActivity {

    /** DAO-Objekt für CRUD-Operation auf Tabelle mit Lernkarten. */
    private LernkartenDao _dao = null;

    /** Element zur Eingabe Text auf Vorderseite der Lernkarte. */
    private EditText _vorneEditText = null;

    /** Element zur Eingabe Text auf Rückseite der Lernkarte. */
    private EditText _hintenEditText = null;


    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_lernkarte);

        setTitle( "Neue Lernkarte" );

        MeineDatenbank db = MeineDatenbank.getSingletonInstance(this);
        _dao = db.lernkartenDao();

        _vorneEditText  = findViewById(R.id.vorneTextview );
        _hintenEditText = findViewById(R.id.hintenTextview);
    }


    /**
     * Event-Handler für Button zum Einfügen einer neuen Lernkarte in die Datenbank.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonAnlegen(View view) {

        String textVorne  = _vorneEditText.getText().toString().trim();
        if (textVorne.length() == 0) {

            zeigeDialog(this, "Fehler", "Kein Text für Vorderseite eingegeben.");
            return;
        }

        String textHinten = _hintenEditText.getText().toString().trim();
        if (textHinten.length() == 0) {

            zeigeDialog(this, "Fehler", "Kein Text für Rückseite eingegeben.");
            return;
        }

        // Datensatz für Lernkarte in Tabelle einfügen
        LernkarteEntity lernkarte = new LernkarteEntity();

        lernkarte.textVorne  = textVorne;
        lernkarte.textHinten = textHinten;

        lernkarte.anzahlFalsch  = 0;
        lernkarte.anzahlRichtig = 0;

        lernkarte.dateTimeErzeugung = new Date();

        _dao.insertLernkarte(lernkarte);

        zeigeDialog(this, "Erfolg", "Datensatz wurde eingefügt.");

        _vorneEditText.setText("");
        _hintenEditText.setText("");
    }

    /**
     * Event-Handler für Button "Abbrechen", kehrt zu MainActivity zurück.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonZurueckMainActivity(View view) {

        finish();
    }

}