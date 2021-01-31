package de.mide.lernkarten;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }

}