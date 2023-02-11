package de.mide.room.lernkarten.activities;

import static de.mide.room.lernkarten.helferlein.DialogHelper.zeigeDialog;
import static android.view.View.VISIBLE;
import static android.view.View.INVISIBLE;
import static de.mide.room.lernkarten.helferlein.IGlobaleKonstanten.TAG4LOGGING;
import static de.mide.room.lernkarten.helferlein.IGlobaleKonstanten.EXTRA_KEY_LERN_MODUS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import de.mide.room.lernkarten.R;
import de.mide.room.lernkarten.db.LernkarteEntity;
import de.mide.room.lernkarten.db.LernkartenDao;
import de.mide.room.lernkarten.db.MeineDatenbank;
import de.mide.room.lernkarten.helferlein.LernModusEnum;

/**
 * Activity zur Anzeige einer Lernkarte. Die Lernkarten werden anhand des Lernmodus, der
 * als Extra übergeben wird, ausgewählt. Mit einem Button kann die Lernkarte "umgedreht"
 *  werden, danach kann der Nutzer eingeben, ob er/sie die richtige Antwort wußte oder
 *  nicht.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class ZeigeLernkarteActivity extends AppCompatActivity {

    /** Zähler für die bisher angezeigten Lernkarten. */
    private int _lernkartenZaehler = 0;

    /**
     * Spezifiziert Lernmodus, wird über Extra in Intent, mit dem diese Activity gestartet
     * wird, mitgegeben.
     */
    private LernModusEnum _lernModus;

    /**
     * Aktuell ausgewählte Lernkarte; kann <code>null</code> sein, nämlich dann, wenn
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

    /**
     * Button, mit der Nutzer eingibt, dass er die richtige Antwort wusste; wird erst dann
     * angezeigt, wenn die Rückseite der Lernkarte angezeigt wird.
     */
    private Button _richtigeAntwortButton = null;

    /**
     * Button, mit der Nutzer eingibt, dass er die richtige Antwort NICHT wusste; wird erst dann
     * angezeigt, wenn die Rückseite der Lernkarte angezeigt wird.
     */
    private Button _falscheAntwortButton = null;

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

        setzeTitel();

        Intent intent = getIntent(); // Intent, mit dem diese Activity gestartet
        _lernModus = (LernModusEnum) intent.getSerializableExtra(EXTRA_KEY_LERN_MODUS);

        MeineDatenbank db = MeineDatenbank.getSingletonInstance(this);
        _dao = db.lernkartenDao();

        _umdrehenButton          = findViewById(R.id.umdrehenButton         );
        _vorderseiteTextview     = findViewById(R.id.karteVorneTextview     );
        _rueckseiteTextview      = findViewById(R.id.karteHintenTextview    );
        _rueckseiteLabelTextview = findViewById(R.id.rueckseiteLabelTextview);

        _richtigeAntwortButton = findViewById(R.id.richtigeAntwortButton);
        _falscheAntwortButton  = findViewById(R.id.falscheAntwortButton);

        holeLernkarte();
    }

    /**
     * Setzt Titel der Activity, der Anzahl der bearbeiteten Karten enthält.
     */
    private void setzeTitel() {

        String titel = "Lernen / Üben (Karten: " + _lernkartenZaehler + ")";
        setTitle(titel);
    }

    /**
     * Holt eine (weitere) Lernkarte aus der Datenbank für den aktuellen Lernmodus
     * und zeigt sie an. Die Buttons werden auch entsprechend auf sichtbar bzw.
     * unsichtbar geschaltet.
     */
    private void holeLernkarte() {

        LernkarteEntity[] lernkarteArray = new LernkarteEntity[0];

        _lernkarteEntity = null;

        _rueckseiteTextview.setVisibility(INVISIBLE);
        _rueckseiteLabelTextview.setVisibility(INVISIBLE);
        _richtigeAntwortButton.setVisibility(INVISIBLE);
        _falscheAntwortButton.setVisibility(INVISIBLE);

        _vorderseiteTextview.setText( "" );
        _rueckseiteTextview.setText(  "" );

        switch (_lernModus) {

            case NOCH_NIE_VERWENDET:
                lernkarteArray = _dao.getUnbenutzteKarte();
                break;

            case NOCH_NIE_RICHTIG_BEANTWORTET:
                lernkarteArray = _dao.getNochNieRichtigBeantworteteKarte();
                break;

            case MEHR_FALSCHE_ALS_RICHTIGE_ANTWORTEN:
                lernkarteArray = _dao.getMehrFalscheAlsRichtigeAntwortenKarte();
                break;

            case SCHON_LANGE_NICHT_MEHR_RICHTIG_BEANTWORTET:
                lernkarteArray = _dao.getKarteSchonLangeNichtMehrRichtigBeantwortet();
                break;

            case SCHON_LANGE_NICHT_MEHR_FALSCH_BEANTWORTET:
                lernkarteArray = _dao.getKarteSchonLangeNichtFalschRichtigBeantwortet();
                break;

            case ZUFAELLIG:
                lernkarteArray = _dao.getZufaelligeLernkarte();
                break;

            default:
                zeigeDialog( this,"Interner Fehler",
                        "Unerwarteter Wert für Lern-Modus: " + _lernModus);
        }

        if (lernkarteArray.length > 0) {

            _lernkarteEntity = lernkarteArray[0];
        }

        if (_lernkarteEntity != null) {

            _lernkartenZaehler++;
            setzeTitel();

            _umdrehenButton.setEnabled(true);
            _vorderseiteTextview.setText( _lernkarteEntity.textVorne  );
            _rueckseiteTextview.setText(  _lernkarteEntity.textHinten ); // Text wird nicht gleich sichtbar

            _umdrehenButton.setVisibility(VISIBLE);

        } else {

            if (_lernkartenZaehler == 0) {

                zeigeDialog( this,"Info",
                        "Keine einzige Lernkarte für den gewählten Lernmodus gefunden.");

            } else {

                zeigeDialog( this,"Info",
                        "Keine weitere Lernkarte für den gewählten Lernmodus gefunden.");
            }
        }
    }

    /**
     * Event-Handler für Button, um die Karte "umzudrehen", also die Rückseite mit der
     * Antwort aufzudecken.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonUmdrehen(View view) {

        if (_lernkarteEntity == null) {

            Log.e(TAG4LOGGING,
                    "Button zum Umdrehen von Lernkarte sollte bei fehlender Lernkarte nicht aktiv sind.");
            return;
        }

        _rueckseiteTextview.setVisibility(VISIBLE);
        _rueckseiteLabelTextview.setVisibility(VISIBLE);

        _richtigeAntwortButton.setVisibility(VISIBLE);
        _falscheAntwortButton.setVisibility(VISIBLE);

        _umdrehenButton.setVisibility(INVISIBLE);
    }

    /**
     * Event-Handler für Button, mit der Nutzer eingibt, dass er die richtige Antwort wusste.
     * Das Lernkarten-Objekt in der DB-Tabelle wird entsprechend aktualisiert; danach wird die
     * nächste Lernkarte geholt.
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonRichtig(View view) {

        _lernkarteEntity.anzahlRichtig++;
        _lernkarteEntity.dateTimeLetztesMalRichtigeAntwort = new Date();
        _dao.updateLernkarte(_lernkarteEntity);

        holeLernkarte();
    }

    /**
     * Event-Handler für Button, mit der Nutzer eingibt, dass er NICHT die richtige Antwort wusste.
     * Das Lernkarten-Objekt in der DB-Tabelle wird entsprechend aktualisiert; danach wird die
     * nächste Lernkarte geholt.
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonFalsch(View view) {

        _lernkarteEntity.anzahlFalsch++;
        _lernkarteEntity.dateTimeLetztesMalFalscheAntwort = new Date();
        _dao.updateLernkarte(_lernkarteEntity);

        holeLernkarte();
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