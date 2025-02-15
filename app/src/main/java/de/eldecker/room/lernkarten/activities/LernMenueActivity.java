package de.eldecker.room.lernkarten.activities;

import static de.eldecker.room.lernkarten.helferlein.IGlobaleKonstanten.EXTRA_KEY_LERN_MODUS;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.NOCH_NIE_VERWENDET;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.NOCH_NIE_RICHTIG_BEANTWORTET;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.MEHR_FALSCHE_ALS_RICHTIGE_ANTWORTEN;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.SCHON_LANGE_NICHT_MEHR_FALSCH_BEANTWORTET;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.SCHON_LANGE_NICHT_MEHR_RICHTIG_BEANTWORTET;
import static de.eldecker.room.lernkarten.helferlein.LernModusEnum.ZUFAELLIG;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.eldecker.room.lernkarten.R;


/**
 * Activity für Auswahl eines Lern-Modus.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class LernMenueActivity extends AppCompatActivity {

    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lern_menue);

        setTitle( "Lernen / üben" );
    }


    /**
     * Event-Handler für Button "Unbenutzte Lernkarten".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonUnbenutzt(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, NOCH_NIE_VERWENDET);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button "Noch nie richtig beantwortet".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonNochNieRichtig(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, NOCH_NIE_RICHTIG_BEANTWORTET);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button "Mehr falsche als richtige Antworten".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonMehrFalscheAlsRichtige(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, MEHR_FALSCHE_ALS_RICHTIGE_ANTWORTEN);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button "Schon lange nicht mehr richtig beantwortet".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonLangeNichtMehrRichtigBeantwortet(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, SCHON_LANGE_NICHT_MEHR_RICHTIG_BEANTWORTET);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button "Schon lange nicht mehr falsch beantwortet".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonLangeNichtMehrFalschBeantwortet(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, SCHON_LANGE_NICHT_MEHR_FALSCH_BEANTWORTET);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button "Zufällige Lernkarten".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonZufallskarte(View view) {

        Intent intent = new Intent(this, ZeigeLernkarteActivity.class);
        intent.putExtra(EXTRA_KEY_LERN_MODUS, ZUFAELLIG);
        startActivity(intent);
    }


    /**
     * Event-Handler für Button, um zurück zum Hauptmenü zu gehen.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonHauptmenu(View view) {

        finish();
    }

}