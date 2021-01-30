package de.mide.lernkarten;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.mide.lernkarten.db.LernkarteEntity;
import de.mide.lernkarten.db.LernkartenDao;
import de.mide.lernkarten.db.MeineDatenbank;

/**
 * Activity, um weitere Lernkarte der Datenbank hinzuzufügen.
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class NeueLernkarteActivity extends Activity {

    /** Singleton-Instanz des Datenbank-Objekts. */
    private MeineDatenbank _meineDatenbank = null;

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

        _meineDatenbank = MeineDatenbank.getSingletonInstance(this);

        _vorneEditText  = findViewById(R.id.vorneTextview);
        _hintenEditText = findViewById(R.id.hintenTextview);
    }


    /**
     * Event-Handler für Button zum Einfügen einer neuen Lernkarte in
     * die Datenbak.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonAnlegen(View view) {

        String textVorne  = _vorneEditText.getText().toString().trim();
        String textHinten = _hintenEditText.getText().toString().trim();

        if (textVorne.length() == 0 || textHinten.length() == 0) {

            Toast.makeText(this, "Bitte Text für beide Seiten eingeben.", Toast.LENGTH_LONG).show();
            return;
        }

        LernkartenDao dao = _meineDatenbank.lernkartenDao();

        LernkarteEntity lernkarte = new LernkarteEntity();

        lernkarte.textVorne  = textVorne;
        lernkarte.textHinten = textHinten;

        lernkarte.anzahlFalsch  = 0;
        lernkarte.anzahlRichtig = 0;

        dao.insertLernkarte(lernkarte);

        Toast.makeText(this, "Datensatz wurde eingefügt.", Toast.LENGTH_LONG).show();

        _vorneEditText.setText("");
        _hintenEditText.setText("");
    }

}