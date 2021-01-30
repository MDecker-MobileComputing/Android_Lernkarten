package de.mide.lernkarten;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Haupt-Activity der Lernkarten-App, zeigt Hauptmenü an.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {

    /**
     * Lifecycle-Methode für Initialisierung der Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Button-Event-Handler für Anlegen neuer Lernkarte.
     *
     * @param view  UI-Element (Button), welches das Event auslöst hat.
     */
    public void onButtonNeueLernkarte(View view) {

        Intent intent = new Intent(this, NeueLernkarteActivity.class);
        startActivity(intent);
    }

}