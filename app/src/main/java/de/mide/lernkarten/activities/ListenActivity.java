package de.mide.lernkarten.activities;

import androidx.appcompat.app.AppCompatActivity;

import de.mide.lernkarten.R;

import android.os.Bundle;
import android.view.View;

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