package de.mide.lernkarten.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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

    }

}