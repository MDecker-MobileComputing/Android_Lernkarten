package de.eldecker.room.lernkarten.helferlein;

import android.app.AlertDialog;
import android.content.Context;

import de.eldecker.room.lernkarten.R;


/**
 * Klasse enthält Hilfsmethoden für Erzeugen von Dialogen.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class DialogHelper {

    /**
     * Hilfsmethode zur Anzeige eines Dialogs für Fehlermeldungen u.ä.
     *
     * @param context  Context (Selbstreferenz auf aufrufende Activity).
     *
     * @param title  Dialog-Titel
     *
     * @param nachricht  Eigentlicher Text des Dialogs
     */
    public static void zeigeDialog(Context context, String title, String nachricht) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(nachricht);

        dialogBuilder.setPositiveButton( context.getText(R.string.weiter_button), null );
        dialogBuilder.setCancelable(false);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}
