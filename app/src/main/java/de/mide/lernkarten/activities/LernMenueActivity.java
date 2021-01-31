package de.mide.lernkarten.activities;

import static de.mide.lernkarten.helpers.IGlobaleKonstanten.EXTRA_KEY_LERN_MODUS;
import static de.mide.lernkarten.helpers.LernModusEnum.NOCH_NIE_VERWENDET;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.mide.lernkarten.R;

/**
 * Activity für Menü der verschiedenen Lern-Modi.
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

        Toast.makeText(this, "Not implemented yet :-(", Toast.LENGTH_LONG).show();
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