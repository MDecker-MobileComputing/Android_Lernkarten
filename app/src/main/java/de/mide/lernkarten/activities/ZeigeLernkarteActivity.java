package de.mide.lernkarten.activities;

import static de.mide.lernkarten.helpers.DialogHelper.zeigeDialog;
import static android.view.View.VISIBLE;
import static android.view.View.INVISIBLE;
import static de.mide.lernkarten.helpers.IGlobaleKonstanten.TAG4LOGGING;
import static de.mide.lernkarten.helpers.IGlobaleKonstanten.EXTRA_KEY_LERN_MODUS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.mide.lernkarten.R;
import de.mide.lernkarten.db.LernkarteEntity;
import de.mide.lernkarten.db.LernkartenDao;
import de.mide.lernkarten.db.MeineDatenbank;
import de.mide.lernkarten.helpers.LernModusEnum;

/**
 * Activity zur Anzeige einer Lernkarte, die als Intent übergeben wird.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class ZeigeLernkarteActivity extends AppCompatActivity {

    private boolean _rueckseiteSichtbar = false;

    /**
     * Spezifiziert Lernmodus, wird über Extra in Intent, mit dem diese Activity gestartet
     * wird, mitgegeben.
     */
    private LernModusEnum _lernModus;

    /**
     * Aktuell ausgewählte Lernkarte; kann <code>null</code> sein, nämlich dann wenn
     * es für den gewählten Lernmodus keine Karten gibt (z.B. keine unbenutzten Karten).
     */
    private LernkarteEntity _lernkarteEntity = null;

    /** DAO für Zugriff auf Tabelle mit Lernkarten. */
    private LernkartenDao _dao;

    /**
     * Buttom, um die gerade angezeigte Lernkarte zumdrehen; ist nur dann aktiv, wenn
     * eine Lernkarte gefunden wurde.
     */
    private Button _umdrehenButton = null;

    /** UI-Element zur Darstellung Text für Vorderseite von Karte. */
    private TextView _vorderseiteTextview = null;

    /** UI-Element zur Darstellung Text für Rückseite von Karte. */
    private TextView _rueckseiteTextview = null;

    /** UI-Element als Label für Text für Rückseite. */
    private TextView _rueckseiteLabelTextview = null;

    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeige_lernkarte);

        Intent intent = getIntent(); // Intent, mit dem diese Activity gestartet
        _lernModus = (LernModusEnum) intent.getSerializableExtra(EXTRA_KEY_LERN_MODUS);

        MeineDatenbank db = MeineDatenbank.getSingletonInstance(this);
        _dao = db.lernkartenDao();

        _umdrehenButton          = findViewById(R.id.umdrehenButton         );
        _vorderseiteTextview     = findViewById(R.id.karteVorneTextview     );
        _rueckseiteTextview = findViewById(R.id.karteHintenTextview    );
        _rueckseiteLabelTextview = findViewById(R.id.rueckseiteLabelTextview);

        holeLernkarte();
    }

    /**
     * Holt eine Lernkarte aus der Datenbank für den aktuellen Lernmodus.
     */
    private void holeLernkarte() {

        LernkarteEntity[] lernkarteArray;

        _lernkarteEntity = null;
        _rueckseiteTextview.setVisibility(INVISIBLE);
        _rueckseiteLabelTextview.setVisibility(INVISIBLE);

        switch (_lernModus) {

            case NOCH_NIE_VERWENDET:
                lernkarteArray = _dao.getUnbenutzteKarte();
                if (lernkarteArray.length > 0) {

                    _lernkarteEntity = lernkarteArray[0];
                }
            break;

            default:
                zeigeDialog( this,"Interner Fehler",
                        "Unerwarteter Wert für Lern-Modus: " + _lernModus);
        }

        if (_lernkarteEntity == null) {

            zeigeDialog( this,"Fehler",
                    "Keine einzige Lernkarte für den gewählten Lernmodus gefunden.");
        } else {

            _umdrehenButton.setEnabled(true);
            _vorderseiteTextview.setText( _lernkarteEntity.textVorne  );
            _rueckseiteTextview.setText(  _lernkarteEntity.textHinten ); // Text wird nicht gleich sichtbar

            _umdrehenButton.setVisibility(VISIBLE);
        }
    }

    /**
     * Event-Handler für Button, um die Karte "umzudrehen".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonUmdrehen(View view) {

        if (_lernkarteEntity == null) {

            Log.e(TAG4LOGGING, "Button zum Umdrehen von Lernkarte sollte bei fehlender Lernkarte nicht aktiv sind.");
            return;
        }

        _rueckseiteTextview.setVisibility(VISIBLE);
        _rueckseiteLabelTextview.setVisibility(VISIBLE);

        _umdrehenButton.setVisibility(INVISIBLE);
    }

    /**
     * Event-Handler für Button, mit dem der Lernmodus beendet wird, also zur Activity mit
     * den Buttons für die veschiedenen Lernmodi zurückgesprungen wird.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonLernmodusBeenden(View view) {

        finish();
    }

}